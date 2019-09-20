// 
// Decompiled by Procyon v0.5.36
// 

package client.main;

import java.awt.event.WindowEvent;
import java.awt.Component;
import javax.swing.JOptionPane;
import client.socket.ThreadClient;
import client.giaodien.GiaoDienChinh;
import client.giaodien.GiaoDienVongChoi;
import client.giaodien.GiaoDienKetNoi;
import client.giaodien.GiaoDienDangNhap;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;

public class MainClient extends WindowAdapter
{
    ImageIcon imageIcon;
    public Image iconChinh;
    GiaoDienDangNhap giaoDienDangNhap;
    GiaoDienKetNoi giaoDienKetNoi;
    GiaoDienVongChoi giaoDienVongChoi;
    GiaoDienChinh giaoDienChinh;
    public ThreadClient threadClient;
    public String kieuDuLieu;
    
    public MainClient() {
        this.imageIcon = new ImageIcon(this.getClass().getResource("LogoTSTT.png"));
        this.iconChinh = this.imageIcon.getImage();
        this.giaoDienDangNhap = new GiaoDienDangNhap(this);
        this.giaoDienKetNoi = new GiaoDienKetNoi(this);
        this.giaoDienVongChoi = new GiaoDienVongChoi(this);
        this.giaoDienChinh = new GiaoDienChinh(this);
        this.threadClient = new ThreadClient(this);
        this.kieuDuLieu = "UTF-8";
    }
    
    public static void main(final String[] args) {
        final MainClient mainClient = new MainClient();
        mainClient.batDau();
    }
    
    public void batDau() {
        this.giaoDienKetNoi.hienThi();
    }
    
    public void ketNoiMayChu(final String svHost, final int svPort) {
        if (this.threadClient.taoKetNoi(svHost, svPort)) {
            this.giaoDienKetNoi.anDi();
            this.giaoDienDangNhap.hienThi();
        }
    }
    
    public void dangNhap(final String id, final String pass) {
        this.guiTinDenServer("DangNhap: " + id + " " + pass);
    }
    
    public void xuLiTinNhan(final String tinNhan) {
        System.out.println(tinNhan);
        if (tinNhan.startsWith("Login: ")) {
            if (tinNhan.indexOf("OK") != -1) {
                this.giaoDienDangNhap.anDi();
                this.giaoDienChinh.hienThi();
                this.giaoDienVongChoi.hienThi();
            }
            else {
                JOptionPane.showMessageDialog(this.giaoDienDangNhap, "Sai ID ho\u1eb7c m\u1eadt kh\u1ea9u");
            }
        }
        if (tinNhan.startsWith("Dap an: ")) {
            this.giaoDienVongChoi.hienThiDapAn(tinNhan.substring(tinNhan.indexOf(" ", tinNhan.indexOf(" ") + 1) + 1));
            return;
        }
        if (tinNhan.startsWith("Bat dau: ")) {
            this.giaoDienVongChoi.hienThiThoiGian(" 00 ");
            return;
        }
        if (tinNhan.startsWith("Thoi gian: ")) {
            this.giaoDienVongChoi.hienThiThoiGian(tinNhan.substring(11));
            return;
        }
        if (tinNhan.startsWith("Ket thuc: ")) {
            this.giaoDienVongChoi.checkGuiDapAn = false;
            return;
        }
        if (tinNhan.startsWith("Reset: ")) {
            this.giaoDienVongChoi.hienThiThoiGian(" 00 ");
            this.giaoDienVongChoi.dapAnTruoc = "";
            this.giaoDienVongChoi.checkGuiDapAn = false;
            return;
        }
        if (tinNhan.startsWith("Ten ID: ")) {
            this.giaoDienVongChoi.hienThiTenNguoiChuoi(tinNhan.substring(8));
            this.giaoDienChinh.thayDoiTen(tinNhan.substring(8));
            return;
        }
        if (tinNhan.startsWith("SetDiem: ")) {
            this.giaoDienVongChoi.hienThiDiemSo(tinNhan.substring(9));
            this.giaoDienChinh.thayDoiDiem(tinNhan.substring(9));
            return;
        }
        if (tinNhan.startsWith("Hien thi: ")) {
            this.giaoDienVongChoi.hienThiDapAn(tinNhan.substring(10));
            System.out.println(tinNhan);
            return;
        }
        if (tinNhan.equals("Kiem tra: ")) {
            this.guiTinDenServer("Kiem tra: ");
        }
    }
    
    public void guiTinDenServer(final String tinNhan) {
        this.threadClient.guiTinDenServer(tinNhan);
    }
    
    public void ketNoiLai() {
        JOptionPane.showMessageDialog(null, "G\u1eb7p s\u1ef1 c\u1ed1 \u0111\u01b0\u1eddng truy\u1ec1n,\n H\u00e3y kh\u1edfi \u0111\u1ed9ng l\u1ea1i ph\u1ea7n m\u1ec1m?", "L\u1ed7i", 0);
        this.exitChuongTrinh();
    }
    
    public void exitChuongTrinh() {
        final String st = JOptionPane.showInputDialog(null, "Nh\u1eadp EXIT \u0111\u1ec3 tho\u00e1t...");
        if (st.equals("EXIT")) {
            System.exit(1);
        }
    }
    
    @Override
    public void windowClosing(final WindowEvent e) {
        this.exitChuongTrinh();
    }
}

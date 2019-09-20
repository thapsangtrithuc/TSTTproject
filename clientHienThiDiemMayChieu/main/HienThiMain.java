// 
// Decompiled by Procyon v0.5.36
// 

package clienthienthi.main;

import java.awt.event.WindowEvent;
import java.awt.Component;
import javax.swing.JOptionPane;
import clienthienthi.socket.ThreadHienThi;
import clienthienthi.giaodien.GiaoDienHienThi;
import clienthienthi.giaodien.GiaoDienDangNhap;
import clienthienthi.giaodien.GiaoDienKetNoi;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;

public class HienThiMain extends WindowAdapter
{
    public ImageIcon icon;
    public Image iconChinh;
    GiaoDienKetNoi giaoDienKetNoi;
    GiaoDienDangNhap giaoDienDangNhap;
    GiaoDienHienThi giaoDienHienThi;
    ThreadHienThi threadHienThi;
    public String kieuDuLieu;
    
    public static void main(final String[] args) {
        new HienThiMain();
    }
    
    public HienThiMain() {
        this.icon = new ImageIcon(this.getClass().getResource("LogoTSTT.png"));
        this.iconChinh = this.icon.getImage();
        this.giaoDienKetNoi = new GiaoDienKetNoi(this);
        this.giaoDienDangNhap = new GiaoDienDangNhap(this);
        this.giaoDienHienThi = new GiaoDienHienThi(this);
        this.threadHienThi = new ThreadHienThi(this);
        this.kieuDuLieu = "UTF-8";
        this.giaoDienKetNoi.hienThi();
    }
    
    public void dangNhap(final String id, final String pass) {
        this.guiTinDenServer("DangNhap: " + id + " " + pass);
    }
    
    public void taoKetNoi(final String svHost, final int svPort) {
        if (this.threadHienThi.taoKetNoi(svHost, svPort)) {
            this.giaoDienKetNoi.anDi();
            this.giaoDienDangNhap.hienThi();
        }
    }
    
    public void guiTinDenServer(final String tinNhan) {
        this.threadHienThi.guiTinDenServer(tinNhan);
    }
    
    public void xuLiTinNhan(final String tinNhan) {
        if (tinNhan.startsWith("Login: ")) {
            if (tinNhan.equals("Login: OK")) {
                this.giaoDienDangNhap.anDi();
                this.giaoDienHienThi.hienThi();
            }
            else {
                JOptionPane.showMessageDialog(null, "Sai ID ho\u1eb7c m\u1eadt kh\u1ea9u");
            }
            return;
        }
        if (tinNhan.startsWith("Bat dau: ")) {
            this.giaoDienHienThi.hienThiThoiGian(" " + tinNhan.substring(9));
            return;
        }
        if (tinNhan.startsWith("Thoi gian: ")) {
            String hienThi = tinNhan.substring(11);
            if (hienThi.length() < 2) {
                hienThi = "0" + hienThi;
            }
            this.giaoDienHienThi.hienThiThoiGian(" " + hienThi);
            return;
        }
        if (tinNhan.startsWith("Ket thuc: ")) {
            this.giaoDienHienThi.hienThiThoiGian(" 00 ");
            return;
        }
        if (tinNhan.startsWith("Reset: ")) {
            this.giaoDienHienThi.resetNhan();
            return;
        }
        if (tinNhan.startsWith("#1: ")) {
            final int vt1 = tinNhan.indexOf("&");
            final int vt2 = tinNhan.indexOf("&", vt1 + 1);
            final int vt3 = tinNhan.indexOf("&", vt2 + 1);
            this.giaoDienHienThi.hienThiNhan1(tinNhan.substring(vt1 + 1, vt2), tinNhan.substring(vt2 + 1, vt3), tinNhan.substring(vt3 + 1));
            return;
        }
        if (tinNhan.startsWith("#2: ")) {
            final int vt1 = tinNhan.indexOf("&");
            final int vt2 = tinNhan.indexOf("&", vt1 + 1);
            final int vt3 = tinNhan.indexOf("&", vt2 + 1);
            this.giaoDienHienThi.hienThiNhan2(tinNhan.substring(vt1 + 1, vt2), tinNhan.substring(vt2 + 1, vt3), tinNhan.substring(vt3 + 1));
            return;
        }
        if (tinNhan.startsWith("#3: ")) {
            final int vt1 = tinNhan.indexOf("&");
            final int vt2 = tinNhan.indexOf("&", vt1 + 1);
            final int vt3 = tinNhan.indexOf("&", vt2 + 1);
            this.giaoDienHienThi.hienThiNhan3(tinNhan.substring(vt1 + 1, vt2), tinNhan.substring(vt2 + 1, vt3), tinNhan.substring(vt3 + 1));
            return;
        }
        if (tinNhan.startsWith("#4: ")) {
            final int vt1 = tinNhan.indexOf("&");
            final int vt2 = tinNhan.indexOf("&", vt1 + 1);
            final int vt3 = tinNhan.indexOf("&", vt2 + 1);
            this.giaoDienHienThi.hienThiNhan4(tinNhan.substring(vt1 + 1, vt2), tinNhan.substring(vt2 + 1, vt3), tinNhan.substring(vt3 + 1));
            return;
        }
        if (tinNhan.equals("Kiem tra: ")) {
            this.guiTinDenServer("Kiem tra: ");
            System.out.println("kiemtraok");
        }
    }
    
    public void ketNoiLai() {
        JOptionPane.showMessageDialog(null, "L\u1ed7i k\u1ebft n\u1ed1i, \nH\u00e3y kh\u1edfi \u0111\u1ed9ng l\u1ea1i ch\u01b0\u01a1ng tr\u00ecnh", "Th\u00f4ng b\u00e1o", 0);
        this.exitChuongTrinh();
    }
    
    public void exitChuongTrinh() {
        final int chon = JOptionPane.showConfirmDialog(null, "B\u1ea1n c\u00f3 mu\u1ed1n tho\u00e1t ch\u01b0\u01a1ng tr\u00ecnh?", null, 0);
        if (chon == 0) {
            System.exit(1);
        }
    }
    
    @Override
    public void windowClosing(final WindowEvent e) {
        this.exitChuongTrinh();
    }
}

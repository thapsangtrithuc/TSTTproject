// 
// Decompiled by Procyon v0.5.36
// 

package client.main;

import java.awt.event.WindowEvent;
import java.awt.Component;
import javax.swing.JOptionPane;
import client.socket.ThreadSocket;
import client.giaodien.GiaoDienDangNhap;
import client.giaodien.GiaoDienKetNoi;
import client.giaodien.GiaoDienChinh;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;

public class MainHienThiClient extends WindowAdapter
{
    ImageIcon imageIcon;
    public Image image;
    GiaoDienChinh giaoDienChinh;
    GiaoDienKetNoi giaoDienKetNoi;
    GiaoDienDangNhap giaoDienDangNhap;
    ThreadSocket threadSocket;
    public String kieuDuLieu;
    String svHost;
    String ID;
    String pass;
    int svPort;
    
    public MainHienThiClient() {
        this.imageIcon = new ImageIcon(this.getClass().getResource("LogoTSTT.png"));
        this.image = this.imageIcon.getImage();
        this.kieuDuLieu = "UTF-8";
        this.giaoDienChinh = new GiaoDienChinh(this);
        this.giaoDienDangNhap = new GiaoDienDangNhap(this);
        this.giaoDienKetNoi = new GiaoDienKetNoi(this);
        this.threadSocket = new ThreadSocket(this);
        this.giaoDienKetNoi.hienThi();
    }
    
    public static void main(final String[] args) {
        new MainHienThiClient();
    }
    
    public void taoKetNoi(final String svHost, final int svPort) {
        this.svHost = svHost;
        this.svPort = svPort;
        if (this.threadSocket.taoKetNoi(svHost, svPort)) {
            this.giaoDienKetNoi.anDi();
            this.giaoDienDangNhap.hienThi();
        }
    }
    
    public void dangNhap(final String id, final String pass) {
        this.ID = id;
        this.pass = pass;
        this.threadSocket.guiTinDenServer("DangNhap: " + id + " " + pass);
    }
    
    public void ketNoiLai() {
        JOptionPane.showMessageDialog(null, "G\u1eb7p s\u1ef1 c\u1ed1 \u0111\u01b0\u1eddng truy\u1ec1n, \nM\u1eddi k\u1ebft n\u1ed1i l\u1ea1i", null, 0);
        this.exitChuongTrinh();
    }
    
    public void xuLiTinNhan(final String tinNhan) {
        System.out.println(tinNhan);
        if (tinNhan.startsWith("Login: ")) {
            if (tinNhan.equals("Login: OK")) {
                this.giaoDienKetNoi.anDi();
                this.giaoDienDangNhap.anDi();
                this.giaoDienChinh.hienThi();
            }
            else {
                JOptionPane.showMessageDialog(this.giaoDienDangNhap, "Sai m\u1eadt kh\u1ea9u ho\u1eb7c ID", null, 0);
            }
            return;
        }
        if (tinNhan.startsWith("SetDiem: ")) {
            this.giaoDienChinh.thayDoiDiem(tinNhan.substring(tinNhan.indexOf(" ") + 1));
            return;
        }
        if (tinNhan.startsWith("Ten ID: ")) {
            this.giaoDienChinh.thayDoiTen(tinNhan.substring(8));
        }
    }
    
    public void exitChuongTrinh() {
        System.exit(1);
    }
    
    @Override
    public void windowClosing(final WindowEvent e) {
        this.exitChuongTrinh();
    }
}

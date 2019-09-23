package clienthienthi.main;

import clienthienthi.giaodien.GiaoDienDangNhap;
import clienthienthi.giaodien.GiaoDienHienThi;
import clienthienthi.giaodien.GiaoDienKetNoi;
import clienthienthi.socket.ThreadHienThi;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class HienThiMain extends WindowAdapter {
   public ImageIcon icon = new ImageIcon(this.getClass().getResource("LogoTSTT.png"));
   public Image iconChinh;
   GiaoDienKetNoi giaoDienKetNoi;
   GiaoDienDangNhap giaoDienDangNhap;
   GiaoDienHienThi giaoDienHienThi;
   ThreadHienThi threadHienThi;
   public String kieuDuLieu;

   public static void main(String[] args) {
      new HienThiMain();
   }

   public HienThiMain() {
      this.iconChinh = this.icon.getImage();
      this.giaoDienKetNoi = new GiaoDienKetNoi(this);
      this.giaoDienDangNhap = new GiaoDienDangNhap(this);
      this.giaoDienHienThi = new GiaoDienHienThi(this);
      this.threadHienThi = new ThreadHienThi(this);
      this.kieuDuLieu = "UTF-8";
      this.giaoDienKetNoi.hienThi();
   }

   public void dangNhap(String id, String pass) {
      this.guiTinDenServer("DangNhap: " + id + " " + pass);
   }

   public void taoKetNoi(String svHost, int svPort) {
      if (this.threadHienThi.taoKetNoi(svHost, svPort)) {
         this.giaoDienKetNoi.anDi();
         this.giaoDienDangNhap.hienThi();
      }

   }

   public void guiTinDenServer(String tinNhan) {
      this.threadHienThi.guiTinDenServer(tinNhan);
   }

   public void xuLiTinNhan(String tinNhan) {
      if (tinNhan.startsWith("Login: ")) {
         if (tinNhan.equals("Login: OK")) {
            this.giaoDienDangNhap.anDi();
            this.giaoDienHienThi.hienThi();
         } else {
            JOptionPane.showMessageDialog((Component)null, "Sai ID hoặc mật khẩu");
         }

      } else if (tinNhan.startsWith("Bat dau: ")) {
         this.giaoDienHienThi.hienThiThoiGian(" " + tinNhan.substring(9));
      } else if (tinNhan.startsWith("Thoi gian: ")) {
         String hienThi = tinNhan.substring(11);
         if (hienThi.length() < 2) {
            hienThi = "0" + hienThi;
         }

         this.giaoDienHienThi.hienThiThoiGian(" " + hienThi);
      } else if (tinNhan.startsWith("Ket thuc: ")) {
         this.giaoDienHienThi.hienThiThoiGian(" 00 ");
      } else if (tinNhan.startsWith("Reset: ")) {
         this.giaoDienHienThi.resetNhan();
      } else {
         int vt1;
         int vt2;
         int vt3;
         if (tinNhan.startsWith("#1: ")) {
            vt1 = tinNhan.indexOf("&");
            vt2 = tinNhan.indexOf("&", vt1 + 1);
            vt3 = tinNhan.indexOf("&", vt2 + 1);
            this.giaoDienHienThi.hienThiNhan1(tinNhan.substring(vt1 + 1, vt2), tinNhan.substring(vt2 + 1, vt3), tinNhan.substring(vt3 + 1));
         } else if (tinNhan.startsWith("#2: ")) {
            vt1 = tinNhan.indexOf("&");
            vt2 = tinNhan.indexOf("&", vt1 + 1);
            vt3 = tinNhan.indexOf("&", vt2 + 1);
            this.giaoDienHienThi.hienThiNhan2(tinNhan.substring(vt1 + 1, vt2), tinNhan.substring(vt2 + 1, vt3), tinNhan.substring(vt3 + 1));
         } else if (tinNhan.startsWith("#3: ")) {
            vt1 = tinNhan.indexOf("&");
            vt2 = tinNhan.indexOf("&", vt1 + 1);
            vt3 = tinNhan.indexOf("&", vt2 + 1);
            this.giaoDienHienThi.hienThiNhan3(tinNhan.substring(vt1 + 1, vt2), tinNhan.substring(vt2 + 1, vt3), tinNhan.substring(vt3 + 1));
         } else if (tinNhan.startsWith("#4: ")) {
            vt1 = tinNhan.indexOf("&");
            vt2 = tinNhan.indexOf("&", vt1 + 1);
            vt3 = tinNhan.indexOf("&", vt2 + 1);
            this.giaoDienHienThi.hienThiNhan4(tinNhan.substring(vt1 + 1, vt2), tinNhan.substring(vt2 + 1, vt3), tinNhan.substring(vt3 + 1));
         } else if (tinNhan.equals("Kiem tra: ")) {
            this.guiTinDenServer("Kiem tra: ");
            System.out.println("kiemtraok");
         }
      }
   }

   public void ketNoiLai() {
      JOptionPane.showMessageDialog((Component)null, "Lỗi kết nối, \nHãy khởi động lại chương trình", "Thông báo", 0);
      this.exitChuongTrinh();
   }

   public void exitChuongTrinh() {
      int chon = JOptionPane.showConfirmDialog((Component)null, "Bạn có muốn thoát chương trình?", (String)null, 0);
      if (chon == 0) {
         System.exit(1);
      }

   }

   public void windowClosing(WindowEvent e) {
      this.exitChuongTrinh();
   }
}
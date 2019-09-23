package client.main;

import client.giaodien.GiaoDienChinh;
import client.giaodien.GiaoDienDangNhap;
import client.giaodien.GiaoDienKetNoi;
import client.giaodien.GiaoDienVongChoi;
import client.socket.ThreadClient;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class MainClient extends WindowAdapter {
   ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("LogoTSTT.png"));
   public Image iconChinh;
   GiaoDienDangNhap giaoDienDangNhap;
   GiaoDienKetNoi giaoDienKetNoi;
   GiaoDienVongChoi giaoDienVongChoi;
   GiaoDienChinh giaoDienChinh;
   public ThreadClient threadClient;
   public String kieuDuLieu;

   public MainClient() {
      this.iconChinh = this.imageIcon.getImage();
      this.giaoDienDangNhap = new GiaoDienDangNhap(this);
      this.giaoDienKetNoi = new GiaoDienKetNoi(this);
      this.giaoDienVongChoi = new GiaoDienVongChoi(this);
      this.giaoDienChinh = new GiaoDienChinh(this);
      this.threadClient = new ThreadClient(this);
      this.kieuDuLieu = "UTF-8";
   }

   public static void main(String[] args) {
      MainClient mainClient = new MainClient();
      mainClient.batDau();
   }

   public void batDau() {
      this.giaoDienKetNoi.hienThi();
   }

   public void ketNoiMayChu(String svHost, int svPort) {
      if (this.threadClient.taoKetNoi(svHost, svPort)) {
         this.giaoDienKetNoi.anDi();
         this.giaoDienDangNhap.hienThi();
      }

   }

   public void dangNhap(String id, String pass) {
      this.guiTinDenServer("DangNhap: " + id + " " + pass);
   }

   public void xuLiTinNhan(String tinNhan) {
      System.out.println(tinNhan);
      if (tinNhan.startsWith("Login: ")) {
         if (tinNhan.indexOf("OK") != -1) {
            this.giaoDienDangNhap.anDi();
            this.giaoDienChinh.hienThi();
            this.giaoDienVongChoi.hienThi();
         } else {
            JOptionPane.showMessageDialog(this.giaoDienDangNhap, "Sai ID hoặc mật khẩu");
         }
      }

      if (tinNhan.startsWith("Dap an: ")) {
         this.giaoDienVongChoi.hienThiDapAn(tinNhan.substring(tinNhan.indexOf(" ", tinNhan.indexOf(" ") + 1) + 1));
      } else if (tinNhan.startsWith("Bat dau: ")) {
         this.giaoDienVongChoi.hienThiThoiGian(" 00 ");
      } else if (tinNhan.startsWith("Thoi gian: ")) {
         this.giaoDienVongChoi.hienThiThoiGian(tinNhan.substring(11));
      } else if (tinNhan.startsWith("Ket thuc: ")) {
         this.giaoDienVongChoi.checkGuiDapAn = false;
      } else if (tinNhan.startsWith("Reset: ")) {
         this.giaoDienVongChoi.hienThiThoiGian(" 00 ");
         this.giaoDienVongChoi.dapAnTruoc = "";
         this.giaoDienVongChoi.checkGuiDapAn = false;
      } else if (tinNhan.startsWith("Ten ID: ")) {
         this.giaoDienVongChoi.hienThiTenNguoiChuoi(tinNhan.substring(8));
         this.giaoDienChinh.thayDoiTen(tinNhan.substring(8));
      } else if (tinNhan.startsWith("SetDiem: ")) {
         this.giaoDienVongChoi.hienThiDiemSo(tinNhan.substring(9));
         this.giaoDienChinh.thayDoiDiem(tinNhan.substring(9));
      } else if (tinNhan.startsWith("Hien thi: ")) {
         this.giaoDienVongChoi.hienThiDapAn(tinNhan.substring(10));
         System.out.println(tinNhan);
      } else if (tinNhan.equals("Kiem tra: ")) {
         this.guiTinDenServer("Kiem tra: ");
      }
   }

   public void guiTinDenServer(String tinNhan) {
      this.threadClient.guiTinDenServer(tinNhan);
   }

   public void ketNoiLai() {
      JOptionPane.showMessageDialog((Component)null, "Gặp sự cố đường truyền,\n Hãy khởi động lại phần mềm?", "Lỗi", 0);
      this.exitChuongTrinh();
   }

   public void exitChuongTrinh() {
      String st = JOptionPane.showInputDialog((Component)null, "Nhập EXIT để thoát...");
      if (st.equals("EXIT")) {
         System.exit(1);
      }

   }

   public void windowClosing(WindowEvent e) {
      this.exitChuongTrinh();
   }
}
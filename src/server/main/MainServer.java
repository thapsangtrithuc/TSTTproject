package server.main;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import server.giaodien.GiaoDienChinh;
import server.giaodien.GiaoDienTaoCong;
import server.socket.TaoServer;
import server.socket.ThreadServer;

public class MainServer extends WindowAdapter implements Runnable {
   ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("LogoTSTT.png"));
   public Image iconChinh;
   public TaoServer taoServer;
   GiaoDienTaoCong giaoDienTaoCong;
   GiaoDienChinh giaoDienChinh;
   public String linkFile;
   Writer writer;
   Vector<ThreadServer> threadServers;
   boolean[] checkThreadServers;
   int gioiHanSoClient;
   int[] idDangNhapTuongUng;
   int soLuongThread;
   ThreadServer threadHienThi;
   boolean checkThreadHienThi;
   boolean[] daGuiTin;
   boolean daGuiTinHienThi;
   public String kieuDuLieu;
   public String[] idDangNhap;
   public int gioiHanSoID;
   public String[] passDangNhap;
   public String[] tenNguoiChoi;
   public int[] clientTuongUng;
   boolean[] tinhTrangDangNhap;
   boolean[] loaiDangNhap;
   public long[] thoiGianCuoiCung;
   String[] cauTraLoi;
   String[] diemSo;
   public String idHienThi;
   public String passHienThi;
   boolean tinhTrangHienThi;
   Thread threadHienThiMay;
   public int soCauHoi;
   long thoiGianBatDau;

   public static void main(String[] args) {
      MainServer mainServer = new MainServer();
      mainServer.batDau();
   }

   public MainServer() {
      this.iconChinh = this.imageIcon.getImage();
      this.taoServer = new TaoServer(this);
      this.giaoDienTaoCong = new GiaoDienTaoCong(this);
      this.linkFile = "e:/log.txt";
      this.threadServers = new Vector();
      this.gioiHanSoClient = 10;
      this.soLuongThread = 0;
      this.checkThreadHienThi = false;
      this.daGuiTin = new boolean[10];
      this.daGuiTinHienThi = false;
      this.kieuDuLieu = "UTF-8";
      this.idDangNhap = new String[]{"user01", "user02", "user03", "user04", "userhienthi01", "userhienthi02", "userhienthi03", "userhienthi04"};
      this.gioiHanSoID = 8;
      this.passDangNhap = new String[]{"pass01", "pass02", "pass03", "pass04", "passht01", "passht02", "passht03", "passht04"};
      this.tenNguoiChoi = new String[this.gioiHanSoID + 5];
      this.tinhTrangDangNhap = new boolean[10];
      this.loaiDangNhap = new boolean[]{true, true, true, true, false, false, false, false, false, false};
      this.thoiGianCuoiCung = new long[this.gioiHanSoID + 5];
      this.cauTraLoi = new String[this.gioiHanSoID + 5];
      this.idHienThi = "userhienthi";
      this.passHienThi = "passhienthi";
      this.tinhTrangHienThi = false;
      this.threadHienThiMay = new Thread(this);
      this.soCauHoi = 0;
      this.thoiGianBatDau = 0L;
      this.checkThreadServers = new boolean[this.gioiHanSoClient + 1];
      this.idDangNhapTuongUng = new int[this.gioiHanSoClient];
      this.clientTuongUng = new int[this.gioiHanSoID];

      int i;
      for(i = 0; i < this.gioiHanSoClient; ++i) {
         this.checkThreadServers[i] = false;
      }

      for(i = 0; i < this.gioiHanSoClient; ++i) {
         this.idDangNhapTuongUng[i] = this.gioiHanSoID;
      }

      for(i = 0; i < this.gioiHanSoID; ++i) {
         this.thoiGianCuoiCung[i] = 10000000000L;
         this.clientTuongUng[i] = this.gioiHanSoClient;
         this.cauTraLoi[i] = "...";
      }

      this.tenNguoiChoi[0] = "Nguyen Van A";
      this.tenNguoiChoi[1] = "Nguyen Van B";
      this.tenNguoiChoi[2] = "Nguyen Van C";
      this.tenNguoiChoi[3] = "Nguyen Van D";
      this.diemSo = new String[4];

      for(i = 0; i < 4; ++i) {
         this.diemSo[i] = "0";
      }

      this.taoLinkFile();
      this.giaoDienChinh = new GiaoDienChinh(this);
   }

   public void taoLinkFile() {
      try {
         this.writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.linkFile, true), this.kieuDuLieu));
         this.writer.close();
      } catch (IOException var2) {
         this.linkFile = JOptionPane.showInputDialog((Component)null, "Không tìm thấy file log.txt,\nMời nhập lại link file,\n nhập NO để thoát.");
         if (this.linkFile.equals("NO")) {
            this.exitChuongTrinh();
         }

         this.taoLinkFile();
      }

   }

   public void batDau() {
      this.giaoDienTaoCong.hienThi();
   }

   public void khoiChayThreadHienThiMay() {
      this.threadHienThiMay.start();
   }

   public void hienThiGiaoDienChinh() {
      this.giaoDienChinh.hienThi();
   }

   public void suaTrangThaiMay() {
      String trangThai = "";

      for(int i = 0; i < this.gioiHanSoClient; ++i) {
         trangThai = trangThai + "\nClient #" + i + " : " + (this.checkThreadServers[i] ? "đã kết nối" : "chưa kết nối") + " = " + (this.daGuiTin[i] ? "OK" : "Fail");
         trangThai = trangThai + (this.idDangNhapTuongUng[i] < this.gioiHanSoID ? "  " + this.idDangNhap[this.idDangNhapTuongUng[i]] : "  None User");
      }

      trangThai = trangThai + "\n\nHiển Thị :" + (this.checkThreadHienThi ? "đã kết nối" : "chưa kết nối") + " = " + (this.daGuiTinHienThi ? "OK" : "Fail");
      this.giaoDienChinh.hienThiTrangThaiMay(trangThai);
   }

   public boolean taoServer(int port) {
      boolean res = this.taoServer.taoServer(port);
      this.khoiChayThreadHienThiMay();
      return res;
   }

   public void addThreadServer(ThreadServer thread) {
      if (this.soLuongThread < this.gioiHanSoClient) {
         boolean kt = false;

         for(int i = 0; i < this.threadServers.size(); ++i) {
            if (!this.checkThreadServers[i]) {
               this.checkThreadServers[i] = true;
               this.threadServers.setElementAt(thread, i);
               ((ThreadServer)this.threadServers.elementAt(i)).thietLapID(i);
               ((ThreadServer)this.threadServers.elementAt(i)).start();
               ++this.soLuongThread;
               kt = true;
            }
         }

         if (!kt) {
            this.threadServers.addElement(thread);
            this.checkThreadServers[this.threadServers.size() - 1] = true;
            ((ThreadServer)this.threadServers.elementAt(this.threadServers.size() - 1)).thietLapID(this.threadServers.size() - 1);
            ((ThreadServer)this.threadServers.elementAt(this.threadServers.size() - 1)).start();
            kt = true;
         }
      }

   }

   public void suaDiem(String[] diemSo) {
      this.diemSo = diemSo;

      for(int i = 0; i < 4; ++i) {
         if (this.checkThreadServers[this.clientTuongUng[i]]) {
            this.guiTinNhan("SetDiem: " + diemSo[i], this.clientTuongUng[i]);
         }

         if (this.checkThreadServers[this.clientTuongUng[i + 4]]) {
            this.guiTinNhan("SetDiem: " + diemSo[i], this.clientTuongUng[i + 4]);
         }
      }

   }

   public void hienThiTen() {
      for(int i = 0; i < 4; ++i) {
         if (this.checkThreadServers[this.clientTuongUng[i]]) {
            this.guiTinNhan("Ten ID: " + this.tenNguoiChoi[i], this.clientTuongUng[i]);
         }

         if (this.checkThreadServers[this.clientTuongUng[i + 4]]) {
            this.guiTinNhan("Ten ID: " + this.tenNguoiChoi[i], this.clientTuongUng[i + 4]);
         }
      }

   }

   public void loaiMay(int idClient) {
      if (idClient == 28) {
         this.checkThreadHienThi = false;
         this.tinhTrangHienThi = false;
         this.threadHienThi.ngatLuongDuLieu();
      } else {
         if (this.checkThreadServers[idClient]) {
            --this.soLuongThread;
         }

         this.checkThreadServers[idClient] = false;
         this.tinhTrangDangNhap[this.idDangNhapTuongUng[idClient]] = false;
         this.idDangNhapTuongUng[idClient] = this.gioiHanSoID;
         ((ThreadServer)this.threadServers.elementAt(idClient)).ngatLuongDuLieu();
      }

   }

   public void xuLiTinNhan(String tinNhan, int idClient) {
      System.out.println(idClient + " " + tinNhan);
      String id;
      String pass;
      if (tinNhan.startsWith("DangNhap: ")) {
         int viTriCach1 = tinNhan.indexOf(" ");
         int viTriCach2 = tinNhan.indexOf(" ", viTriCach1 + 1);
         id = tinNhan.substring(viTriCach1 + 1, viTriCach2);
         pass = tinNhan.substring(viTriCach2 + 1);
         System.out.println(idClient + " " + id + " " + pass);
         System.out.println(tinNhan);

         for(int i = 0; i < this.gioiHanSoID; ++i) {
            if (this.idDangNhap[i].equals(id) && this.passDangNhap[i].equals(pass) && !this.tinhTrangDangNhap[i]) {
               this.guiTinNhan("Login: OK", idClient);
               this.tinhTrangDangNhap[i] = true;
               this.idDangNhapTuongUng[idClient] = i;
               this.clientTuongUng[i] = idClient;
               this.hienThiTen();
               this.suaDiem(this.diemSo);
               return;
            }
         }

         if (this.idHienThi.equals(id) && this.passHienThi.equals(pass) && !this.tinhTrangHienThi) {
            this.threadHienThi = (ThreadServer)this.threadServers.elementAt(idClient);
            this.threadHienThi.thietLapID(28);
            this.checkThreadHienThi = true;
            this.checkThreadServers[idClient] = false;
            this.tinhTrangHienThi = true;
            this.guiTinNhan("Login: OK", 28);
         } else {
            this.guiTinNhan("Login: FALL", idClient);
         }
      } else if (tinNhan.startsWith("Tra loi: ")) {
         String hienThi = tinNhan.substring(tinNhan.indexOf(" ", tinNhan.indexOf(" ") + 1) + 1);
         Calendar calendar = Calendar.getInstance();
         id = this.tinhToanThoiGian(calendar);
         this.thoiGianCuoiCung[this.idDangNhapTuongUng[idClient]] = this.tinhThoiGian(calendar);
         this.cauTraLoi[this.idDangNhapTuongUng[idClient]] = hienThi;
         this.guiTinNhan("Dap an: " + id + hienThi, idClient);
         pass = "\n #" + String.valueOf(idClient) + " : " + this.idDangNhap[this.idDangNhapTuongUng[idClient]] + " " + id + hienThi;
         this.ghiFile("\n-> Trả lời: " + pass);
         this.giaoDienChinh.hienThiTraLoi(pass);
      }
   }

   public void guiBangRank() {
      boolean[] mang = new boolean[]{true, true, true, true, true};
      long max = 0L;
      int vtmax = -1;
      max = 200000000000L;

      int i;
      for(i = 0; i < 4; ++i) {
         if (this.thoiGianCuoiCung[i] < max && mang[i]) {
            max = this.thoiGianCuoiCung[i];
            vtmax = i;
         }
      }

      String tinNhanGui = "#1: &" + this.tenNguoiChoi[vtmax] + "&" + this.tinhThoiGianTuongDoi(this.thoiGianCuoiCung[vtmax]) + "&" + this.cauTraLoi[vtmax];
      this.guiTinNhan(tinNhanGui, 28);
      this.ghiFile(tinNhanGui);
      System.out.println(this.tenNguoiChoi[vtmax] + " " + this.tinhThoiGianTuongDoi(this.thoiGianCuoiCung[vtmax]));
      mang[vtmax] = false;
      max = 200000000000L;

      for(i = 0; i < 4; ++i) {
         if (this.thoiGianCuoiCung[i] < max && mang[i]) {
            max = this.thoiGianCuoiCung[i];
            vtmax = i;
         }
      }

      tinNhanGui = "#2: &" + this.tenNguoiChoi[vtmax] + "&" + this.tinhThoiGianTuongDoi(this.thoiGianCuoiCung[vtmax]) + "&" + this.cauTraLoi[vtmax];
      this.guiTinNhan(tinNhanGui, 28);
      this.ghiFile(tinNhanGui);
      System.out.println(this.tenNguoiChoi[vtmax] + " " + this.tinhThoiGianTuongDoi(this.thoiGianCuoiCung[vtmax]));
      mang[vtmax] = false;
      max = 200000000000L;

      for(i = 0; i < 4; ++i) {
         if (this.thoiGianCuoiCung[i] < max && mang[i]) {
            max = this.thoiGianCuoiCung[i];
            vtmax = i;
         }
      }

      tinNhanGui = "#3: &" + this.tenNguoiChoi[vtmax] + "&" + this.tinhThoiGianTuongDoi(this.thoiGianCuoiCung[vtmax]) + "&" + this.cauTraLoi[vtmax];
      this.guiTinNhan(tinNhanGui, 28);
      this.ghiFile(tinNhanGui);
      System.out.println(this.tenNguoiChoi[vtmax] + " " + this.tinhThoiGianTuongDoi(this.thoiGianCuoiCung[vtmax]));
      mang[vtmax] = false;
      max = 200000000000L;

      for(i = 0; i < 4; ++i) {
         if (this.thoiGianCuoiCung[i] < max && mang[i]) {
            max = this.thoiGianCuoiCung[i];
            vtmax = i;
         }
      }

      tinNhanGui = "#4: &" + this.tenNguoiChoi[vtmax] + "&" + this.tinhThoiGianTuongDoi(this.thoiGianCuoiCung[vtmax]) + "&" + this.cauTraLoi[vtmax];
      this.guiTinNhan(tinNhanGui, 28);
      this.ghiFile(tinNhanGui);
      System.out.println(this.tenNguoiChoi[vtmax] + " " + this.tinhThoiGianTuongDoi(this.thoiGianCuoiCung[vtmax]));
      mang[vtmax] = false;
   }

   String tinhThoiGianTuongDoi(long thoiGian) {
      long thoiGianRes = thoiGian - this.thoiGianBatDau;
      System.out.println("Thoi gian tuong doi " + thoiGianRes + " " + thoiGian);
      if (thoiGianRes <= 0L) {
         thoiGianRes = 97000L;
      }

      String res = String.valueOf((double)thoiGianRes * 1.0D / 1000.0D);
      if (thoiGianRes > 60000L) {
         res = "ERR";
      }

      return res;
   }

   public void resetBangRank() {
      for(int i = 0; i < 4; ++i) {
         this.thoiGianCuoiCung[i] = 100000000000L;
         this.cauTraLoi[i] = "...";
      }

      this.thoiGianBatDau = 0L;
   }

   public void taoThoiGianBatDau(Calendar calendar) {
      this.thoiGianBatDau = this.tinhThoiGian(calendar);
      String hienThi = "\n ********************\n Câu hỏi số #" + String.valueOf(this.soCauHoi) + " : " + this.tinhToanThoiGian(calendar) + "\n";
      this.giaoDienChinh.hienThiTraLoi(hienThi);
      this.ghiFile(hienThi);
      this.guiTinNhanDenToanBo("Hien thi: Câu hỏi số #" + String.valueOf(this.soCauHoi++));
      System.out.println("thoi gian bat dau: " + this.thoiGianBatDau);
   }

   public boolean guiTinNhan(String tinNhan, int idClient) {
      if (idClient == 28) {
         if (this.checkThreadHienThi) {
            this.threadHienThi.guiDuLieu(tinNhan);
            this.daGuiTinHienThi = true;
            return true;
         } else {
            this.daGuiTinHienThi = false;
            return false;
         }
      } else {
         try {
            if (this.checkThreadServers[idClient]) {
               ((ThreadServer)this.threadServers.elementAt(idClient)).guiDuLieu(tinNhan);
               this.daGuiTin[idClient] = true;
               return true;
            }

            this.daGuiTin[idClient] = false;
         } catch (ArrayIndexOutOfBoundsException var4) {
            JOptionPane.showMessageDialog((Component)null, var4);
         }

         return false;
      }
   }

   public void guiTinNhanDenToanBo(String tinNhan) {
      if (!this.threadHienThiMay.isAlive()) {
         this.threadHienThiMay.start();
      }

      this.guiTinNhan(tinNhan, 28);

      for(int i = 0; i < 10; ++i) {
         this.guiTinNhan(tinNhan, i);
      }

   }

   public String tinhToanThoiGian(Calendar calendar) {
      String thoiGian = "";
      String gio = String.valueOf(calendar.get(10));
      String phut = String.valueOf(calendar.get(12));
      String giay = String.valueOf(calendar.get(13));
      String milis = String.valueOf(calendar.get(14));
      thoiGian = thoiGian + "[ " + gio + ":" + phut + ":" + giay + "." + milis + " ] ";
      return thoiGian;
   }

   public long tinhThoiGian(Calendar calendar) {
      long thoiGian = (long)calendar.get(10);
      thoiGian = thoiGian * 60L + (long)calendar.get(12);
      thoiGian = thoiGian * 60L + (long)calendar.get(13);
      thoiGian = thoiGian * 1000L + (long)calendar.get(14);
      return thoiGian;
   }

   public void ghiFile(String duLieu) {
      try {
         this.writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.linkFile, true), this.kieuDuLieu));
         this.writer.append(duLieu).append("\n");
         this.writer.flush();
         this.writer.close();
      } catch (IOException var3) {
         JOptionPane.showMessageDialog((Component)null, "Có lỗi xảy ra với file log");
         this.taoLinkFile();
         this.ghiFile(duLieu);
      }

   }

   public void exitChuongTrinh() {
      int hoi = JOptionPane.showConfirmDialog((Component)null, "Bạn có muốn thoát chương trình không?", (String)null, 0);
      if (hoi == 0) {
         System.exit(0);
      }

   }

   public void windowClosing(WindowEvent e) {
      this.exitChuongTrinh();
   }

   public void run() {
      while(true) {
         this.guiTinNhanDenToanBo("Kiem tra: ");
         this.suaTrangThaiMay();

         try {
            Thread.sleep(1000L);
         } catch (InterruptedException var2) {
            JOptionPane.showMessageDialog((Component)null, "Lỗi thread hiển thị máy");
         }
      }
   }
}
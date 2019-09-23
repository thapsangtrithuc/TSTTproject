 package server.giaodien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Timer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import server.main.MainServer;

public class GiaoDienChinh extends Frame implements ActionListener {
   private static final long serialVersionUID = 1L;
   public MainServer mainServer;
   JTextArea hienThiTraLoi;
   JTextArea hienThiMayKetNoi;
   JTextField thietLapThoiGian;
   JLabel nguoiChoi1;
   JLabel nguoiChoi2;
   JLabel nguoiChoi3;
   JLabel nguoiChoi4;
   JTextField diemSo1;
   JTextField diemSo2;
   JTextField diemSo3;
   JTextField diemSo4;
   String actionChay = "run";
   String actionCancel = "cancel";
   String actionReset = "reset";
   String actionDiem = "setdiem";
   JLabel thoiGian;
   JScrollPane cuonHienThiTraLoi;
   JScrollPane cuonHienThiMay;
   Border border;
   MenuBar menuBar;
   Menu menuCaiDat;
   Menu menuHelp;
   MenuItem itemUser;
   MenuItem itemExit;
   MenuItem itemHelp;
   public GiaoDienTuyChon giaoDienTuyChon;
   int phut;
   int giay;
   boolean daBatDau;
   boolean daKetThuc;
   boolean daReset;
   int thoiGianChay;
   Timer timer;
   Image iconMain;

   public GiaoDienChinh(MainServer mainServer) {
      super("Thắp sáng tri thức - Server");
      this.border = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 10);
      this.menuBar = new MenuBar();
      this.phut = 0;
      this.giay = 0;
      this.daBatDau = false;
      this.daKetThuc = false;
      this.daReset = true;
      this.thoiGianChay = 0;
      this.mainServer = mainServer;
      this.taoMenuBar();
      this.setMenuBar(this.menuBar);
      this.add(this.taoPanelChinh());
      this.setSize(1000, 700);
      this.addWindowListener(mainServer);
      this.setIconImage(mainServer.iconChinh);
   }

   void taoMenuBar() {
      this.menuCaiDat = new Menu("Cài đặt");
      this.menuHelp = new Menu("Help?");
      this.menuBar.add(this.menuCaiDat);
      this.menuBar.add(this.menuHelp);
      this.itemUser = new MenuItem("Client-User");
      this.itemExit = new MenuItem("Exit");
      this.itemHelp = new MenuItem("Help");
      this.itemUser.addActionListener(this);
      this.itemExit.addActionListener(this);
      this.itemHelp.addActionListener(this);
      this.menuHelp.add(this.itemHelp);
      this.menuCaiDat.add(this.itemUser);
      this.menuCaiDat.add(this.itemExit);
   }

   JPanel taoPanelChinh() {
      JPanel panel = new JPanel(new BorderLayout(10, 10));
      panel.add(this.taoPanelCenter(), "Center");
      panel.add(this.taoPanelDiem(), "North");
      return panel;
   }

   JPanel taoPanelDiem() {
      JPanel panel = new JPanel(new GridLayout(5, 2));
      panel.add(this.nguoiChoi1 = new JLabel(" " + this.mainServer.idDangNhap[0] + " = " + this.mainServer.tenNguoiChoi[0]));
      panel.add(this.diemSo1 = new JTextField("000"));
      panel.add(this.nguoiChoi2 = new JLabel(" " + this.mainServer.idDangNhap[1] + " = " + this.mainServer.tenNguoiChoi[1]));
      panel.add(this.diemSo2 = new JTextField("000"));
      panel.add(this.nguoiChoi3 = new JLabel(" " + this.mainServer.idDangNhap[2] + " = " + this.mainServer.tenNguoiChoi[2]));
      panel.add(this.diemSo3 = new JTextField("000"));
      panel.add(this.nguoiChoi4 = new JLabel(" " + this.mainServer.idDangNhap[3] + " = " + this.mainServer.tenNguoiChoi[3]));
      panel.add(this.diemSo4 = new JTextField("000"));
      JButton button = new JButton("Sửa Điểm");
      button.addActionListener(this);
      button.setActionCommand(this.actionDiem);
      panel.add(button);
      this.nguoiChoi1.setFont(new Font("Arial", 3, 20));
      this.diemSo1.setFont(new Font("Times New Roman", 1, 20));
      this.nguoiChoi2.setFont(new Font("Arial", 3, 20));
      this.diemSo2.setFont(new Font("Times New Roman", 1, 20));
      this.nguoiChoi3.setFont(new Font("Arial", 3, 20));
      this.diemSo3.setFont(new Font("Times New Roman", 1, 20));
      this.nguoiChoi4.setFont(new Font("Arial", 3, 20));
      this.diemSo4.setFont(new Font("Times New Roman", 1, 20));
      return panel;
   }

   JPanel taoPanelCenter() {
      JPanel panel = new JPanel(new GridLayout(1, 2));
      panel.add(this.taoPanelTrai());
      panel.add(this.taoPanelPhai());
      return panel;
   }

   JPanel taoPanelPhai() {
      JPanel panel = new JPanel(new BorderLayout());
      this.hienThiTraLoi = new JTextArea();
      this.hienThiTraLoi.setFont(new Font("Arial", 0, 15));
      this.hienThiTraLoi.setEditable(false);
      this.cuonHienThiTraLoi = new JScrollPane(this.hienThiTraLoi);
      panel.setBorder(this.border);
      panel.add(this.cuonHienThiTraLoi, "Center");
      panel.add(this.cuonHienThiTraLoi.getVerticalScrollBar(), "East");
      panel.add(this.cuonHienThiTraLoi.getHorizontalScrollBar(), "South");
      return panel;
   }

   JPanel taoPanelTrai() {
      JPanel panel = new JPanel(new GridLayout(2, 1));
      JPanel panelHienThiMay = new JPanel(new BorderLayout());
      this.hienThiMayKetNoi = new JTextArea("Chưa có máy kết nối...");
      this.hienThiMayKetNoi.setFont(new Font("Times New Roman", 0, 15));
      this.hienThiMayKetNoi.setEditable(false);
      this.cuonHienThiMay = new JScrollPane(this.hienThiMayKetNoi);
      panelHienThiMay.add(this.cuonHienThiMay, "Center");
      panelHienThiMay.add(this.cuonHienThiMay.getVerticalScrollBar(), "East");
      panel.add(panelHienThiMay);
      panel.add(this.taoPanelThoiGian());
      return panel;
   }

   JPanel taoPanelThoiGian() {
      JPanel panel = new JPanel(new GridLayout(1, 2));
      JPanel panelTrai = new JPanel(new GridLayout(2, 1));
      this.thietLapThoiGian = new JTextField();
      this.thietLapThoiGian.setBorder(this.border);
      this.thietLapThoiGian.setActionCommand(this.actionChay);
      this.thietLapThoiGian.setText("10");
      this.thietLapThoiGian.addActionListener(this);
      this.thietLapThoiGian.setFont(new Font("Arial", 2, 50));
      this.thoiGian = new JLabel(" 00 ");
      this.thoiGian.setSize(300, 20);
      this.thoiGian.setBorder(this.border);
      this.thoiGian.setFont(new Font("Times New Roman", 1, 50));
      panelTrai.setBorder(this.border);
      panelTrai.add(this.thoiGian);
      panelTrai.add(this.thietLapThoiGian);
      panel.add(panelTrai);
      JPanel panelPhai = new JPanel(new GridLayout(3, 1));
      panelPhai.setBorder(this.border);
      panelPhai.add(this.taoNut("Bắt Đầu.", this.actionChay));
      panelPhai.add(this.taoNut("Kết thúc", this.actionCancel));
      panelPhai.add(this.taoNut("Reset", this.actionReset));
      panel.add(panelPhai);
      return panel;
   }

   JLabel taoNhan(String ten) {
      JLabel label = new JLabel(ten);
      return label;
   }

   JButton taoNut(String tenNut, String action) {
      JButton nut = new JButton(tenNut);
      nut.setActionCommand(action);
      nut.addActionListener(this);
      return nut;
   }

   public void hienThiTrangThaiMay(String tinNhan) {
      this.hienThiMayKetNoi.setText(tinNhan);
   }

   public void hienThiTraLoi(String tinNhan) {
      this.hienThiTraLoi.append(tinNhan);
   }

   public void xoaTraLoi() {
      this.hienThiTraLoi.setText("");
   }

   public void hienThiNguoiChuoi() {
      this.nguoiChoi1.setText(" " + this.mainServer.idDangNhap[0] + " = " + this.mainServer.tenNguoiChoi[0]);
      this.nguoiChoi2.setText(" " + this.mainServer.idDangNhap[1] + " = " + this.mainServer.tenNguoiChoi[1]);
      this.nguoiChoi3.setText(" " + this.mainServer.idDangNhap[2] + " = " + this.mainServer.tenNguoiChoi[2]);
      this.nguoiChoi4.setText(" " + this.mainServer.idDangNhap[3] + " = " + this.mainServer.tenNguoiChoi[3]);
      this.mainServer.hienThiTen();
   }

   public void hienThi() {
      this.setVisible(true);
   }

   public void anDi() {
      this.setVisible(false);
   }

   private void chayDongHo(int thoiGian) {
      this.tangThoiGian();
      if (thoiGian != 0) {
         this.timer = new Timer();
         this.timer.purge();
         this.timer.schedule(new DongHoSuKien(this, thoiGian), 1000L, 1000L);
      }
   }

   public void tangThoiGian() {
      ++this.giay;
      String time = String.valueOf(this.giay);
      this.thoiGian.setText(time);
      if (this.phut == 0 && this.giay == 0) {
         this.mainServer.guiTinNhanDenToanBo("Bat Dau: ");
      } else {
         this.mainServer.guiTinNhanDenToanBo("Thoi gian: " + time);
      }

   }

   public void inKetQua() {
      this.mainServer.guiTinNhanDenToanBo("Ket thuc: ");
      this.mainServer.guiBangRank();
   }

   public void actionPerformed(ActionEvent e) {
      if (e.getActionCommand().equals(this.actionChay) && this.daReset) {
         this.daBatDau = true;
         this.daReset = false;
         this.daKetThuc = false;
         this.mainServer.taoThoiGianBatDau(Calendar.getInstance());
         if (this.thoiGianChay == 0) {
            Matcher matcher = Pattern.compile("\\d*").matcher(this.thietLapThoiGian.getText());
            if (!matcher.matches()) {
               JOptionPane.showMessageDialog((Component)null, "Nhập lại thời gian");
               return;
            }

            this.thoiGianChay = Integer.parseInt(this.thietLapThoiGian.getText());
            this.phut = 0;
            this.giay = -1;
         }

         if (this.thoiGianChay < 0) {
            this.thoiGianChay = 0;
         }

         this.chayDongHo(this.thoiGianChay);
         this.thoiGianChay = 0;
      }

      if (e.getActionCommand().equals(this.actionReset) && this.daKetThuc) {
         this.daBatDau = false;
         this.daKetThuc = false;
         this.daReset = true;
         this.thoiGian.setText(" 00 ");
         this.mainServer.guiTinNhanDenToanBo("Reset: ");
         this.mainServer.resetBangRank();
      }

      if (e.getActionCommand().equals(this.actionCancel) && this.daBatDau) {
         this.daKetThuc = true;
         this.daReset = false;
         this.daBatDau = false;
         this.thoiGian.setText(this.thoiGian.getText() + "  Gián đoạn");
         this.timer.cancel();
         this.mainServer.guiTinNhanDenToanBo("Ket thuc: ");
      }

      if (e.getSource() == this.itemExit) {
         this.mainServer.exitChuongTrinh();
      }

      if (e.getSource() == this.itemUser) {
         this.anDi();
         this.giaoDienTuyChon = new GiaoDienTuyChon(this);
         this.giaoDienTuyChon.hienThi();
      } else if (e.getActionCommand().equalsIgnoreCase("Help")) {
         JOptionPane.showMessageDialog((Component)null, "Mọi thắc mắc xin liên hệ: nc.dinh15t2@gmail.com");
      } else if (e.getActionCommand().equals(this.actionDiem)) {
         String[] diemSo = new String[]{this.diemSo1.getText(), this.diemSo2.getText(), this.diemSo3.getText(), this.diemSo4.getText()};
         this.mainServer.suaDiem(diemSo);
      }
   }
}
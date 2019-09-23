package clienthienthi.giaodien;

import clienthienthi.main.HienThiMain;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class GiaoDienHienThi extends Frame {
   private static final long serialVersionUID = 1L;
   HienThiMain hienThiMain;
   String user = "  #USER";
   String time00 = " 00 ";
   String answer = "   ANSWER";
   JLabel tenNguoi1;
   JLabel tenNguoi2;
   JLabel tenNguoi3;
   JLabel tenNguoi4;
   JLabel thoiGian1;
   JLabel thoiGian2;
   JLabel thoiGian3;
   JLabel thoiGian4;
   JLabel dapAn1;
   JLabel dapAn2;
   JLabel dapAn3;
   JLabel dapAn4;
   JLabel thoiGian;
   JLabel nhanAnh;
   Border borderTenNguoi;
   Border borderThoiGian;
   Border borderDongHo;
   Border borderAnh;
   int chieuRong;
   int chieuCao;

   public GiaoDienHienThi(HienThiMain hienThiMain) {
      super("Thắp Sáng Tri Thức - Hiển Thị.");
      this.tenNguoi1 = new JLabel(this.user);
      this.tenNguoi2 = new JLabel(this.user);
      this.tenNguoi3 = new JLabel(this.user);
      this.tenNguoi4 = new JLabel(this.user);
      this.thoiGian1 = new JLabel(this.time00, 0);
      this.thoiGian2 = new JLabel(this.time00, 0);
      this.thoiGian3 = new JLabel(this.time00, 0);
      this.thoiGian4 = new JLabel(this.time00, 0);
      this.dapAn1 = new JLabel(this.answer);
      this.dapAn2 = new JLabel(this.answer);
      this.dapAn3 = new JLabel(this.answer);
      this.dapAn4 = new JLabel(this.answer);
      this.borderTenNguoi = BorderFactory.createLineBorder(Color.BLACK, 5);
      this.borderThoiGian = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 5);
      this.borderDongHo = BorderFactory.createLineBorder(Color.BLUE, 10);
      this.borderAnh = BorderFactory.createLineBorder(Color.ORANGE, 15);
      this.hienThiMain = hienThiMain;
      this.setSize(1000, 700);
      this.setIconImage(hienThiMain.iconChinh);
      this.addWindowListener(hienThiMain);
      this.add(this.taoPanelChinh());
   }

   JPanel taoPanelChinh() {
      JPanel panel = new JPanel(new BorderLayout(0, 10));
      panel.add(this.taoPanelTrai(), "West");
      panel.add(this.taoPanelCenter(), "Center");
      return panel;
   }

   JPanel taoPanelTrai() {
      JPanel panel = new JPanel(new GridLayout(2, 1));
      this.nhanAnh = new JLabel();
      this.nhanAnh.setIcon(new ImageIcon(this.hienThiMain.iconChinh.getScaledInstance(300, 300, 4)));
      this.nhanAnh.setBorder(this.borderAnh);
      panel.add(this.nhanAnh);
      this.thoiGian = new JLabel(" 00 ", 0);
      this.thoiGian.setBorder(this.borderDongHo);
      this.thoiGian.setFont(new Font("Arial", 1, 200));
      panel.add(this.thoiGian);
      return panel;
   }

   JPanel taoPanelCenter() {
      JPanel panel = new JPanel(new GridLayout(4, 1));
      panel.add(this.taoPanelCon(this.tenNguoi1, this.thoiGian1, this.dapAn1));
      panel.add(this.taoPanelCon(this.tenNguoi2, this.thoiGian2, this.dapAn2));
      panel.add(this.taoPanelCon(this.tenNguoi3, this.thoiGian3, this.dapAn3));
      panel.add(this.taoPanelCon(this.tenNguoi4, this.thoiGian4, this.dapAn4));
      panel.setBorder(this.borderTenNguoi);
      return panel;
   }

   JPanel taoPanelCon(JLabel tenNguoi, JLabel thoiGian, JLabel dapAn) {
      JPanel panel = new JPanel(new BorderLayout());
      tenNguoi.setFont(new Font("Times New Roman", 2, 40));
      tenNguoi.setBorder(this.borderTenNguoi);
      panel.add(tenNguoi, "North");
      thoiGian.setFont(new Font("Arial", 1, 40));
      thoiGian.setBorder(this.borderThoiGian);
      panel.add(thoiGian, "West");
      dapAn.setFont(new Font("Times New Roman", 3, 35));
      dapAn.setBorder(this.borderThoiGian);
      panel.add(dapAn, "Center");
      panel.setBorder(this.borderTenNguoi);
      return panel;
   }

   public void hienThiThoiGian(String thoiGian) {
      this.thoiGian.setText(thoiGian);
   }

   public void resetNhan() {
      this.hienThiNhan1(" #USER NAME 1", " 00 ", " ANSWER...");
      this.hienThiNhan2(" #USER NAME 2", " 00 ", " ANSWER...");
      this.hienThiNhan3(" #USER NAME 3", " 00 ", " ANSWER...");
      this.hienThiNhan4(" #USER NAME 4", " 00 ", " ANSWER...");
      this.hienThiThoiGian(" 00 ");
   }

   public void hienThiNhan1(String tenNguoi, String thoiGian, String dapAn) {
      this.tenNguoi1.setText("  " + tenNguoi);
      this.thoiGian1.setText(" " + thoiGian);
      this.dapAn1.setText("  " + dapAn);
   }

   public void hienThiNhan2(String tenNguoi, String thoiGian, String dapAn) {
      this.tenNguoi2.setText("  " + tenNguoi);
      this.thoiGian2.setText(" " + thoiGian);
      this.dapAn2.setText("  " + dapAn);
   }

   public void hienThiNhan3(String tenNguoi, String thoiGian, String dapAn) {
      this.tenNguoi3.setText("  " + tenNguoi);
      this.thoiGian3.setText(" " + thoiGian);
      this.dapAn3.setText("  " + dapAn);
   }

   public void hienThiNhan4(String tenNguoi, String thoiGian, String dapAn) {
      this.tenNguoi4.setText("  " + tenNguoi);
      this.thoiGian4.setText(" " + thoiGian);
      this.dapAn4.setText("  " + dapAn);
   }

   public void hienThi() {
      this.setVisible(true);
   }

   public void anDi() {
      this.setVisible(false);
   }
}
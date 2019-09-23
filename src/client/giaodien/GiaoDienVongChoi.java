package client.giaodien;

import client.main.MainClient;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class GiaoDienVongChoi extends Frame implements ActionListener {
   private static final long serialVersionUID = 1L;
   MainClient mainClient;
   JLabel nhanTenNguoiChoi;
   JLabel nhanThoiGian;
   JLabel nhanDiemSo;
   int kichCoTen = 23;
   int kichCoThoiGian = 23;
   int kichCoDiemSo = 23;
   int kichCoNhap = 20;
   int kichCoHienThi = 18;
   String font = "Arial";
   JTextField nhapDapAn;
   JTextArea hienThiDapAn;
   JButton guiDapAn;
   Border border;
   Border border2;
   String actionGui;
   public String dapAnTruoc;
   public boolean checkGuiDapAn;

   public GiaoDienVongChoi(MainClient mainClient) {
      super("Thắp Sáng Tri Thức - Client");
      this.border = BorderFactory.createLineBorder(Color.ORANGE, 5);
      this.border2 = BorderFactory.createLineBorder(Color.DARK_GRAY, 20);
      this.actionGui = "guidapan";
      this.dapAnTruoc = "";
      this.mainClient = mainClient;
      this.addWindowListener(mainClient);
      this.setSize(this.getMaximumSize());
      this.addWindowListener(mainClient);
      this.add(this.taoPanelChinh());
      this.setIconImage(mainClient.iconChinh);
      this.checkGuiDapAn = false;
   }

   JPanel taoPanelChinh() {
      JPanel panel = new JPanel(new BorderLayout(10, 10));
      panel.setBackground(Color.DARK_GRAY);
      panel.add(this.taoPanelTop(), "North");
      panel.add(this.taoPanelCenter(), "Center");
      panel.add(this.taoPanelBot(), "South");
      return panel;
   }

   JPanel taoPanelTop() {
      JPanel panel = new JPanel(new GridLayout(1, 2));
      this.nhanTenNguoiChoi = new JLabel("TÊN NGƯỜI CHƠI;");
      this.nhanTenNguoiChoi.setBorder(this.border);
      this.nhanTenNguoiChoi.setFont(new Font("Times New Roman", 3, this.kichCoTen));
      panel.add(this.nhanTenNguoiChoi);
      JPanel panelRight = new JPanel(new GridLayout(1, 2));
      this.nhanDiemSo = new JLabel("######");
      this.nhanDiemSo.setFont(new Font(this.font, 1, this.kichCoDiemSo));
      this.nhanThoiGian = new JLabel("THỜI GIAN: 00");
      this.nhanThoiGian.setFont(new Font(this.font, 1, this.kichCoThoiGian));
      panelRight.add(this.nhanDiemSo);
      panelRight.add(this.nhanThoiGian);
      panelRight.setBorder(this.border);
      panel.add(panelRight);
      return panel;
   }

   JPanel taoPanelCenter() {
      JPanel panel = new JPanel(new BorderLayout());
      this.hienThiDapAn = new JTextArea();
      JScrollPane scrollPane = new JScrollPane(this.hienThiDapAn);
      this.hienThiDapAn.setFont(new Font("Arial", 0, this.kichCoHienThi));
      this.hienThiDapAn.setEditable(false);
      panel.setBorder(this.border2);
      panel.add(scrollPane, "Center");
      panel.add(scrollPane.getVerticalScrollBar(), "East");
      panel.add(scrollPane.getHorizontalScrollBar(), "South");
      return panel;
   }

   JPanel taoPanelBot() {
      JPanel panel = new JPanel(new BorderLayout(10, 0));
      this.nhapDapAn = new JTextField();
      this.nhapDapAn.setFont(new Font(this.font, 2, this.kichCoNhap));
      this.nhapDapAn.setActionCommand(this.actionGui);
      this.nhapDapAn.addActionListener(this);
      panel.add(this.nhapDapAn, "Center");
      this.guiDapAn = new JButton("Gửi Đáp Án");
      this.guiDapAn.setActionCommand(this.actionGui);
      this.guiDapAn.addActionListener(this);
      this.guiDapAn.setFont(new Font(this.font, 1, this.kichCoNhap));
      panel.add(this.guiDapAn, "East");
      panel.setBackground(Color.DARK_GRAY);
      panel.setBorder(this.border2);
      return panel;
   }

   public void hienThiThoiGian(String thoiGian) {
      this.checkGuiDapAn = true;
      this.nhanThoiGian.setText("THỜI GIAN: " + thoiGian);
   }

   public void hienThiDapAn(String tinNhan) {
      this.hienThiDapAn.append("\n" + tinNhan);
   }

   public void hienThiTenNguoiChuoi(String tenNguoiChoi) {
      this.nhanTenNguoiChoi.setText(tenNguoiChoi);
   }

   public void hienThiDiemSo(String diemSo) {
      this.nhanDiemSo.setText("Điểm Số: " + diemSo);
   }

   public void hienThi() {
      this.setVisible(true);
   }

   public void anDi() {
      this.setVisible(false);
   }

   public void actionPerformed(ActionEvent ev) {
      if (ev.getActionCommand().equals(this.actionGui)) {
         if (this.checkGuiDapAn) {
            System.out.println("check true");
            if (!this.dapAnTruoc.equals(this.nhapDapAn.getText())) {
               this.dapAnTruoc = this.nhapDapAn.getText();
               this.mainClient.guiTinDenServer("Tra loi: " + this.dapAnTruoc);
            }
         } else {
            System.out.println("check false");
         }

      }
   }
}
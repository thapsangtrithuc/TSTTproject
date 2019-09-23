package client.giaodien;

import client.main.MainClient;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class GiaoDienChinh extends Frame implements ActionListener {
   private static final long serialVersionUID = 1L;
   JLabel nhanTen;
   JLabel nhanDiem;
   MainClient mainHienThiClient;

   public GiaoDienChinh(MainClient mainHienThiClient) {
      super("Hiển thị điểm");
      this.mainHienThiClient = mainHienThiClient;
      this.addWindowListener(mainHienThiClient);
      this.setSize(this.getMaximumSize());
      this.setIconImage(mainHienThiClient.iconChinh);
      this.add(this.taoPanelChinh());
   }

   JPanel taoPanelChinh() {
      JPanel panel = new JPanel(new BorderLayout(10, 10));
      this.nhanTen = new JLabel("xxx", 0);
      Border border = BorderFactory.createLineBorder(Color.BLUE, 15);
      this.nhanTen.setFont(new Font("Times New Roman", 3, 150));
      JPanel panelTren = new JPanel();
      panelTren.setBackground(Color.decode("#FFFF99"));
      panelTren.add(this.nhanTen);
      panelTren.setBorder(border);
      panel.add(panelTren, "North");
      this.nhanDiem = new JLabel("0", 0);
      this.nhanDiem.setFont(new Font("Arial", 1, 500));
      panel.add(this.nhanDiem, "Center");
      return panel;
   }

   public void hienThi() {
      this.setVisible(true);
   }

   public void anDi() {
      this.setVisible(false);
   }

   public void thayDoiTen(String ten) {
      this.nhanTen.setText(ten);
   }

   public void thayDoiDiem(String diem) {
      this.nhanDiem.setText(diem);
   }

   public void actionPerformed(ActionEvent e) {
   }
}
package client.giaodien;

import client.main.MainClient;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GiaoDienDangNhap extends Frame implements ActionListener {
   private static final long serialVersionUID = 1L;
   MainClient mainClient;
   JTextField nhapID;
   JTextField nhapPass;
   String actionDangNhap = "dangnhap";

   public GiaoDienDangNhap(MainClient mainClient) {
      super("Thắp sáng tri thức - Client");
      this.mainClient = mainClient;
      this.setSize(300, 200);
      this.addWindowListener(mainClient);
      this.add(this.taoPanelChinh());
      this.setIconImage(mainClient.iconChinh);
   }

   JPanel taoPanelChinh() {
      JPanel panel = new JPanel(new BorderLayout());
      panel.add(this.taoPanelTop(), "North");
      panel.add(this.taoPanelBot(), "South");
      panel.add(this.taoPanelTrai(), "West");
      panel.add(this.taoPanelCenter(), "Center");
      return panel;
   }

   JPanel taoPanelTop() {
      JPanel panel = new JPanel();
      panel.add(this.taoNhan("Đăng nhập tài khoản máy chủ: "));
      return panel;
   }

   JPanel taoPanelBot() {
      JPanel panel = new JPanel();
      panel.add(this.taoNut("Đăng nhập.", this.actionDangNhap));
      return panel;
   }

   JPanel taoPanelTrai() {
      JPanel panel = new JPanel(new GridLayout(2, 1));
      panel.add(this.taoNhan("Nhập ID: "));
      panel.add(this.taoNhan("Nhập Pass: "));
      return panel;
   }

   JPanel taoPanelCenter() {
      JPanel panel = new JPanel(new GridLayout(2, 1));
      this.nhapID = new JTextField();
      this.nhapPass = new JTextField();
      panel.add(this.nhapID);
      panel.add(this.nhapPass);
      return panel;
   }

   JLabel taoNhan(String tenNhan) {
      JLabel label = new JLabel(tenNhan);
      return label;
   }

   JButton taoNut(String tenNut, String action) {
      JButton nut = new JButton(tenNut);
      nut.setActionCommand(action);
      nut.addActionListener(this);
      return nut;
   }

   public void hienThi() {
      this.setVisible(true);
   }

   public void anDi() {
      this.setVisible(false);
   }

   public void actionPerformed(ActionEvent e) {
      if (e.getActionCommand().equals(this.actionDangNhap)) {
         this.mainClient.dangNhap(this.nhapID.getText(), this.nhapPass.getText());
      }

   }
}
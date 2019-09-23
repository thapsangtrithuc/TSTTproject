package clienthienthi.giaodien;

import clienthienthi.main.HienThiMain;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GiaoDienKetNoi extends Frame implements ActionListener {
   private static final long serialVersionUID = 1L;
   HienThiMain hienThiMain;
   JTextField nhapPort;
   JTextField nhapHost;
   String actionKetNoi = "ketnoi";

   public GiaoDienKetNoi(HienThiMain hienThiMain) {
      super("Thắp sáng tri thức - Hiển thị.");
      this.setSize(300, 200);
      this.add(this.taoPanelChinh());
      this.hienThiMain = hienThiMain;
      this.addWindowListener(hienThiMain);
      this.setIconImage(hienThiMain.iconChinh);
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
      panel.add(this.taoNhan("Kết nối đến máy chủ: "));
      return panel;
   }

   JPanel taoPanelBot() {
      JPanel panel = new JPanel();
      panel.add(this.taoNut("Kết Nối.", this.actionKetNoi));
      return panel;
   }

   JPanel taoPanelTrai() {
      JPanel panel = new JPanel(new GridLayout(2, 1));
      panel.add(this.taoNhan("Nhap Host: "));
      panel.add(this.taoNhan("Nhập Port: "));
      return panel;
   }

   JPanel taoPanelCenter() {
      JPanel panel = new JPanel(new GridLayout(2, 1));
      this.nhapHost = new JTextField();
      this.nhapPort = new JTextField();
      this.nhapHost.setText("localhost");
      this.nhapPort.setText("7777");
      panel.add(this.nhapHost);
      panel.add(this.nhapPort);
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
      if (e.getActionCommand().equals(this.actionKetNoi)) {
         Matcher matcher = Pattern.compile("\\d*").matcher(this.nhapPort.getText());
         if (matcher.matches()) {
            this.hienThiMain.taoKetNoi(this.nhapHost.getText(), Integer.parseInt(this.nhapPort.getText()));
         } else {
            JOptionPane.showMessageDialog((Component)null, "Nhập port chưa đúng định dạng");
         }
      }

   }
}
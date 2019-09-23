package server.giaodien;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import server.main.MainServer;

public class GiaoDienTaoCong extends Frame implements ActionListener {
   private static final long serialVersionUID = 1L;
   MainServer mainServer;
   JTextField nhapPort;
   JButton nutTaoCong;
   String actionTaoCong = "taoCong";
   JLabel thongBao;

   public GiaoDienTaoCong(MainServer mainServer) {
      super("Thắp Sáng Tri Thức - Server.");
      this.mainServer = mainServer;
      this.add(this.taoPanelChinh());
      this.setSize(300, 200);
      this.addWindowListener(mainServer);
      this.setIconImage(mainServer.iconChinh);
   }

   JPanel taoPanelChinh() {
      JPanel panel = new JPanel(new GridLayout(3, 1));
      JLabel nhanTieuDe = new JLabel("Nhập cổng kết nối: ");
      this.nhapPort = new JTextField();
      this.nhapPort.setText("7777");
      this.nhapPort.addActionListener(this);
      panel.add(nhanTieuDe);
      panel.add(this.nhapPort);
      JPanel panelNut = new JPanel(new FlowLayout());
      this.nutTaoCong = new JButton("Tạo cổng.");
      this.nutTaoCong.setActionCommand(this.actionTaoCong);
      this.nutTaoCong.addActionListener(this);
      panelNut.add(this.nutTaoCong);
      this.thongBao = new JLabel("");
      panelNut.add(this.thongBao);
      panel.add(panelNut);
      return panel;
   }

   public void hienThi() {
      this.setVisible(true);
   }

   public void anDi() {
      this.setVisible(false);
   }

   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == this.nhapPort || e.getActionCommand().equals(this.actionTaoCong)) {
         Matcher matcher = Pattern.compile("\\d*").matcher(this.nhapPort.getText());
         if (matcher.matches()) {
            if (this.mainServer.taoServer(Integer.parseInt(this.nhapPort.getText()))) {
               this.anDi();
               this.mainServer.hienThiGiaoDienChinh();
            }
         } else {
            this.thongBao.setText("Nhập sai port");
         }
      }

   }
}
// 
// Decompiled by Procyon v0.5.36
// 

package server.giaodien;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.event.WindowListener;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import server.main.MainServer;
import java.awt.event.ActionListener;
import java.awt.Frame;

public class GiaoDienTaoCong extends Frame implements ActionListener
{
    private static final long serialVersionUID = 1L;
    MainServer mainServer;
    JTextField nhapPort;
    JButton nutTaoCong;
    String actionTaoCong;
    JLabel thongBao;
    
    public GiaoDienTaoCong(final MainServer mainServer) {
        super("Th\u1eafp S\u00e1ng Tri Th\u1ee9c - Server.");
        this.actionTaoCong = "taoCong";
        this.mainServer = mainServer;
        this.add(this.taoPanelChinh());
        this.setSize(300, 200);
        this.addWindowListener(mainServer);
        this.setIconImage(mainServer.iconChinh);
    }
    
    JPanel taoPanelChinh() {
        final JPanel panel = new JPanel(new GridLayout(3, 1));
        final JLabel nhanTieuDe = new JLabel("Nh\u1eadp c\u1ed5ng k\u1ebft n\u1ed1i: ");
        (this.nhapPort = new JTextField()).setText("7777");
        this.nhapPort.addActionListener(this);
        panel.add(nhanTieuDe);
        panel.add(this.nhapPort);
        final JPanel panelNut = new JPanel(new FlowLayout());
        (this.nutTaoCong = new JButton("T\u1ea1o c\u1ed5ng.")).setActionCommand(this.actionTaoCong);
        this.nutTaoCong.addActionListener(this);
        panelNut.add(this.nutTaoCong);
        panelNut.add(this.thongBao = new JLabel(""));
        panel.add(panelNut);
        return panel;
    }
    
    public void hienThi() {
        this.setVisible(true);
    }
    
    public void anDi() {
        this.setVisible(false);
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == this.nhapPort || e.getActionCommand().equals(this.actionTaoCong)) {
            final Matcher matcher = Pattern.compile("\\d*").matcher(this.nhapPort.getText());
            if (matcher.matches()) {
                if (this.mainServer.taoServer(Integer.parseInt(this.nhapPort.getText()))) {
                    this.anDi();
                    this.mainServer.hienThiGiaoDienChinh();
                }
            }
            else {
                this.thongBao.setText("Nh\u1eadp sai port");
            }
        }
    }
}

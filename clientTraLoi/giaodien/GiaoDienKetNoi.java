// 
// Decompiled by Procyon v0.5.36
// 

package client.giaodien;

import java.util.regex.Matcher;
import javax.swing.JOptionPane;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.event.WindowListener;
import java.awt.Component;
import client.socket.ThreadClient;
import javax.swing.JTextField;
import client.main.MainClient;
import java.awt.event.ActionListener;
import java.awt.Frame;

public class GiaoDienKetNoi extends Frame implements ActionListener
{
    private static final long serialVersionUID = 1L;
    MainClient mainClient;
    JTextField nhapPort;
    JTextField nhapHost;
    String actionKetNoi;
    ThreadClient threadClient;
    
    public GiaoDienKetNoi(final MainClient mainClient) {
        super("Th\u1eafp s\u00e1ng tri th\u1ee9c - Client");
        this.actionKetNoi = "ketnoi";
        this.mainClient = mainClient;
        this.setSize(300, 200);
        this.add(this.taoPanelChinh());
        this.addWindowListener(mainClient);
        this.threadClient = mainClient.threadClient;
        this.setIconImage(mainClient.iconChinh);
    }
    
    JPanel taoPanelChinh() {
        final JPanel panel = new JPanel(new BorderLayout());
        panel.add(this.taoPanelTop(), "North");
        panel.add(this.taoPanelBot(), "South");
        panel.add(this.taoPanelTrai(), "West");
        panel.add(this.taoPanelCenter(), "Center");
        return panel;
    }
    
    JPanel taoPanelTop() {
        final JPanel panel = new JPanel();
        panel.add(this.taoNhan("K\u1ebft n\u1ed1i \u0111\u1ebfn m\u00e1y ch\u1ee7: "));
        return panel;
    }
    
    JPanel taoPanelBot() {
        final JPanel panel = new JPanel();
        panel.add(this.taoNut("K\u1ebft N\u1ed1i.", this.actionKetNoi));
        return panel;
    }
    
    JPanel taoPanelTrai() {
        final JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.add(this.taoNhan("Nhap Host: "));
        panel.add(this.taoNhan("Nh\u1eadp Port: "));
        return panel;
    }
    
    JPanel taoPanelCenter() {
        final JPanel panel = new JPanel(new GridLayout(2, 1));
        this.nhapHost = new JTextField();
        this.nhapPort = new JTextField();
        this.nhapHost.setText("localhost");
        this.nhapPort.setText("7777");
        panel.add(this.nhapHost);
        panel.add(this.nhapPort);
        return panel;
    }
    
    JLabel taoNhan(final String tenNhan) {
        final JLabel label = new JLabel(tenNhan);
        return label;
    }
    
    JButton taoNut(final String tenNut, final String action) {
        final JButton nut = new JButton(tenNut);
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
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getActionCommand().equals(this.actionKetNoi)) {
            final Matcher matcher = Pattern.compile("\\d*").matcher(this.nhapPort.getText());
            if (matcher.matches()) {
                this.mainClient.ketNoiMayChu(this.nhapHost.getText(), Integer.parseInt(this.nhapPort.getText()));
            }
            else {
                JOptionPane.showMessageDialog(null, "Nh\u1eadp port ch\u01b0a \u0111\u00fang \u0111\u1ecbnh d\u1ea1ng");
            }
        }
    }
}

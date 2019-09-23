// 
// Decompiled by Procyon v0.5.36
// 

package client.giaodien;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.event.WindowListener;
import javax.swing.JTextField;
import client.main.MainClient;
import java.awt.event.ActionListener;
import java.awt.Frame;

public class GiaoDienDangNhap extends Frame implements ActionListener
{
    private static final long serialVersionUID = 1L;
    MainClient mainClient;
    JTextField nhapID;
    JTextField nhapPass;
    String actionDangNhap;
    
    public GiaoDienDangNhap(final MainClient mainClient) {
        super("Th\u1eafp s\u00e1ng tri th\u1ee9c - Client");
        this.actionDangNhap = "dangnhap";
        this.mainClient = mainClient;
        this.setSize(300, 200);
        this.addWindowListener(mainClient);
        this.add(this.taoPanelChinh());
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
        panel.add(this.taoNhan("\u0110\u0103ng nh\u1eadp t\u00e0i kho\u1ea3n m\u00e1y ch\u1ee7: "));
        return panel;
    }
    
    JPanel taoPanelBot() {
        final JPanel panel = new JPanel();
        panel.add(this.taoNut("\u0110\u0103ng nh\u1eadp.", this.actionDangNhap));
        return panel;
    }
    
    JPanel taoPanelTrai() {
        final JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.add(this.taoNhan("Nh\u1eadp ID: "));
        panel.add(this.taoNhan("Nh\u1eadp Pass: "));
        return panel;
    }
    
    JPanel taoPanelCenter() {
        final JPanel panel = new JPanel(new GridLayout(2, 1));
        this.nhapID = new JTextField();
        this.nhapPass = new JTextField();
        panel.add(this.nhapID);
        panel.add(this.nhapPass);
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
        if (e.getActionCommand().equals(this.actionDangNhap)) {
            this.mainClient.dangNhap(this.nhapID.getText(), this.nhapPass.getText());
        }
    }
}

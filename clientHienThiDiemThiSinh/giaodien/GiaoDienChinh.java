// 
// Decompiled by Procyon v0.5.36
// 

package client.giaodien;

import java.awt.event.ActionEvent;
import javax.swing.border.Border;
import java.awt.Font;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.event.WindowListener;
import client.main.MainHienThiClient;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.Frame;

public class GiaoDienChinh extends Frame implements ActionListener
{
    private static final long serialVersionUID = 1L;
    JLabel nhanTen;
    JLabel nhanDiem;
    MainHienThiClient mainHienThiClient;
    
    public GiaoDienChinh(final MainHienThiClient mainHienThiClient) {
        super("Hi\u1ec3n th\u1ecb \u0111i\u1ec3m");
        this.addWindowListener(this.mainHienThiClient = mainHienThiClient);
        this.setSize(this.getMaximumSize());
        this.setIconImage(mainHienThiClient.image);
        this.add(this.taoPanelChinh());
    }
    
    JPanel taoPanelChinh() {
        final JPanel panel = new JPanel(new BorderLayout(10, 10));
        this.nhanTen = new JLabel("xxx", 0);
        final Border border = BorderFactory.createLineBorder(Color.BLUE, 15);
        this.nhanTen.setFont(new Font("Times New Roman", 3, 150));
        final JPanel panelTren = new JPanel();
        panelTren.setBackground(Color.decode("#FFFF99"));
        panelTren.add(this.nhanTen);
        panelTren.setBorder(border);
        panel.add(panelTren, "North");
        (this.nhanDiem = new JLabel("0", 0)).setFont(new Font("Arial", 1, 500));
        panel.add(this.nhanDiem, "Center");
        return panel;
    }
    
    public void hienThi() {
        this.setVisible(true);
    }
    
    public void anDi() {
        this.setVisible(false);
    }
    
    public void thayDoiTen(final String ten) {
        this.nhanTen.setText(ten);
    }
    
    public void thayDoiDiem(final String diem) {
        this.nhanDiem.setText(diem);
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
    }
}

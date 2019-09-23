// 
// Decompiled by Procyon v0.5.36
// 

package server.giaodien;

import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.event.WindowListener;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.Frame;

public class GiaoDienTuyChon extends Frame implements ActionListener
{
    private static final long serialVersionUID = 1L;
    GiaoDienChinh giaoDienChinh;
    JTextField nhapNguoiChoi;
    JTextField nhapID;
    JTextField nhapPass;
    JTextField nhapIDHienThi;
    JTextField nhapPassHienThi;
    JTextField nhapLinkFileLog;
    JButton nutNguoiChoi;
    JButton nutHienThi;
    JButton nutFile;
    String[] thuTu;
    JComboBox comboBox;
    
    public GiaoDienTuyChon(final GiaoDienChinh giaoDienChinh) {
        super("C\u00e0i \u0111\u1eb7t:");
        this.thuTu = new String[] { "user 1", "user 2", "user 3", "user 4" };
        this.giaoDienChinh = giaoDienChinh;
        this.setSize(500, 600);
        this.addWindowListener(new Close(this, giaoDienChinh));
        this.add(this.taoPanelChinh());
    }
    
    JPanel taoPanelChinh() {
        final JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.add(this.taoPanel());
        panel.add(this.taoPanelFile());
        return panel;
    }
    
    JPanel taoPanel() {
        final JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(this.taoPanelTaiKhoan());
        panel.add(this.taoHienThi());
        return panel;
    }
    
    JPanel taoPanelTaiKhoan() {
        final JPanel panel = new JPanel(new FlowLayout());
        (this.comboBox = new JComboBox((E[])this.thuTu)).addActionListener(this);
        panel.add(this.comboBox);
        this.nhapNguoiChoi = new JTextField(this.giaoDienChinh.mainServer.tenNguoiChoi[0]);
        this.nhapID = new JTextField(this.giaoDienChinh.mainServer.idDangNhap[0]);
        this.nhapPass = new JTextField(this.giaoDienChinh.mainServer.passDangNhap[0]);
        (this.nutNguoiChoi = new JButton("Ch\u1ec9nh s\u1eeda")).addActionListener(this);
        panel.add(this.nhapNguoiChoi);
        panel.add(this.nhapID);
        panel.add(this.nhapPass);
        panel.add(this.nutNguoiChoi);
        return panel;
    }
    
    JPanel taoHienThi() {
        final JPanel panel = new JPanel(new FlowLayout());
        this.nhapIDHienThi = new JTextField(this.giaoDienChinh.mainServer.idHienThi);
        this.nhapPassHienThi = new JTextField(this.giaoDienChinh.mainServer.passHienThi);
        (this.nutHienThi = new JButton("Ch\u1ec9nh s\u1eeda hi\u1ec3n th\u1ecb")).addActionListener(this);
        panel.add(this.nhapIDHienThi);
        panel.add(this.nhapPassHienThi);
        panel.add(this.nutHienThi);
        return panel;
    }
    
    JPanel taoPanelFile() {
        final JPanel panel = new JPanel(new FlowLayout());
        this.nhapLinkFileLog = new JTextField(this.giaoDienChinh.mainServer.linkFile);
        (this.nutFile = new JButton("T\u1ea1o l\u1ea1i file")).addActionListener(this);
        panel.add(this.nhapLinkFileLog);
        panel.add(this.nutFile);
        return panel;
    }
    
    public void hienThi() {
        this.nhapLinkFileLog.setText(this.giaoDienChinh.mainServer.linkFile);
        this.setVisible(true);
    }
    
    public void anDi() {
        this.setVisible(false);
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == this.comboBox) {
            this.nhapNguoiChoi.setText(this.giaoDienChinh.mainServer.tenNguoiChoi[this.comboBox.getSelectedIndex()]);
            this.nhapID.setText(this.giaoDienChinh.mainServer.idDangNhap[this.comboBox.getSelectedIndex()]);
            this.nhapPass.setText(this.giaoDienChinh.mainServer.passDangNhap[this.comboBox.getSelectedIndex()]);
            return;
        }
        if (e.getSource() == this.nutNguoiChoi) {
            this.giaoDienChinh.mainServer.tenNguoiChoi[this.comboBox.getSelectedIndex()] = this.nhapNguoiChoi.getText();
            this.giaoDienChinh.mainServer.idDangNhap[this.comboBox.getSelectedIndex()] = this.nhapID.getText();
            this.giaoDienChinh.mainServer.passDangNhap[this.comboBox.getSelectedIndex()] = this.nhapPass.getText();
            this.giaoDienChinh.hienThiNguoiChuoi();
            return;
        }
        if (e.getSource() == this.nutHienThi) {
            this.giaoDienChinh.mainServer.idHienThi = this.nhapIDHienThi.getText();
            this.giaoDienChinh.mainServer.passHienThi = this.nhapPassHienThi.getText();
            return;
        }
        if (e.getSource() == this.nutFile) {
            this.giaoDienChinh.mainServer.linkFile = this.nhapLinkFileLog.getText();
            this.giaoDienChinh.mainServer.taoLinkFile();
        }
    }
}

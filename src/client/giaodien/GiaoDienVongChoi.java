// 
// Decompiled by Procyon v0.5.36
// 

package client.giaodien;

import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.event.WindowListener;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import client.main.MainClient;
import java.awt.event.ActionListener;
import java.awt.Frame;

public class GiaoDienVongChoi extends Frame implements ActionListener
{
    private static final long serialVersionUID = 1L;
    MainClient mainClient;
    JLabel nhanTenNguoiChoi;
    JLabel nhanThoiGian;
    JLabel nhanDiemSo;
    int kichCoTen;
    int kichCoThoiGian;
    int kichCoDiemSo;
    int kichCoNhap;
    int kichCoHienThi;
    String font;
    JTextField nhapDapAn;
    JTextArea hienThiDapAn;
    JButton guiDapAn;
    Border border;
    Border border2;
    String actionGui;
    public String dapAnTruoc;
    public boolean checkGuiDapAn;
    
    public GiaoDienVongChoi(final MainClient mainClient) {
        super("Th\u1eafp S\u00e1ng Tri Th\u1ee9c - Client");
        this.kichCoTen = 23;
        this.kichCoThoiGian = 23;
        this.kichCoDiemSo = 23;
        this.kichCoNhap = 20;
        this.kichCoHienThi = 18;
        this.font = "Arial";
        this.border = BorderFactory.createLineBorder(Color.ORANGE, 5);
        this.border2 = BorderFactory.createLineBorder(Color.DARK_GRAY, 20);
        this.actionGui = "guidapan";
        this.dapAnTruoc = "";
        this.addWindowListener(this.mainClient = mainClient);
        this.setSize(this.getMaximumSize());
        this.addWindowListener(mainClient);
        this.add(this.taoPanelChinh());
        this.setIconImage(mainClient.iconChinh);
        this.checkGuiDapAn = false;
    }
    
    JPanel taoPanelChinh() {
        final JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.DARK_GRAY);
        panel.add(this.taoPanelTop(), "North");
        panel.add(this.taoPanelCenter(), "Center");
        panel.add(this.taoPanelBot(), "South");
        return panel;
    }
    
    JPanel taoPanelTop() {
        final JPanel panel = new JPanel(new GridLayout(1, 2));
        (this.nhanTenNguoiChoi = new JLabel("T\u00caN NG\u01af\u1edcI CH\u01a0I;")).setBorder(this.border);
        this.nhanTenNguoiChoi.setFont(new Font("Times New Roman", 3, this.kichCoTen));
        panel.add(this.nhanTenNguoiChoi);
        final JPanel panelRight = new JPanel(new GridLayout(1, 2));
        (this.nhanDiemSo = new JLabel("######")).setFont(new Font(this.font, 1, this.kichCoDiemSo));
        (this.nhanThoiGian = new JLabel("TH\u1edcI GIAN: 00")).setFont(new Font(this.font, 1, this.kichCoThoiGian));
        panelRight.add(this.nhanDiemSo);
        panelRight.add(this.nhanThoiGian);
        panelRight.setBorder(this.border);
        panel.add(panelRight);
        return panel;
    }
    
    JPanel taoPanelCenter() {
        final JPanel panel = new JPanel(new BorderLayout());
        this.hienThiDapAn = new JTextArea();
        final JScrollPane scrollPane = new JScrollPane(this.hienThiDapAn);
        this.hienThiDapAn.setFont(new Font("Arial", 0, this.kichCoHienThi));
        this.hienThiDapAn.setEditable(false);
        panel.setBorder(this.border2);
        panel.add(scrollPane, "Center");
        panel.add(scrollPane.getVerticalScrollBar(), "East");
        panel.add(scrollPane.getHorizontalScrollBar(), "South");
        return panel;
    }
    
    JPanel taoPanelBot() {
        final JPanel panel = new JPanel(new BorderLayout(10, 0));
        (this.nhapDapAn = new JTextField()).setFont(new Font(this.font, 2, this.kichCoNhap));
        this.nhapDapAn.setActionCommand(this.actionGui);
        this.nhapDapAn.addActionListener(this);
        panel.add(this.nhapDapAn, "Center");
        (this.guiDapAn = new JButton("G\u1eedi \u0110\u00e1p \u00c1n")).setActionCommand(this.actionGui);
        this.guiDapAn.addActionListener(this);
        this.guiDapAn.setFont(new Font(this.font, 1, this.kichCoNhap));
        panel.add(this.guiDapAn, "East");
        panel.setBackground(Color.DARK_GRAY);
        panel.setBorder(this.border2);
        return panel;
    }
    
    public void hienThiThoiGian(final String thoiGian) {
        this.checkGuiDapAn = true;
        this.nhanThoiGian.setText("TH\u1edcI GIAN: " + thoiGian);
    }
    
    public void hienThiDapAn(final String tinNhan) {
        this.hienThiDapAn.append("\n" + tinNhan);
    }
    
    public void hienThiTenNguoiChuoi(final String tenNguoiChoi) {
        this.nhanTenNguoiChoi.setText(tenNguoiChoi);
    }
    
    public void hienThiDiemSo(final String diemSo) {
        this.nhanDiemSo.setText("\u0110i\u1ec3m S\u1ed1: " + diemSo);
    }
    
    public void hienThi() {
        this.setVisible(true);
    }
    
    public void anDi() {
        this.setVisible(false);
    }
    
    @Override
    public void actionPerformed(final ActionEvent ev) {
        if (ev.getActionCommand().equals(this.actionGui)) {
            if (this.checkGuiDapAn) {
                System.out.println("check true");
                if (!this.dapAnTruoc.equals(this.nhapDapAn.getText())) {
                    this.dapAnTruoc = this.nhapDapAn.getText();
                    this.mainClient.guiTinDenServer("Tra loi: " + this.dapAnTruoc);
                }
            }
            else {
                System.out.println("check false");
            }
        }
    }
}

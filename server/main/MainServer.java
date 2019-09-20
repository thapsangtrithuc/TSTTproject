// 
// Decompiled by Procyon v0.5.36
// 

package server.main;

import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.io.IOException;
import java.awt.Component;
import javax.swing.JOptionPane;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import server.socket.ThreadServer;
import java.util.Vector;
import java.io.Writer;
import server.giaodien.GiaoDienChinh;
import server.giaodien.GiaoDienTaoCong;
import server.socket.TaoServer;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;

public class MainServer extends WindowAdapter implements Runnable
{
    ImageIcon imageIcon;
    public Image iconChinh;
    public TaoServer taoServer;
    GiaoDienTaoCong giaoDienTaoCong;
    GiaoDienChinh giaoDienChinh;
    public String linkFile;
    Writer writer;
    Vector<ThreadServer> threadServers;
    boolean[] checkThreadServers;
    int gioiHanSoClient;
    int[] idDangNhapTuongUng;
    int soLuongThread;
    ThreadServer threadHienThi;
    boolean checkThreadHienThi;
    boolean[] daGuiTin;
    boolean daGuiTinHienThi;
    public String kieuDuLieu;
    public String[] idDangNhap;
    public int gioiHanSoID;
    public String[] passDangNhap;
    public String[] tenNguoiChoi;
    public int[] clientTuongUng;
    boolean[] tinhTrangDangNhap;
    boolean[] loaiDangNhap;
    public long[] thoiGianCuoiCung;
    String[] cauTraLoi;
    String[] diemSo;
    public String idHienThi;
    public String passHienThi;
    boolean tinhTrangHienThi;
    Thread threadHienThiMay;
    public int soCauHoi;
    long thoiGianBatDau;
    
    public static void main(final String[] args) {
        final MainServer mainServer = new MainServer();
        mainServer.batDau();
    }
    
    public MainServer() {
        this.imageIcon = new ImageIcon(this.getClass().getResource("LogoTSTT.png"));
        this.iconChinh = this.imageIcon.getImage();
        this.taoServer = new TaoServer(this);
        this.giaoDienTaoCong = new GiaoDienTaoCong(this);
        this.linkFile = "e:/log.txt";
        this.threadServers = new Vector<ThreadServer>();
        this.gioiHanSoClient = 10;
        this.soLuongThread = 0;
        this.checkThreadHienThi = false;
        this.daGuiTin = new boolean[10];
        this.daGuiTinHienThi = false;
        this.kieuDuLieu = "UTF-8";
        this.idDangNhap = new String[] { "user01", "user02", "user03", "user04", "userhienthi01", "userhienthi02", "userhienthi03", "userhienthi04" };
        this.gioiHanSoID = 8;
        this.passDangNhap = new String[] { "pass01", "pass02", "pass03", "pass04", "passht01", "passht02", "passht03", "passht04" };
        this.tenNguoiChoi = new String[this.gioiHanSoID + 5];
        this.tinhTrangDangNhap = new boolean[10];
        final boolean[] loaiDangNhap = new boolean[10];
        loaiDangNhap[1] = (loaiDangNhap[0] = true);
        loaiDangNhap[3] = (loaiDangNhap[2] = true);
        this.loaiDangNhap = loaiDangNhap;
        this.thoiGianCuoiCung = new long[this.gioiHanSoID + 5];
        this.cauTraLoi = new String[this.gioiHanSoID + 5];
        this.idHienThi = "userhienthi";
        this.passHienThi = "passhienthi";
        this.tinhTrangHienThi = false;
        this.threadHienThiMay = new Thread(this);
        this.soCauHoi = 0;
        this.thoiGianBatDau = 0L;
        this.checkThreadServers = new boolean[this.gioiHanSoClient + 1];
        this.idDangNhapTuongUng = new int[this.gioiHanSoClient];
        this.clientTuongUng = new int[this.gioiHanSoID];
        for (int i = 0; i < this.gioiHanSoClient; ++i) {
            this.checkThreadServers[i] = false;
        }
        for (int i = 0; i < this.gioiHanSoClient; ++i) {
            this.idDangNhapTuongUng[i] = this.gioiHanSoID;
        }
        for (int i = 0; i < this.gioiHanSoID; ++i) {
            this.thoiGianCuoiCung[i] = 10000000000L;
            this.clientTuongUng[i] = this.gioiHanSoClient;
            this.cauTraLoi[i] = "...";
        }
        this.tenNguoiChoi[0] = "Nguyen Van A";
        this.tenNguoiChoi[1] = "Nguyen Van B";
        this.tenNguoiChoi[2] = "Nguyen Van C";
        this.tenNguoiChoi[3] = "Nguyen Van D";
        this.diemSo = new String[4];
        for (int i = 0; i < 4; ++i) {
            this.diemSo[i] = "0";
        }
        this.taoLinkFile();
        this.giaoDienChinh = new GiaoDienChinh(this);
    }
    
    public void taoLinkFile() {
        try {
            (this.writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.linkFile, true), this.kieuDuLieu))).close();
        }
        catch (IOException ex) {
            this.linkFile = JOptionPane.showInputDialog(null, "Kh\u00f4ng t\u00ecm th\u1ea5y file log.txt,\nM\u1eddi nh\u1eadp l\u1ea1i link file,\n nh\u1eadp NO \u0111\u1ec3 tho\u00e1t.");
            if (this.linkFile.equals("NO")) {
                this.exitChuongTrinh();
            }
            this.taoLinkFile();
        }
    }
    
    public void batDau() {
        this.giaoDienTaoCong.hienThi();
    }
    
    public void khoiChayThreadHienThiMay() {
        this.threadHienThiMay.start();
    }
    
    public void hienThiGiaoDienChinh() {
        this.giaoDienChinh.hienThi();
    }
    
    public void suaTrangThaiMay() {
        String trangThai = "";
        for (int i = 0; i < this.gioiHanSoClient; ++i) {
            trangThai = String.valueOf(trangThai) + "\nClient #" + i + " : " + (this.checkThreadServers[i] ? "\u0111\u00e3 k\u1ebft n\u1ed1i" : "ch\u01b0a k\u1ebft n\u1ed1i") + " = " + (this.daGuiTin[i] ? "OK" : "Fail");
            trangThai = String.valueOf(trangThai) + ((this.idDangNhapTuongUng[i] < this.gioiHanSoID) ? ("  " + this.idDangNhap[this.idDangNhapTuongUng[i]]) : "  None User");
        }
        trangThai = String.valueOf(trangThai) + "\n\nHi\u1ec3n Th\u1ecb :" + (this.checkThreadHienThi ? "\u0111\u00e3 k\u1ebft n\u1ed1i" : "ch\u01b0a k\u1ebft n\u1ed1i") + " = " + (this.daGuiTinHienThi ? "OK" : "Fail");
        this.giaoDienChinh.hienThiTrangThaiMay(trangThai);
    }
    
    public boolean taoServer(final int port) {
        final boolean res = this.taoServer.taoServer(port);
        this.khoiChayThreadHienThiMay();
        return res;
    }
    
    public void addThreadServer(final ThreadServer thread) {
        if (this.soLuongThread < this.gioiHanSoClient) {
            boolean kt = false;
            for (int i = 0; i < this.threadServers.size(); ++i) {
                if (!this.checkThreadServers[i]) {
                    this.checkThreadServers[i] = true;
                    this.threadServers.setElementAt(thread, i);
                    this.threadServers.elementAt(i).thietLapID(i);
                    this.threadServers.elementAt(i).start();
                    ++this.soLuongThread;
                    kt = true;
                }
            }
            if (!kt) {
                this.threadServers.addElement(thread);
                this.checkThreadServers[this.threadServers.size() - 1] = true;
                this.threadServers.elementAt(this.threadServers.size() - 1).thietLapID(this.threadServers.size() - 1);
                this.threadServers.elementAt(this.threadServers.size() - 1).start();
                kt = true;
            }
        }
    }
    
    public void suaDiem(final String[] diemSo) {
        this.diemSo = diemSo;
        for (int i = 0; i < 4; ++i) {
            if (this.checkThreadServers[this.clientTuongUng[i]]) {
                this.guiTinNhan("SetDiem: " + diemSo[i], this.clientTuongUng[i]);
            }
            if (this.checkThreadServers[this.clientTuongUng[i + 4]]) {
                this.guiTinNhan("SetDiem: " + diemSo[i], this.clientTuongUng[i + 4]);
            }
        }
    }
    
    public void hienThiTen() {
        for (int i = 0; i < 4; ++i) {
            if (this.checkThreadServers[this.clientTuongUng[i]]) {
                this.guiTinNhan("Ten ID: " + this.tenNguoiChoi[i], this.clientTuongUng[i]);
            }
            if (this.checkThreadServers[this.clientTuongUng[i + 4]]) {
                this.guiTinNhan("Ten ID: " + this.tenNguoiChoi[i], this.clientTuongUng[i + 4]);
            }
        }
    }
    
    public void loaiMay(final int idClient) {
        if (idClient == 28) {
            this.checkThreadHienThi = false;
            this.tinhTrangHienThi = false;
            this.threadHienThi.ngatLuongDuLieu();
        }
        else {
            if (this.checkThreadServers[idClient]) {
                --this.soLuongThread;
            }
            this.checkThreadServers[idClient] = false;
            this.tinhTrangDangNhap[this.idDangNhapTuongUng[idClient]] = false;
            this.idDangNhapTuongUng[idClient] = this.gioiHanSoID;
            this.threadServers.elementAt(idClient).ngatLuongDuLieu();
        }
    }
    
    public void xuLiTinNhan(final String tinNhan, final int idClient) {
        System.out.println(String.valueOf(idClient) + " " + tinNhan);
        if (tinNhan.startsWith("DangNhap: ")) {
            final int viTriCach1 = tinNhan.indexOf(" ");
            final int viTriCach2 = tinNhan.indexOf(" ", viTriCach1 + 1);
            final String id = tinNhan.substring(viTriCach1 + 1, viTriCach2);
            final String pass = tinNhan.substring(viTriCach2 + 1);
            System.out.println(String.valueOf(idClient) + " " + id + " " + pass);
            System.out.println(tinNhan);
            for (int i = 0; i < this.gioiHanSoID; ++i) {
                if (this.idDangNhap[i].equals(id) && this.passDangNhap[i].equals(pass) && !this.tinhTrangDangNhap[i]) {
                    this.guiTinNhan("Login: OK", idClient);
                    this.tinhTrangDangNhap[i] = true;
                    this.idDangNhapTuongUng[idClient] = i;
                    this.clientTuongUng[i] = idClient;
                    this.hienThiTen();
                    this.suaDiem(this.diemSo);
                    return;
                }
            }
            if (this.idHienThi.equals(id) && this.passHienThi.equals(pass) && !this.tinhTrangHienThi) {
                (this.threadHienThi = this.threadServers.elementAt(idClient)).thietLapID(28);
                this.checkThreadHienThi = true;
                this.checkThreadServers[idClient] = false;
                this.tinhTrangHienThi = true;
                this.guiTinNhan("Login: OK", 28);
                return;
            }
            this.guiTinNhan("Login: FALL", idClient);
        }
        else if (tinNhan.startsWith("Tra loi: ")) {
            final String hienThi = tinNhan.substring(tinNhan.indexOf(" ", tinNhan.indexOf(" ") + 1) + 1);
            final Calendar calendar = Calendar.getInstance();
            final String thoiGian = this.tinhToanThoiGian(calendar);
            this.thoiGianCuoiCung[this.idDangNhapTuongUng[idClient]] = this.tinhThoiGian(calendar);
            this.cauTraLoi[this.idDangNhapTuongUng[idClient]] = hienThi;
            this.guiTinNhan("Dap an: " + thoiGian + hienThi, idClient);
            final String hienThiTraLoi = "\n #" + String.valueOf(idClient) + " : " + this.idDangNhap[this.idDangNhapTuongUng[idClient]] + " " + thoiGian + hienThi;
            this.ghiFile("\n-> Tr\u1ea3 l\u1eddi: " + hienThiTraLoi);
            this.giaoDienChinh.hienThiTraLoi(hienThiTraLoi);
        }
    }
    
    public void guiBangRank() {
        final boolean[] mang = { true, true, true, true, true };
        long max = 0L;
        int vtmax = -1;
        max = 200000000000L;
        for (int i = 0; i < 4; ++i) {
            if (this.thoiGianCuoiCung[i] < max && mang[i]) {
                max = this.thoiGianCuoiCung[i];
                vtmax = i;
            }
        }
        String tinNhanGui = "#1: &" + this.tenNguoiChoi[vtmax] + "&" + this.tinhThoiGianTuongDoi(this.thoiGianCuoiCung[vtmax]) + "&" + this.cauTraLoi[vtmax];
        this.guiTinNhan(tinNhanGui, 28);
        this.ghiFile(tinNhanGui);
        System.out.println(String.valueOf(this.tenNguoiChoi[vtmax]) + " " + this.tinhThoiGianTuongDoi(this.thoiGianCuoiCung[vtmax]));
        mang[vtmax] = false;
        max = 200000000000L;
        for (int i = 0; i < 4; ++i) {
            if (this.thoiGianCuoiCung[i] < max && mang[i]) {
                max = this.thoiGianCuoiCung[i];
                vtmax = i;
            }
        }
        tinNhanGui = "#2: &" + this.tenNguoiChoi[vtmax] + "&" + this.tinhThoiGianTuongDoi(this.thoiGianCuoiCung[vtmax]) + "&" + this.cauTraLoi[vtmax];
        this.guiTinNhan(tinNhanGui, 28);
        this.ghiFile(tinNhanGui);
        System.out.println(String.valueOf(this.tenNguoiChoi[vtmax]) + " " + this.tinhThoiGianTuongDoi(this.thoiGianCuoiCung[vtmax]));
        mang[vtmax] = false;
        max = 200000000000L;
        for (int i = 0; i < 4; ++i) {
            if (this.thoiGianCuoiCung[i] < max && mang[i]) {
                max = this.thoiGianCuoiCung[i];
                vtmax = i;
            }
        }
        tinNhanGui = "#3: &" + this.tenNguoiChoi[vtmax] + "&" + this.tinhThoiGianTuongDoi(this.thoiGianCuoiCung[vtmax]) + "&" + this.cauTraLoi[vtmax];
        this.guiTinNhan(tinNhanGui, 28);
        this.ghiFile(tinNhanGui);
        System.out.println(String.valueOf(this.tenNguoiChoi[vtmax]) + " " + this.tinhThoiGianTuongDoi(this.thoiGianCuoiCung[vtmax]));
        mang[vtmax] = false;
        max = 200000000000L;
        for (int i = 0; i < 4; ++i) {
            if (this.thoiGianCuoiCung[i] < max && mang[i]) {
                max = this.thoiGianCuoiCung[i];
                vtmax = i;
            }
        }
        tinNhanGui = "#4: &" + this.tenNguoiChoi[vtmax] + "&" + this.tinhThoiGianTuongDoi(this.thoiGianCuoiCung[vtmax]) + "&" + this.cauTraLoi[vtmax];
        this.guiTinNhan(tinNhanGui, 28);
        this.ghiFile(tinNhanGui);
        System.out.println(String.valueOf(this.tenNguoiChoi[vtmax]) + " " + this.tinhThoiGianTuongDoi(this.thoiGianCuoiCung[vtmax]));
        mang[vtmax] = false;
    }
    
    String tinhThoiGianTuongDoi(final long thoiGian) {
        long thoiGianRes = thoiGian - this.thoiGianBatDau;
        System.out.println("Thoi gian tuong doi " + thoiGianRes + " " + thoiGian);
        if (thoiGianRes <= 0L) {
            thoiGianRes = 97000L;
        }
        String res = String.valueOf(thoiGianRes * 1.0 / 1000.0);
        if (thoiGianRes > 60000L) {
            res = "ERR";
        }
        return res;
    }
    
    public void resetBangRank() {
        for (int i = 0; i < 4; ++i) {
            this.thoiGianCuoiCung[i] = 100000000000L;
            this.cauTraLoi[i] = "...";
        }
        this.thoiGianBatDau = 0L;
    }
    
    public void taoThoiGianBatDau(final Calendar calendar) {
        this.thoiGianBatDau = this.tinhThoiGian(calendar);
        final String hienThi = "\n ********************\n C\u00e2u h\u1ecfi s\u1ed1 #" + String.valueOf(this.soCauHoi) + " : " + this.tinhToanThoiGian(calendar) + "\n";
        this.giaoDienChinh.hienThiTraLoi(hienThi);
        this.ghiFile(hienThi);
        this.guiTinNhanDenToanBo("Hien thi: C\u00e2u h\u1ecfi s\u1ed1 #" + String.valueOf(this.soCauHoi++));
        System.out.println("thoi gian bat dau: " + this.thoiGianBatDau);
    }
    
    public boolean guiTinNhan(final String tinNhan, final int idClient) {
        if (idClient != 28) {
            try {
                if (this.checkThreadServers[idClient]) {
                    this.threadServers.elementAt(idClient).guiDuLieu(tinNhan);
                    return this.daGuiTin[idClient] = true;
                }
                this.daGuiTin[idClient] = false;
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
            return false;
        }
        if (this.checkThreadHienThi) {
            this.threadHienThi.guiDuLieu(tinNhan);
            return this.daGuiTinHienThi = true;
        }
        return this.daGuiTinHienThi = false;
    }
    
    public void guiTinNhanDenToanBo(final String tinNhan) {
        if (!this.threadHienThiMay.isAlive()) {
            this.threadHienThiMay.start();
        }
        this.guiTinNhan(tinNhan, 28);
        for (int i = 0; i < 10; ++i) {
            this.guiTinNhan(tinNhan, i);
        }
    }
    
    public String tinhToanThoiGian(final Calendar calendar) {
        String thoiGian = "";
        final String gio = String.valueOf(calendar.get(10));
        final String phut = String.valueOf(calendar.get(12));
        final String giay = String.valueOf(calendar.get(13));
        final String milis = String.valueOf(calendar.get(14));
        thoiGian = String.valueOf(thoiGian) + "[ " + gio + ":" + phut + ":" + giay + "." + milis + " ] ";
        return thoiGian;
    }
    
    public long tinhThoiGian(final Calendar calendar) {
        long thoiGian = calendar.get(10);
        thoiGian = thoiGian * 60L + calendar.get(12);
        thoiGian = thoiGian * 60L + calendar.get(13);
        thoiGian = thoiGian * 1000L + calendar.get(14);
        return thoiGian;
    }
    
    public void ghiFile(final String duLieu) {
        try {
            this.writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.linkFile, true), this.kieuDuLieu));
            this.writer.append((CharSequence)duLieu).append((CharSequence)"\n");
            this.writer.flush();
            this.writer.close();
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "C\u00f3 l\u1ed7i x\u1ea3y ra v\u1edbi file log");
            this.taoLinkFile();
            this.ghiFile(duLieu);
        }
    }
    
    public void exitChuongTrinh() {
        final int hoi = JOptionPane.showConfirmDialog(null, "B\u1ea1n c\u00f3 mu\u1ed1n tho\u00e1t ch\u01b0\u01a1ng tr\u00ecnh kh\u00f4ng?", null, 0);
        if (hoi == 0) {
            System.exit(0);
        }
    }
    
    @Override
    public void windowClosing(final WindowEvent e) {
        this.exitChuongTrinh();
    }
    
    @Override
    public void run() {
        while (true) {
            this.guiTinNhanDenToanBo("Kiem tra: ");
            this.suaTrangThaiMay();
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex) {
                JOptionPane.showMessageDialog(null, "L\u1ed7i thread hi\u1ec3n th\u1ecb m\u00e1y");
            }
        }
    }
}

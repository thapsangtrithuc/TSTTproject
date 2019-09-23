// 
// Decompiled by Procyon v0.5.36
// 

package server.socket;

import java.awt.Component;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;
import server.main.MainServer;

public class ThreadServer extends Thread
{
    MainServer mainServer;
    Socket socket;
    int ID;
    BufferedWriter bufferedWriter;
    BufferedReader bufferedReader;
    String tinNhanNhanDuoc;
    
    public ThreadServer(final MainServer mainServer, final Socket socket) {
        this.ID = 0;
        this.mainServer = mainServer;
        this.socket = socket;
        try {
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), mainServer.kieuDuLieu));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), mainServer.kieuDuLieu));
        }
        catch (IOException ex) {
            mainServer.loaiMay(this.ID);
        }
    }
    
    public void thietLapID(final int ID) {
        this.ID = ID;
    }
    
    public void guiDuLieu(final String tinNhan) {
        try {
            System.out.println("gui: " + tinNhan);
            this.bufferedWriter.write(tinNhan);
            this.bufferedWriter.newLine();
            this.bufferedWriter.flush();
        }
        catch (IOException ex) {
            this.mainServer.loaiMay(this.ID);
        }
    }
    
    @Override
    public void run() {
        try {
            while (true) {
                if ((this.tinNhanNhanDuoc = this.bufferedReader.readLine()) != null) {
                    this.mainServer.xuLiTinNhan(this.tinNhanNhanDuoc, this.ID);
                }
            }
        }
        catch (IOException ex) {
            this.mainServer.loaiMay(this.ID);
            JOptionPane.showMessageDialog(null, "L\u1ed7i \u0111\u1ecdc d\u1eef li\u1ec7u: " + this.ID);
        }
    }
    
    public boolean ngatLuongDuLieu() {
        try {
            this.bufferedReader.close();
            this.bufferedWriter.close();
            this.socket.close();
            return true;
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ng\u1eaft lu\u1ed3ng th\u1ea5t b\u1ea1i");
            return false;
        }
    }
}

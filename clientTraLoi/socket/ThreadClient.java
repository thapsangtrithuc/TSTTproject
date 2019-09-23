// 
// Decompiled by Procyon v0.5.36
// 

package client.socket;

import java.io.IOException;
import java.net.UnknownHostException;
import java.awt.Component;
import javax.swing.JOptionPane;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.net.Socket;
import client.main.MainClient;

public class ThreadClient extends Thread
{
    MainClient mainClient;
    Socket socket;
    String svHost;
    int svPort;
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;
    String tinNhanTuServer;
    boolean daKetNoi;
    
    public ThreadClient(final MainClient mainClient) {
        this.daKetNoi = false;
        this.mainClient = mainClient;
    }
    
    public boolean taoKetNoi(final String svHost, final int svPort) {
        this.svHost = svHost;
        this.svPort = svPort;
        try {
            this.socket = new Socket(svHost, svPort);
            this.bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), this.mainClient.kieuDuLieu));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream(), this.mainClient.kieuDuLieu));
            JOptionPane.showMessageDialog(null, "K\u1ebft n\u1ed1i th\u00e0nh c\u00f4ng");
            this.start();
            return true;
        }
        catch (UnknownHostException ex) {
            JOptionPane.showMessageDialog(null, "M\u1eddi nh\u1eadp l\u1ea1i", "L\u1ed7i k\u1ebft n\u1ed1i", 0);
        }
        catch (IOException ex2) {
            JOptionPane.showMessageDialog(null, "M\u1eddi nh\u1eadp l\u1ea1i", "L\u1ed7i k\u1ebft n\u1ed1i", 0);
        }
        return false;
    }
    
    public void guiTinDenServer(final String tinNhan) {
        try {
            System.out.println("gui: " + tinNhan);
            this.bufferedWriter.write(tinNhan);
            this.bufferedWriter.newLine();
            this.bufferedWriter.flush();
        }
        catch (UnknownHostException ex) {
            this.mainClient.ketNoiLai();
        }
        catch (IOException ex2) {
            this.mainClient.ketNoiLai();
        }
    }
    
    @Override
    public void run() {
        try {
            while (true) {
                if ((this.tinNhanTuServer = this.bufferedReader.readLine()) != null) {
                    this.mainClient.xuLiTinNhan(this.tinNhanTuServer);
                }
            }
        }
        catch (UnknownHostException ex) {
            this.mainClient.ketNoiLai();
        }
        catch (IOException ex2) {
            this.mainClient.ketNoiLai();
        }
    }
}

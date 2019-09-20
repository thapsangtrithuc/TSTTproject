// 
// Decompiled by Procyon v0.5.36
// 

package server.socket;

import java.io.IOException;
import java.awt.Component;
import javax.swing.JOptionPane;
import java.net.Socket;
import java.net.ServerSocket;
import server.main.MainServer;

public class TaoServer extends Thread
{
    MainServer mainServer;
    ServerSocket serverSocket;
    Socket socket;
    int portServer;
    
    public TaoServer(final MainServer mainServer) {
        this.mainServer = mainServer;
    }
    
    public boolean taoServer(final int port) {
        this.portServer = port;
        try {
            this.serverSocket = new ServerSocket(this.portServer);
            this.start();
            JOptionPane.showMessageDialog(null, "T\u1ea1o c\u1ed5ng th\u00e0nh c\u00f4ng");
            return true;
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "T\u1ea1o c\u1ed5ng th\u1ea5t b\u1ea1i!!!", null, 0);
            return false;
        }
    }
    
    @Override
    public void run() {
        try {
            while (true) {
                this.socket = this.serverSocket.accept();
                this.mainServer.addThreadServer(new ThreadServer(this.mainServer, this.socket));
            }
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "L\u1ed7i khi k\u1ebft n\u1ed1i client");
        }
    }
}

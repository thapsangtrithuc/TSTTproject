package server.socket;

import java.awt.Component;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;
import server.main.MainServer;

public class TaoServer extends Thread {
   MainServer mainServer;
   ServerSocket serverSocket;
   Socket socket;
   int portServer;

   public TaoServer(MainServer mainServer) {
      this.mainServer = mainServer;
   }

   public boolean taoServer(int port) {
      this.portServer = port;

      try {
         this.serverSocket = new ServerSocket(this.portServer);
         this.start();
         JOptionPane.showMessageDialog((Component)null, "Tạo cổng thành công");
         return true;
      } catch (IOException var3) {
         JOptionPane.showMessageDialog((Component)null, "Tạo cổng thất bại!!!", (String)null, 0);
         return false;
      }
   }

   public void run() {
      try {
         while(true) {
            this.socket = this.serverSocket.accept();
            this.mainServer.addThreadServer(new ThreadServer(this.mainServer, this.socket));
         }
      } catch (IOException var2) {
         JOptionPane.showMessageDialog((Component)null, "Lỗi khi kết nối client");
      }
   }
}
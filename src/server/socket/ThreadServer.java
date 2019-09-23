package server.socket;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import javax.swing.JOptionPane;
import server.main.MainServer;

public class ThreadServer extends Thread {
   MainServer mainServer;
   Socket socket;
   int ID = 0;
   BufferedWriter bufferedWriter;
   BufferedReader bufferedReader;
   String tinNhanNhanDuoc;

   public ThreadServer(MainServer mainServer, Socket socket) {
      this.mainServer = mainServer;
      this.socket = socket;

      try {
         this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), mainServer.kieuDuLieu));
         this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), mainServer.kieuDuLieu));
      } catch (IOException var4) {
         mainServer.loaiMay(this.ID);
      }

   }

   public void thietLapID(int ID) {
      this.ID = ID;
   }

   public void guiDuLieu(String tinNhan) {
      try {
         System.out.println("gui: " + tinNhan);
         this.bufferedWriter.write(tinNhan);
         this.bufferedWriter.newLine();
         this.bufferedWriter.flush();
      } catch (IOException var3) {
         this.mainServer.loaiMay(this.ID);
      }

   }

   public void run() {
      try {
         while(true) {
            if ((this.tinNhanNhanDuoc = this.bufferedReader.readLine()) != null) {
               this.mainServer.xuLiTinNhan(this.tinNhanNhanDuoc, this.ID);
            }
         }
      } catch (IOException var2) {
         this.mainServer.loaiMay(this.ID);
         JOptionPane.showMessageDialog((Component)null, "Lỗi đọc dữ liệu: " + this.ID);
      }
   }

   public boolean ngatLuongDuLieu() {
      try {
         this.bufferedReader.close();
         this.bufferedWriter.close();
         this.socket.close();
         return true;
      } catch (IOException var2) {
         JOptionPane.showMessageDialog((Component)null, "Ngắt luồng thất bại");
         return false;
      }
   }
}
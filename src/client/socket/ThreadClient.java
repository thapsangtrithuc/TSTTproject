package client.socket;

import client.main.MainClient;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

public class ThreadClient extends Thread {
   MainClient mainClient;
   Socket socket;
   String svHost;
   int svPort;
   BufferedReader bufferedReader;
   BufferedWriter bufferedWriter;
   String tinNhanTuServer;
   boolean daKetNoi = false;

   public ThreadClient(MainClient mainClient) {
      this.mainClient = mainClient;
   }

   public boolean taoKetNoi(String svHost, int svPort) {
      this.svHost = svHost;
      this.svPort = svPort;

      try {
         this.socket = new Socket(svHost, svPort);
         this.bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), this.mainClient.kieuDuLieu));
         this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream(), this.mainClient.kieuDuLieu));
         JOptionPane.showMessageDialog((Component)null, "Kết nối thành công");
         this.start();
         return true;
      } catch (UnknownHostException var4) {
         JOptionPane.showMessageDialog((Component)null, "Mời nhập lại", "Lỗi kết nối", 0);
      } catch (IOException var5) {
         JOptionPane.showMessageDialog((Component)null, "Mời nhập lại", "Lỗi kết nối", 0);
      }

      return false;
   }

   public void guiTinDenServer(String tinNhan) {
      try {
         System.out.println("gui: " + tinNhan);
         this.bufferedWriter.write(tinNhan);
         this.bufferedWriter.newLine();
         this.bufferedWriter.flush();
      } catch (UnknownHostException var3) {
         this.mainClient.ketNoiLai();
      } catch (IOException var4) {
         this.mainClient.ketNoiLai();
      }

   }

   public void run() {
      try {
         while(true) {
            if ((this.tinNhanTuServer = this.bufferedReader.readLine()) != null) {
               this.mainClient.xuLiTinNhan(this.tinNhanTuServer);
            }
         }
      } catch (UnknownHostException var2) {
         this.mainClient.ketNoiLai();
      } catch (IOException var3) {
         this.mainClient.ketNoiLai();
      }

   }
}
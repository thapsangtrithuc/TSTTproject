package clienthienthi.socket;

import clienthienthi.main.HienThiMain;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

public class ThreadHienThi extends Thread {
   HienThiMain hienThiMain;
   Socket socket;
   String svHost;
   int svPort;
   BufferedReader bufferedReader;
   BufferedWriter bufferedWriter;
   String tinNhanTuServer;

   public ThreadHienThi(HienThiMain hienThiMain) {
      this.hienThiMain = hienThiMain;
   }

   public boolean taoKetNoi(String svHost, int svPort) {
      this.svHost = svHost;
      this.svPort = svPort;

      try {
         this.socket = new Socket(svHost, svPort);
         this.bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), this.hienThiMain.kieuDuLieu));
         this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream(), this.hienThiMain.kieuDuLieu));
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
         this.hienThiMain.ketNoiLai();
      } catch (IOException var4) {
         this.hienThiMain.ketNoiLai();
      }

   }

   public void run() {
      try {
         while(true) {
            if ((this.tinNhanTuServer = this.bufferedReader.readLine()) != null) {
               this.hienThiMain.xuLiTinNhan(this.tinNhanTuServer);
            }
         }
      } catch (UnknownHostException var2) {
         this.hienThiMain.ketNoiLai();
      } catch (IOException var3) {
         this.hienThiMain.ketNoiLai();
      }

   }
}
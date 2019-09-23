package server.giaodien;

import java.util.Calendar;
import java.util.TimerTask;

class DongHoSuKien extends TimerTask {
   GiaoDienChinh giaoDienChinh;
   int tongThoiGian = 0;
   int demThoiGian;

   public DongHoSuKien(GiaoDienChinh giaoDienChinh, int tongThoiGian) {
      this.giaoDienChinh = giaoDienChinh;
      this.tongThoiGian = tongThoiGian;
      this.demThoiGian = 0;
   }

   public void run() {
      ++this.demThoiGian;
      Calendar calendar = Calendar.getInstance();
      System.out.println(calendar.get(13) + " " + calendar.get(14));
      this.giaoDienChinh.tangThoiGian();
      if (this.demThoiGian >= this.tongThoiGian) {
         this.cancel();
         this.giaoDienChinh.inKetQua();
      }

   }
}
    
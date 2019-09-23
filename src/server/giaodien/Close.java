   package server.giaodien;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class Close extends WindowAdapter {
   GiaoDienTuyChon giaoDienTuyChon;
   GiaoDienChinh giaoDienChinh;

   public Close(GiaoDienTuyChon giaoDienTuyChon, GiaoDienChinh giaoDienChinh) {
      this.giaoDienTuyChon = giaoDienTuyChon;
      this.giaoDienChinh = giaoDienChinh;
   }

   public void windowClosing(WindowEvent e) {
      this.giaoDienTuyChon.anDi();
      this.giaoDienChinh.hienThi();
   }
}
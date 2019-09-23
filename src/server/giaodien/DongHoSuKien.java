
package server.giaodien;

import java.util.Calendar;
import java.util.TimerTask;

class DongHoSuKien extends TimerTask
{
    GiaoDienChinh giaoDienChinh;
    int tongThoiGian;
    int demThoiGian;
    
    public DongHoSuKien(final GiaoDienChinh giaoDienChinh, final int tongThoiGian) {
        this.tongThoiGian = 0;
        this.giaoDienChinh = giaoDienChinh;
        this.tongThoiGian = tongThoiGian;
        this.demThoiGian = 0;
    }
    
    @Override
    public void run() {
        ++this.demThoiGian;
        final Calendar calendar = Calendar.getInstance();
        System.out.println(String.valueOf(calendar.get(13)) + " " + calendar.get(14));
        this.giaoDienChinh.tangThoiGian();
        if (this.demThoiGian >= this.tongThoiGian) {
            this.cancel();
            this.giaoDienChinh.inKetQua();
        }
    }
}

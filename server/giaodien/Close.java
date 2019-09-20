// 
// Decompiled by Procyon v0.5.36
// 

package server.giaodien;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

class Close extends WindowAdapter
{
    GiaoDienTuyChon giaoDienTuyChon;
    GiaoDienChinh giaoDienChinh;
    
    public Close(final GiaoDienTuyChon giaoDienTuyChon, final GiaoDienChinh giaoDienChinh) {
        this.giaoDienTuyChon = giaoDienTuyChon;
        this.giaoDienChinh = giaoDienChinh;
    }
    
    @Override
    public void windowClosing(final WindowEvent e) {
        this.giaoDienTuyChon.anDi();
        this.giaoDienChinh.hienThi();
    }
}

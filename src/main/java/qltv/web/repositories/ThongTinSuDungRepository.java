package qltv.web.repositories;

import java.util.Date;
import java.util.List;
import qltv.web.models.ThongTinSuDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ThongTinSuDungRepository extends JpaRepository<ThongTinSuDung, Long> {

    @Query("SELECT ttsd FROM ThongTinSuDung ttsd RIGHT JOIN ttsd.thietBi tb WHERE tb.maTB = :maTB AND DATE(ttsd.tgMuon) = DATE(:tgMuon) AND ttsd.tgVao IS NOT NULL")
    public List<ThongTinSuDung> findAllByThietBiMaTBAndTgMuon(int maTB, Date tgMuon);

    @Query("SELECT ttsd FROM ThongTinSuDung ttsd WHERE (CAST(ttsd.thanhVien.maTV AS string) LIKE %:query% OR CAST(ttsd.thietBi.maTB AS string) LIKE %:query%) AND ttsd.thietBi.maTB IS NOT NULL")
    List<ThongTinSuDung> searchThongTinSuDung(@Param("query") String query);

    @Query("SELECT ttsd FROM ThongTinSuDung ttsd WHERE ttsd.tgVao IS NULL")
    List<ThongTinSuDung> findAllttsdMuonTra();

    @Query("SELECT ttsd FROM ThongTinSuDung ttsd WHERE (DATE(ttsd.tgDatCho) = CURRENT_DATE OR (ttsd.tgMuon IS NOT NULL AND ttsd.tgTra IS NULL)) AND ttsd.thietBi.maTB IS NOT NULL")
    List<ThongTinSuDung> findTtsdSoHuuThietBiDangBan();

    @Query("SELECT ttsd FROM ThongTinSuDung ttsd WHERE (DATE(ttsd.tgDatCho) = CURRENT_DATE OR (ttsd.tgMuon IS NOT NULL AND ttsd.tgTra IS NULL)) AND ttsd.thietBi.maTB IS NOT NULL AND CAST(ttsd.thietBi.maTB AS string) LIKE %:query%")
    List<ThongTinSuDung> SearchTtsdSoHuuThietBiDangBan(@Param("query") String query);

    @Query("SELECT MAX(ttsd.maTT) FROM ThongTinSuDung ttsd")
    Long getMaxMaTT();

    @Query("SELECT COUNT(ttsd) FROM ThongTinSuDung ttsd WHERE ttsd.thietBi.maTB = :maTB AND ttsd.tgMuon IS NOT NULL AND ttsd.tgTra IS NULL")
    int thietBiDangDuocMuon(@Param("maTB") int maTB);

    @Query("SELECT COUNT(ttsd) FROM ThongTinSuDung ttsd WHERE ttsd.thietBi.maTB = :maTB AND DATE(ttsd.tgDatCho) = CURRENT_DATE")
    int thietBiDuocDatChoTrongNgay(@Param("maTB") int maTB);

    // hàm này lấy tất cả các thông tin sử dụng có tgDatCho < hiện tại
    @Query("SELECT ttsd FROM ThongTinSuDung ttsd WHERE ttsd.tgDatCho < CURRENT_TIMESTAMP")
    List<ThongTinSuDung> findAllttsdDatChoQuaHan();

    @Query("SELECT ttsd FROM ThongTinSuDung ttsd WHERE ttsd.tgMuon IS NOT NULL AND ttsd.tgTra IS NULL")
    List<ThongTinSuDung> findAllttsdChuaTra();
    
    @Query("SELECT ttsd FROM ThongTinSuDung ttsd WHERE ttsd.tgMuon IS NOT NULL AND ttsd.tgTra IS NULL AND CAST(ttsd.thietBi.maTB AS string) LIKE %:query%")
    List<ThongTinSuDung> SearchTtsdSoHuuThietBiDangMuon(@Param("query") String query);
    
    ThongTinSuDung findByMaTT(long maTT);
    
    @Query("SELECT ttsd FROM ThongTinSuDung ttsd RIGHT JOIN ttsd.thietBi tb WHERE tb.maTB = :maTB")
    List<ThongTinSuDung> getTtsdByMaTB(long maTB);
    
    @Query("SELECT COUNT(*) + 1 FROM ThongTinSuDung ttsd")
    int getMaxIdFlusOne();
}

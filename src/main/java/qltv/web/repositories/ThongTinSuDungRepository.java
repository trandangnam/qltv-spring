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
    
    @Query("SELECT ttsd FROM ThongTinSuDung ttsd RIGHT JOIN ttsd.thietBi tb WHERE tb.maTB = :maTB")
    List<ThongTinSuDung> getTtsdByMaTB(long maTB);
    
    @Query("SELECT COUNT(*) + 1 FROM ThongTinSuDung ttsd")
    int getMaxIdFlusOne();
}

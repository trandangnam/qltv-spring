package qltv.web.repositories;

import java.util.Date;
import java.util.List;
import qltv.web.models.ThongTinSuDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ThongTinSuDungRepository extends JpaRepository<ThongTinSuDung, Long> {

    @Query("SELECT ttsd FROM ThongTinSuDung ttsd RIGHT JOIN ttsd.thietBi tb WHERE tb.maTB = :maTB AND DATE(ttsd.tgMuon) = DATE(:tgMuon)")
    public List<ThongTinSuDung> findAllByThietBiMaTBAndTgMuon(int maTB, Date tgMuon);
}

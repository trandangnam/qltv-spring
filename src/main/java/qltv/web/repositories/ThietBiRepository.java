package qltv.web.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import qltv.web.models.ThietBi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ThietBiRepository extends JpaRepository<ThietBi, Long> {

    Page<ThietBi> findByTenTBContaining(String tenTB, Pageable pageable);

    @Query("SELECT tb FROM ThietBi tb")
    List<ThietBi> listThietBi();
    @Query("SELECT tb FROM ThietBi tb WHERE LOWER(tb.tenTB) LIKE LOWER(CONCAT('%', :tenTB, '%'))")
    List<ThietBi> findByTenTB(String tenTB);//Không phân biệt hoa thường
    
    ThietBi findByMaTB(long maTB);


    
    ThietBi findByMaTB(long maTB);
}

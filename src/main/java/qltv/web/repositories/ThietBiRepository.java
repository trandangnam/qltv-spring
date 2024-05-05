package qltv.web.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import qltv.web.models.ThietBi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ThietBiRepository extends JpaRepository<ThietBi, Long> {

    Page<ThietBi> findByTenTBContaining(String tenTB, Pageable pageable);



    @Query("SELECT tb FROM ThietBi tb WHERE tb.tenTB LIKE CONCAT('%', :query, '%')")
    List<ThietBi> searchThietBi(String query);

    ThietBi findByMaTB(int maTB);

    @Query("SELECT tb FROM ThietBi tb WHERE tb.maTB = :username")
    ThietBi findFirstByMaTB(String username);

    public void deleteThietBi(int maTB);
    


    
    ThietBi findByMaTB(long maTB);
}

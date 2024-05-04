package qltv.web.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import qltv.web.models.ThietBi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThietBiRepository extends JpaRepository<ThietBi, Long> {

    Page<ThietBi> findByTenTBContaining(String tenTB, Pageable pageable);
    
    ThietBi findByMaTB(long maTB);
}

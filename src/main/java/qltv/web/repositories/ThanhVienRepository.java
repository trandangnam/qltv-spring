package qltv.web.repositories;

import java.util.List;
import qltv.web.models.ThanhVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ThanhVienRepository extends JpaRepository<ThanhVien, Long> {

    @Query("SELECT tv FROM ThanhVien tv WHERE tv.hoTen LIKE CONCAT('%', :query, '%')")
    List<ThanhVien> searchThanhVien(String query);

    ThanhVien findByEmail(String email);

    ThanhVien findByMaTV(long maTV);

    @Query("SELECT tv FROM ThanhVien tv WHERE tv.maTV = :username")
    ThanhVien findFirstByMaTV(String username);
    
    @Query("SELECT tv FROM ThanhVien tv WHERE tv.nganh = :nganh")
    List<ThanhVien> findFirstByNganh(String nganh);
    
}

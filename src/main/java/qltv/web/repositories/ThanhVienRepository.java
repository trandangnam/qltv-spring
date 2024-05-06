package qltv.web.repositories;

import jakarta.transaction.Transactional;
import java.util.List;
import qltv.web.models.ThanhVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import qltv.web.dto.ThanhVienInfoDTO;

public interface ThanhVienRepository extends JpaRepository<ThanhVien, Long> {

    @Query("SELECT tv FROM ThanhVien tv WHERE tv.hoTen LIKE CONCAT('%', :query, '%')")
    List<ThanhVien> searchThanhVien(String query);

    ThanhVien findByEmail(String email);

    ThanhVien findByMaTV(long maTV);

    @Query("SELECT tv FROM ThanhVien tv WHERE tv.maTV = :username")
    ThanhVien findFirstByMaTV(String username);

    @Query("SELECT tv FROM ThanhVien tv WHERE tv.nganh = :nganh")
    List<ThanhVien> findFirstByNganh(String nganh);

    @Modifying
    @Transactional
    @Query("UPDATE ThanhVien tv SET tv.hoTen = :hoTen, tv.khoa = :khoa, tv.nganh = :nganh, tv.sdt = :sdt, tv.email = :email WHERE tv.maTV = :maTV")
    void updateInfoThanhVien(long maTV, String hoTen, String khoa, String nganh, String sdt, String email);

    @Modifying
    @Transactional
    @Query("UPDATE ThanhVien tv SET tv.password = :password WHERE tv.maTV = :maTV")
    void updatePassword(long maTV, String password);
    
    @Modifying
    @Transactional
    @Query("UPDATE ThanhVien tv SET tv.password = :password WHERE tv.email = :email")
    void changePasswordByEmail(String email, String password);
    
    @Query("SELECT tv.password FROM ThanhVien tv WHERE tv.maTV = :maTV")
    String findPasswordByMaTV(@Param("maTV") long maTV);
    
    boolean existsByEmail(String email);

}

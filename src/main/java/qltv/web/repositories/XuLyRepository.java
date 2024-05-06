package qltv.web.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import qltv.web.models.XuLy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface XuLyRepository extends JpaRepository<XuLy, Long> { // trong đây hỗ trợ 4 hàm crud rồi chỉ cần viết thêm mấy hàm cần zo thôi
   @Query("SELECT xuLy FROM XuLy xuLy WHERE CAST(xuLy.thanhVien.maTV AS string) LIKE %:query%")
    List<XuLy> searchXuLy(@Param("query") String query);
    
    XuLy findByMaXL(long maXuLy);

    @Query("SELECT MAX(xuLy.maXL) FROM XuLy xuLy")
    Long getMaxMaXL();

    @Query("SELECT xuLy FROM XuLy xuLy WHERE xuLy.hinhThucXL LIKE '%tháng%'")
    List<XuLy> findXuLyCoKhoaThe();

    @Query("SELECT COUNT(xuLy) FROM XuLy xuLy WHERE xuLy.thanhVien.maTV = :maTV AND xuLy.trangThaiXL = 1")
    int thanhVienDangBiXuLy(@Param("maTV") long maTV);

    @Query("SELECT xuLy FROM XuLy xuLy WHERE CAST(xuLy.thanhVien.maTV AS string) LIKE %:query%")
    Page<XuLy> getListXuLy(String query, Pageable pageable);
}

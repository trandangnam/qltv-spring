package qltv.web.repositories;

import java.util.List;
import qltv.web.models.XuLy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface XuLyRepository extends JpaRepository<XuLy, Long> { // trong đây hỗ trợ 4 hàm crud rồi chỉ cần viết thêm mấy hàm cần zo thôi
   @Query("SELECT xuLy FROM XuLy xuLy WHERE CAST(xuLy.thanhVien.maTV AS string) LIKE %:query%")
    List<XuLy> searchXuLy(@Param("query") String query);
    
    XuLy findByMaXL(long maXuLy);
}

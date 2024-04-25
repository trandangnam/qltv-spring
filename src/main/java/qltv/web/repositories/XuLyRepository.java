package qltv.web.repositories;

import java.util.List;
import qltv.web.models.XuLy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface XuLyRepository extends JpaRepository<XuLy, Long> { // trong đây hỗ trợ 4 hàm crud rồi chỉ cần viết thêm mấy hàm cần zo thôi
   @Query("SELECT tv FROM XuLy xl WHERE xl.MaTV LIKE CONCAT('%', :query, '%')")
    List<XuLy> searchXuLy(String query);

    XuLy findByMaXL(long maXuLy);
}

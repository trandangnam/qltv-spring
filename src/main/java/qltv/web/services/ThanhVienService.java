package qltv.web.services;

import qltv.web.dto.ThanhVienDTO;
import java.util.List;
import org.springframework.data.repository.query.Param;
import qltv.web.dto.ThanhVienInfoDTO;
import qltv.web.dto.ThanhVienResponse;
import qltv.web.models.ThanhVien;

public interface ThanhVienService {

    public List<ThanhVienDTO> getAllThanhVien();

    public ThanhVien saveThanhVien(ThanhVienDTO thanhVien);

//    public ThanhVienInfo updateProfile(long maTV, String hoTen, String khoa, String nganh, String sdt, String email);
    public ThanhVienDTO findMemberById(long maTV);

    public void updateThanhVien(ThanhVienDTO thanhVien);

    public void updateInfoThanhVien(long maTV, String hoTen, String khoa, String nganh, String sdt, String email);
    
    public String findPasswordByMaTV(@Param("maTV") long maTV);

    public void updatePassword(long maTV, String newPassword);
    
    public void changePasswordByEmail(String email, String password);

    public boolean existsByEmail(String email);
    
    public ThanhVien findByEmail(String email);

    public void deleteThanhVien(long maTV);

    public List<ThanhVienDTO> searchThanhVien(String query);

    public List<ThanhVienDTO> findMemberByNganh(String nganh);
    
    public ThanhVienResponse getListThanhVien(int pageNo, int pageSize, String query);
}

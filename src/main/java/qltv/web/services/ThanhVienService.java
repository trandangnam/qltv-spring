package qltv.web.services;

import qltv.web.dto.ThanhVienDTO;
import java.util.List;
import qltv.web.models.ThanhVien;

public interface ThanhVienService {

    public List<ThanhVienDTO> getAllThanhVien();

    public ThanhVien saveThanhVien(ThanhVienDTO thanhVien);

    public ThanhVienDTO findMemberById(long maTV);

    public void updateThanhVien(ThanhVienDTO thanhVien);

    public void deleteThanhVien(long maTV);

    public List<ThanhVienDTO> searchThanhVien(String query);
}

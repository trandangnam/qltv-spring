package qltv.web.services;

import java.util.List;
import qltv.web.dto.XuLyDTO;
import qltv.web.models.XuLy;

public interface XuLyService {

    public List<XuLyDTO> getAllXuLy();

    public XuLy saveXuLy(XuLyDTO xuLy);

    public XuLyDTO findXuLyByMaXL(long maXL);

    public void updateXuLy(XuLyDTO xuLy);

    public void deleteXuLy(long maXL);

    public List<XuLyDTO> searchXuLy(String query);

    public Long getMaxMaXL();

    public String validateXuLy(String maTV, String hinhThucXL, String soTien);

    public void updateXuLyDuThoiGian();

    public boolean thanhVienDangBiXuLy(long maTV);

}

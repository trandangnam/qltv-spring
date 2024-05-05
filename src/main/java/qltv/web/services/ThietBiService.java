package qltv.web.services;

import java.util.List;
import qltv.web.dto.ThietBiDTO;
import qltv.web.dto.ThietBiResponse;

public interface ThietBiService {

    public ThietBiResponse findThietBiMuonTrongNgay(int pageNo, int pageSize, String query);
    public List<ThietBiDTO> getAllThietBi();
    public List<ThietBiDTO> searchThietBi(String tenTB);
    public ThietBiDTO findByMaTB(long maTB);
}

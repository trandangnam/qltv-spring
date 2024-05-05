package qltv.web.services;

import java.util.List;
import qltv.web.dto.ThietBiDTO;
import qltv.web.dto.ThietBiResponse;
import qltv.web.models.ThietBi;

public interface ThietBiService {

    public ThietBiResponse findThietBiMuonTrongNgay(int pageNo, int pageSize, String query);
    public List<ThietBiDTO> getAllThietBi();
    public List<ThietBiDTO> searchThietBi(String tenTB);
    public ThietBiDTO findByMaTB(long maTB);
    

    public List<ThietBiDTO> getAllThietBi();

    public ThietBi saveThietBi(ThietBiDTO thietBi);

    public ThietBiDTO findThietBiById(int maTV);

    public void updateThietBi(ThietBiDTO thietBi);

    public void deleteThietBi(int maTV);

    public List<ThietBiDTO> searchThietBi(String query);
    

    public ThietBiDTO findThietBiById(long maTB);

}

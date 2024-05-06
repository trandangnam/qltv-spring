package qltv.web.services;

import java.util.List;
import qltv.web.dto.ThietBiDTO;
import qltv.web.dto.ThietBiResponse;
import qltv.web.models.ThietBi;

public interface ThietBiService {

    public ThietBiResponse findThietBiDatChoTrongNgay(int pageNo, int pageSize, String query);
    public List<ThietBiDTO> getAllThietBi();
    public ThietBiDTO findByMaTB(long maTB);
    
    public ThietBi saveThietBi(ThietBiDTO thietBi);

    public ThietBiDTO findThietBiById(int maTV);

    public void updateThietBi(ThietBiDTO thietBi);

    public void deleteThietBi(int maTB);

    public List<ThietBiDTO> searchThietBi(String query);
    

    public ThietBiDTO findThietBiById(long maTB);

}

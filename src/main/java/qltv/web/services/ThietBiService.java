package qltv.web.services;

import qltv.web.dto.ThietBiDTO;
import qltv.web.dto.ThietBiResponse;

public interface ThietBiService {

    public ThietBiResponse findThietBiMuonTrongNgay(int pageNo, int pageSize, String query);
    
    public ThietBiDTO findThietBiById(long maTB);
}

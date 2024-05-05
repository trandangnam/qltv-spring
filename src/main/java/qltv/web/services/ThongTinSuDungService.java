package qltv.web.services;

import java.util.List;
import qltv.web.dto.ThongTinSuDungDTO;
import qltv.web.models.ThongTinSuDung;

public interface ThongTinSuDungService {
    
    public List<ThongTinSuDungDTO> getAllThongTinSuDung();
    
    public ThongTinSuDung saveThongTinSuDung(ThongTinSuDungDTO ttsdDTO);

    public void updateThongTinSuDung(ThongTinSuDungDTO ttsdDTO);

    public void deleteThongTinSuDung(long maTTSD);

    public List<ThongTinSuDungDTO> searchThongTinSuDung(String query);
    
    public List<ThongTinSuDungDTO> getTtsdByMaTB(long maTB);
    
    public int getMaxIdFlusOne();
}

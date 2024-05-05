package qltv.web.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import qltv.web.dto.ThongTinSuDungDTO;
import qltv.web.mappers.ThongTinSuDungMapper;
import qltv.web.models.ThongTinSuDung;
import qltv.web.repositories.ThanhVienRepository;
import qltv.web.repositories.ThietBiRepository;
import qltv.web.repositories.ThongTinSuDungRepository;
import qltv.web.services.ThongTinSuDungService;

@Service
public class ThongTinSuDungServiceImpl implements ThongTinSuDungService {

    private ThongTinSuDungRepository thongTinSuDungRepository;
    private ThanhVienRepository thanhVienReponsitory;
    private ThietBiRepository thietBiReponsitory;

    public ThongTinSuDungServiceImpl(ThongTinSuDungRepository thongTinSuDungRepository, ThanhVienRepository thanhVienReponsitory, ThietBiRepository thietBiReponsitory) {
        this.thongTinSuDungRepository = thongTinSuDungRepository;
        this.thanhVienReponsitory = thanhVienReponsitory;
        this.thietBiReponsitory = thietBiReponsitory;
    }

    @Override
    public List<ThongTinSuDungDTO> getAllThongTinSuDung() {
        List<ThongTinSuDung> thongTinSuDungs = (ArrayList) thongTinSuDungRepository.findAllttsdMuonTra();
        return thongTinSuDungs.stream().map(thongTinSuDung -> ThongTinSuDungMapper.mapToThongTinSuDungDTO(thongTinSuDung)).collect(Collectors.toList());
    }

    @Override
    public ThongTinSuDung saveThongTinSuDung(ThongTinSuDungDTO ttsdDTO) {
        ThongTinSuDung ttsd = ThongTinSuDungMapper.mapToThongTinSuDung(ttsdDTO);
        return thongTinSuDungRepository.save(ttsd);
    }

    @Override
    public void updateThongTinSuDung(ThongTinSuDungDTO ttsdDTO) {
        ThongTinSuDung ttsd = ThongTinSuDungMapper.mapToThongTinSuDung(ttsdDTO);
        thongTinSuDungRepository.save(ttsd);
    }

    @Override
    public void deleteThongTinSuDung(long maTTSD) {
        thongTinSuDungRepository.deleteById(maTTSD);
    }

    @Override
    public List<ThongTinSuDungDTO> searchThongTinSuDung(String query) {
        List<ThongTinSuDung> thongTinSuDungs = (ArrayList) thongTinSuDungRepository.searchThongTinSuDung(query);
        return thongTinSuDungs.stream().map(thongTinSuDung -> ThongTinSuDungMapper.mapToThongTinSuDungDTO(thongTinSuDung)).collect(Collectors.toList());
    }
    @Override
    public List<ThongTinSuDungDTO> getTtsdByMaTB(long maTB){
        List<ThongTinSuDung> ttsds = thongTinSuDungRepository.getTtsdByMaTB(maTB);
        return ttsds.stream().map(ttsd -> ThongTinSuDungMapper.mapToThongTinSuDungDTO(ttsd)).collect(Collectors.toList());
    }
    @Override
    public int getMaxIdFlusOne(){
        return thongTinSuDungRepository.getMaxIdFlusOne();
    }
}

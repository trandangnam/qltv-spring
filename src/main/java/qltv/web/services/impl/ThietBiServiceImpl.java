package qltv.web.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import qltv.web.dto.ThietBiDTO;
import qltv.web.dto.ThietBiResponse;
import qltv.web.mappers.ThietBiMapper;
import qltv.web.models.ThietBi;
import qltv.web.models.ThongTinSuDung;
import qltv.web.repositories.ThietBiRepository;
import qltv.web.repositories.ThongTinSuDungRepository;
import qltv.web.services.ThietBiService;

@Service
public class ThietBiServiceImpl implements ThietBiService {

    private ThietBiRepository tbRepository;
    private ThongTinSuDungRepository ttsdRepository;

    @Autowired
    public ThietBiServiceImpl(ThietBiRepository tbRepository, ThongTinSuDungRepository ttsdRepository) {
        this.tbRepository = tbRepository;
        this.ttsdRepository = ttsdRepository;
    }

    @Override
    public ThietBiResponse findThietBiMuonTrongNgay(int pageNo, int pageSize, String query) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<ThietBi> result = tbRepository.findByTenTBContaining(query, pageable);
        List<ThietBi> listThietBi = result.getContent();
        Date today = new Date();
        for (ThietBi tb : listThietBi) {
            List<ThongTinSuDung> listTTSD = ttsdRepository.findAllByThietBiMaTBAndTgMuon(tb.getMaTB(), today);
            tb.setThongTinSuDung(listTTSD);
        }
        List<ThietBiDTO> content = listThietBi.stream()
                .map(tb -> ThietBiMapper.mapToThietBiDto(tb))
                .collect(Collectors.toList());

        ThietBiResponse response = new ThietBiResponse();
        response.setContent(content);
        response.setPageNo(pageNo + 1);
        response.setPageSize(pageSize);
        response.setTotalElements(result.getTotalElements());
        response.setTotalPages(result.getTotalPages());
        return response;
    }
}

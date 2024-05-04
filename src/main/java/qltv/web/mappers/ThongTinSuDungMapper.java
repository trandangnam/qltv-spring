package qltv.web.mappers;

import qltv.web.dto.ThongTinSuDungDTO;
import qltv.web.models.ThongTinSuDung;

public class ThongTinSuDungMapper {

    public static ThongTinSuDung mapToThongTinSuDung(ThongTinSuDungDTO ttsd) {
        if (ttsd == null) {
            return new ThongTinSuDung();
        }
        ThongTinSuDung model = ThongTinSuDung.builder()
                .maTT(ttsd.getMaTT())
                .thanhVien(ThanhVienMapper.mapToThanhVien(ttsd.getThanhVien()))
                .thietBi(ThietBiMapper.mapToThietBi(ttsd.getThietBi()))
                .tgVao(ttsd.getTgVao())
                .tgMuon(ttsd.getTgMuon())
                .tgTra(ttsd.getTgTra())
                .tgDatCho(ttsd.getTgDatCho())
                .build();
        return model;
    }

    public static ThongTinSuDungDTO mapToThongTinSuDungDTO(ThongTinSuDung ttsd) {
        if (ttsd == null) {
            return new ThongTinSuDungDTO();
        }
        ThongTinSuDungDTO dto = ThongTinSuDungDTO.builder()
                .maTT(ttsd.getMaTT())
                .thanhVien(ThanhVienMapper.mapToThanhVienDto(ttsd.getThanhVien()))
                .thietBi(ThietBiMapper.mapToThietBiDto(ttsd.getThietBi()))
                .tgVao(ttsd.getTgVao())
                .tgMuon(ttsd.getTgMuon())
                .tgTra(ttsd.getTgTra())
                .tgDatCho(ttsd.getTgDatCho())
                .build();
        return dto;
    }

    public static ThongTinSuDungDTO mapToThongTinSuDungDtoExceptForeignKeys(ThongTinSuDung ttsd) {
        if (ttsd == null) {
            return new ThongTinSuDungDTO();
        }
        ThongTinSuDungDTO dto = ThongTinSuDungDTO.builder()
                .maTT(ttsd.getMaTT())
                .tgVao(ttsd.getTgVao())
                .tgMuon(ttsd.getTgMuon())
                .tgTra(ttsd.getTgTra())
                .tgDatCho(ttsd.getTgDatCho())
                .build();
        return dto;
    }
}

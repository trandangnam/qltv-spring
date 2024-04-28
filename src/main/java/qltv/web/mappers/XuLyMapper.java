package qltv.web.mappers;

import qltv.web.dto.XuLyDTO;
import qltv.web.models.XuLy;

public class XuLyMapper {

    public static XuLy mapToXuLy(XuLyDTO xuLyDTO) {
        if (xuLyDTO == null) {
            return new XuLy();
        }
        XuLy model = XuLy.builder()
                .maXL(xuLyDTO.getMaXL())
                .thanhVien(ThanhVienMapper.mapToThanhVien(xuLyDTO.getThanhVien()))
                .hinhThucXL(xuLyDTO.getHinhThucXL())
                .soTien(xuLyDTO.getSoTien())
                .ngayXL(xuLyDTO.getNgayXL())
                .trangThaiXL(xuLyDTO.getTrangThaiXL())
                .build();
        return model;
    }

    public static XuLyDTO mapToXuLyDTO(XuLy xuLy) {
        if (xuLy == null) {
            return new XuLyDTO();
        }
        XuLyDTO dto = XuLyDTO.builder()
                .maXL(xuLy.getMaXL())
                .thanhVien(ThanhVienMapper.mapToThanhVienDto(xuLy.getThanhVien()))
                .hinhThucXL(xuLy.getHinhThucXL())
                .soTien(xuLy.getSoTien())
                .ngayXL(xuLy.getNgayXL())
                .trangThaiXL(xuLy.getTrangThaiXL())
                .build();
        return dto;
    }
}

package qltv.web.mappers;

import qltv.web.dto.ThanhVienDTO;
import qltv.web.dto.ThanhVienInfoDTO;
import qltv.web.models.ThanhVien;

public class ThanhVienMapper {

    public static ThanhVien mapToThanhVien(ThanhVienDTO tv) {
        if (tv == null) {
            return new ThanhVien();
        }
        ThanhVien model = ThanhVien.builder()
                .maTV(tv.getMaTV())
                .hoTen(tv.getHoTen())
                .khoa(tv.getKhoa())
                .nganh(tv.getNganh())
                .sdt(tv.getSdt())
                .password(tv.getPassword())
                .email(tv.getEmail())
                .build();
        return model;
    }

    public static ThanhVienDTO mapToThanhVienDto(ThanhVien tv) {
        if (tv == null) {
            return new ThanhVienDTO();
        }
        ThanhVienDTO dto = ThanhVienDTO.builder()
                .maTV(tv.getMaTV())
                .hoTen(tv.getHoTen())
                .khoa(tv.getKhoa())
                .nganh(tv.getNganh())
                .sdt(tv.getSdt())
                .password(tv.getPassword())
                .email(tv.getEmail())
                .build();
        return dto;
    }

}

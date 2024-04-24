package qltv.web.services.impl;

import qltv.web.dto.ThanhVienDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import qltv.web.models.ThanhVien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import qltv.web.repositories.ThanhVienRepository;
import qltv.web.services.ThanhVienService;

@Service
public class ThanhVienServiceImpl implements ThanhVienService {

    private ThanhVienRepository tvRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public ThanhVienServiceImpl(ThanhVienRepository tvRepository, PasswordEncoder passwordEncoder) {
        this.tvRepository = tvRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<ThanhVienDTO> getAllThanhVien() {
        List<ThanhVien> members = (ArrayList) tvRepository.findAll();
        return members.stream().map(member -> mapToThanhVienDto(member)).collect(Collectors.toList());
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

    @Override
    public ThanhVien saveThanhVien(ThanhVienDTO tv) {
        tv.setPassword(passwordEncoder.encode(tv.getPassword()));
        ThanhVien thanhVien = mapToThanhVien(tv);
        return tvRepository.save(thanhVien);
    }

    @Override
    public ThanhVienDTO findMemberById(long maTV) {
        ThanhVien thanhVien = tvRepository.findByMaTV(maTV);
        return mapToThanhVienDto(thanhVien);
    }

    @Override
    public void updateThanhVien(ThanhVienDTO thanhVienDto) {
        thanhVienDto.setPassword(passwordEncoder.encode(thanhVienDto.getPassword()));
        ThanhVien thanhVien = mapToThanhVien(thanhVienDto);
        tvRepository.save(thanhVien);
    }

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

    @Override
    public void deleteThanhVien(long maTV) {
        tvRepository.deleteById(maTV);
    }

    @Override
    public List<ThanhVienDTO> searchThanhVien(String query) {
        List<ThanhVien> listThanhVien = tvRepository.searchThanhVien(query);
        return listThanhVien.stream().map(thanhVien -> mapToThanhVienDto(thanhVien)).collect(Collectors.toList());
    }
}

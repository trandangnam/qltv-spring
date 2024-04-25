package qltv.web.services.impl;

import qltv.web.dto.ThanhVienDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import qltv.web.models.ThanhVien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import qltv.web.mappers.ThanhVienMapper;
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
        return members.stream().map(member -> ThanhVienMapper.mapToThanhVienDto(member)).collect(Collectors.toList());
    }

    @Override
    public ThanhVien saveThanhVien(ThanhVienDTO tv) {
        tv.setPassword(passwordEncoder.encode(tv.getPassword()));
        ThanhVien thanhVien = ThanhVienMapper.mapToThanhVien(tv);
        return tvRepository.save(thanhVien);
    }

    @Override
    public ThanhVienDTO findMemberById(long maTV) {
        ThanhVien thanhVien = tvRepository.findByMaTV(maTV);
        return ThanhVienMapper.mapToThanhVienDto(thanhVien);
    }

    @Override
    public void updateThanhVien(ThanhVienDTO thanhVienDto) {
        thanhVienDto.setPassword(passwordEncoder.encode(thanhVienDto.getPassword()));
        ThanhVien thanhVien = ThanhVienMapper.mapToThanhVien(thanhVienDto);
        tvRepository.save(thanhVien);
    }

    @Override
    public void deleteThanhVien(long maTV) {
        tvRepository.deleteById(maTV);
    }

    @Override
    public List<ThanhVienDTO> searchThanhVien(String query) {
        List<ThanhVien> listThanhVien = tvRepository.searchThanhVien(query);
        return listThanhVien.stream().map(thanhVien -> ThanhVienMapper.mapToThanhVienDto(thanhVien)).collect(Collectors.toList());
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qltv.web.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qltv.web.dto.XuLyDTO;
import qltv.web.mappers.XuLyMapper;
import qltv.web.models.XuLy;
import qltv.web.repositories.ThanhVienRepository;
import qltv.web.repositories.XuLyRepository;
import qltv.web.services.XuLyService;

/**
 *
 * @author Acer
 */
@Service
public class XuLyServiceImpl implements XuLyService {
    XuLyRepository xuLyRepository;
    ThanhVienRepository thanhVienRepository;

    @Autowired
    public XuLyServiceImpl(XuLyRepository xuLyReponsitory, ThanhVienRepository thanhVienRepository) {
        this.xuLyRepository = xuLyReponsitory;
        this.thanhVienRepository = thanhVienRepository;
    }

    @Override
    public List<XuLyDTO> getAllXuLy() {
        List<XuLy> xuLys = (ArrayList) xuLyRepository.findAll();
        return xuLys.stream().map(xuLy -> XuLyMapper.mapToXuLyDTO(xuLy)).collect(Collectors.toList());
    }



    @Override
    public XuLy saveXuLy(XuLyDTO xuLyDTO) {
        XuLy xuLy = XuLyMapper.mapToXuLy(xuLyDTO);
        return xuLyRepository.save(xuLy);
    }

    @Override
    public XuLyDTO findXuLyByMaXL(long maXL) {
        XuLy xuLy = xuLyRepository.findByMaXL(maXL);
        return XuLyMapper.mapToXuLyDTO(xuLy);
    }

    @Override
    public void updateXuLy(XuLyDTO xuLyDTO) {
        XuLy xuLy = XuLyMapper.mapToXuLy(xuLyDTO);
        xuLyRepository.save(xuLy); // hàm save này có hỗ trợ rồi k cần viết lại trong repo
    }

    @Override
    public void deleteXuLy(long maXuLy) {
        xuLyRepository.deleteById(maXuLy);
    }

    @Override
    public List<XuLyDTO> searchXuLy(String maTV) {
        List<XuLy> xuLys = (ArrayList) xuLyRepository.searchXuLy(maTV);
        return xuLys.stream().map(xuLy -> XuLyMapper.mapToXuLyDTO(xuLy)).collect(Collectors.toList());
    }

    @Override
    public Long getMaxMaXL() {
        return xuLyRepository.getMaxMaXL();
    }

    @Override
    public String validateXuLy(String maTV, String hinhThucXL, String soTien) {
        String result = "";
        if (maTV == null || maTV.isEmpty() || maTV.trim().length() != 10 || !maTV.matches("[0-9]+")) {
            result = "Mã thành viên không hợp lệ,";
        } else if (thanhVienRepository.findByMaTV(Long.parseLong(maTV)) == null) {
            result += "Mã thành viên không tồn tại,";
        }
        if (hinhThucXL == null || hinhThucXL.isEmpty()) {
            result += "Hình thức xử lý không hợp lệ,";
        } else if (hinhThucXL.contains("oiThuong")) {
            if (soTien == null || soTien.isEmpty() || !soTien.matches("[0-9]+") || Integer.parseInt(soTien) < 10000 || Integer.parseInt(soTien) > 100000000) {
                result+= "Số tiền không hợp lệ";
            }
        }
        
        return result.isEmpty() ? "" : result.substring(0, result.length() - 1); //cắt cái dấu , cuối cùng
    }

}

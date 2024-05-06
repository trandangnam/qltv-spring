/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qltv.web.services.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import qltv.web.dto.XuLyDTO;
import qltv.web.dto.XuLyResponse;
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
                result += "Số tiền không hợp lệ";
            }
        }

        return result.isEmpty() ? "" : result.substring(0, result.length() - 1); //cắt cái dấu , cuối cùng
    }

    @Override
    public void updateXuLyDuThoiGian() {
        List<XuLy> xuLys = (ArrayList) xuLyRepository.findXuLyCoKhoaThe();
        List<XuLyDTO> xuLyDTOs = xuLys.stream().map(xuLy -> XuLyMapper.mapToXuLyDTO(xuLy)).collect(Collectors.toList());
        Date today = new Date(System.currentTimeMillis());
        for (XuLyDTO xuLyDTO : xuLyDTOs) {
            if (xuLyDTO.getHinhThucXL().contains("1") || xuLyDTO.getHinhThucXL().contains("2") || xuLyDTO.getHinhThucXL().contains("3")) {
                // Tính toán ngày kết thúc của thời gian khóa thẻ
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(xuLyDTO.getNgayXL());
                int soThangKhoaThe = 1;
                if (xuLyDTO.getHinhThucXL().contains("2")) {
                    soThangKhoaThe = 2;
                } else if (xuLyDTO.getHinhThucXL().contains("3")) {
                    soThangKhoaThe = 3;
                }
                calendar.add(Calendar.MONTH, soThangKhoaThe);
                Date ngayKetThuc = calendar.getTime();
                // Kiểm tra xem ngày hiện tại có vượt quá ngày kết thúc của thời gian khóa thẻ không
                if (today.after(ngayKetThuc)) {
                    xuLyDTO.setTrangThaiXL(0);
                    updateXuLy(xuLyDTO);
                }
            }
        }
    }

    @Override
    public boolean thanhVienDangBiXuLy(long maTV) {
        return xuLyRepository.thanhVienDangBiXuLy(maTV) > 0;
    }

    @Override
    public XuLyResponse getListXuLy(int pageNo, int pageSize, String query) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<XuLy> result = xuLyRepository.getListXuLy(query, pageable);
        List<XuLyDTO> content = result.getContent().stream()
                .map(xl -> XuLyMapper.mapToXuLyDTO(xl))
                .collect(Collectors.toList());

        XuLyResponse response = new XuLyResponse();
        response.setContent(content);
        response.setPageNo(pageNo + 1);
        response.setPageSize(pageSize);
        response.setTotalElements(result.getTotalElements());
        response.setTotalPages(result.getTotalPages());
        return response;
    }
}

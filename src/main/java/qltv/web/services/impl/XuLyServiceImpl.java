/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qltv.web.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import qltv.web.dto.XuLyDTO;
import qltv.web.models.XuLy;
import qltv.web.repositories.XuLyRepository;
import qltv.web.services.XuLyService;

/**
 *
 * @author Acer
 */
public class XuLyServiceImpl implements XuLyService{
    XuLyRepository xuLyRepository;
    
    @Autowired
    XuLyServiceImpl(XuLyRepository xuLyReponsitory){
        this.xuLyRepository = xuLyReponsitory;
    }
    
    @Override
    public List<XuLyDTO> getAllXuLy() {
        List<XuLy> xuLys = (ArrayList) xuLyRepository.findAll();
        return xuLys.stream().map(xuLy -> mapToXuLyDTO(xuLy)).collect(Collectors.toList());
    }

    public static XuLyDTO mapToXuLyDTO(XuLy xuLy) {
        if (xuLy == null) {
            return new XuLyDTO();
        }
        XuLyDTO dto = XuLyDTO.builder()
                .maXL(xuLy.getMaXL())
                .hinhThucXL(xuLy.getHinhThucXL())
                .soTien(xuLy.getSoTien())
                .ngayXL(xuLy.getNgayXL())
                .trangThaiXL(xuLy.getTrangThaiXL())
                .build();
        return dto;
    }

    @Override
    public XuLy saveXuLy(XuLyDTO xuLyDTO) {
        XuLy xuLy = mapToXuLy(xuLyDTO);
        return xuLyRepository.save(xuLy);
    }

    @Override
    public XuLyDTO findXuLyByMaXL(long maXL) {
        XuLy xuLy = xuLyRepository.findByMaXL(maXL);
        return mapToXuLyDTO(xuLy);
    }

    @Override
    public void updateXuLy(XuLyDTO xuLyDTO) {
        XuLy xuLy = mapToXuLy(xuLyDTO);
        xuLyRepository.save(xuLy); // hàm save này có hỗ trợ rồi k cần viết lại trong repo
    }

    public static XuLy mapToXuLy(XuLyDTO xuLyDTO) {
        if (xuLyDTO == null) {
            return new XuLy();
        }
        XuLy model = XuLy.builder()
                .maXL(xuLyDTO.getMaXL())
                .hinhThucXL(xuLyDTO.getHinhThucXL())
                .soTien(xuLyDTO.getSoTien())
                .ngayXL(xuLyDTO.getNgayXL())
                .trangThaiXL(xuLyDTO.getTrangThaiXL())
                .build();
        return model;
    }

    @Override
    public void deleteXuLy(long maXuLy) {
        xuLyRepository.deleteById(maXuLy);
    }

    @Override
    public List<XuLyDTO> searchXuLy(String query) {
        List<XuLy> xuLys = xuLyRepository.searchXuLy(query);
        return xuLys.stream().map(xuLy -> mapToXuLyDTO(xuLy)).collect(Collectors.toList());
    }
}

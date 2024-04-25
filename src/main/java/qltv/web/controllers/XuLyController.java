/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qltv.web.controllers;

import jakarta.validation.Valid;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import qltv.web.dto.ThanhVienDTO;
import qltv.web.dto.XuLyDTO;
import qltv.web.models.ThanhVien;
import qltv.web.security.SecurityUtil;
import qltv.web.services.ThanhVienService;
import qltv.web.services.XuLyService;
import qltv.web.services.impl.ThanhVienServiceImpl;

@Controller
public class XuLyController {

    private XuLyService xuLyService;
    private ThanhVienService thanhVienService;

    @Autowired
    public XuLyController(XuLyService xuLyService, ThanhVienService thanhVienService) {
        this.xuLyService = xuLyService;
        this.thanhVienService = thanhVienService;
    }

    @GetMapping("/xuly")
    public String listXuLy(Model model) {
        String username = SecurityUtil.getUserSession();
        if (username == null) { // kiểm tra đăng nhập
            return "redirect:/login";
        }
        Long maTV = Long.parseLong(username);
        ThanhVienDTO user = thanhVienService.findMemberById(maTV);
        List<XuLyDTO> xuLys = xuLyService.getAllXuLy();
        
        model.addAttribute("user", user);
        model.addAttribute("xuLys", xuLys);
        return "xu-ly-list";
    }
    
    @GetMapping("/xuly/new")
    public String CreateXuLy(Model model){
        String username = SecurityUtil.getUserSession();
        if (username == null) { // kiểm tra đăng nhập
            return "redirect:/login";
        }
        Long maTV = Long.parseLong(username);
        ThanhVienDTO user = thanhVienService.findMemberById(maTV);
        XuLyDTO xuLy = new XuLyDTO();
        Long nextMaXL = xuLyService.getMaxMaXL() + 1;
        List<ThanhVienDTO> thanhViens = thanhVienService.getAllThanhVien();
        
        model.addAttribute("user", user);
        model.addAttribute("xuLy", xuLy);
        model.addAttribute("nextMaXL", nextMaXL);
        model.addAttribute("thanhViens", thanhViens);
        
        return "xu-ly-create";
    }
    
    @PostMapping("/xuly/new")
    public String saveXuLy(@RequestParam("maXuLy") String maXuLy,
        @RequestParam("maThanhVien") String maThanhVien,
     @RequestParam("hinhThucXuLy") String hinhThucXuLy,
      @RequestParam("tienPhat") String tienPhat,
         @RequestParam("trangThai") String trangThai,
        Model model) {        
        String username = SecurityUtil.getUserSession();
        if (username == null) {
            return "redirect:/login";
        }

        String validate = xuLyService.validateXuLy(maThanhVien, hinhThucXuLy, tienPhat);
        if (!validate.isEmpty()) {
            model.addAttribute("error", validate);
            return "xu-ly-create";
        }

        switch(hinhThucXuLy){
            case "KhoaThe2Thang":
                hinhThucXuLy = "Khóa thẻ 2 tháng";
                break;
            case "KhoaThe3Thang":
                hinhThucXuLy = "Khóa thẻ 3 tháng";
                break;
            case "KhoaThe1ThangVaBoiThuong":
                hinhThucXuLy = "Khóa thẻ 1 tháng và bồi thường";
                break;
            case "BoiThuong":
                hinhThucXuLy = "Bồi thường";
                break;
            default:
                hinhThucXuLy = "Khóa thẻ 1 tháng";
        }

        if(tienPhat.isEmpty()){
            tienPhat = "0";
        }

        XuLyDTO xuLy = new XuLyDTO();
        xuLy.setMaXL(Integer.parseInt(maXuLy));
        xuLy.setThanhVien(ThanhVienServiceImpl.mapToThanhVien(thanhVienService.findMemberById(Integer.parseInt(maThanhVien))));
        xuLy.setHinhThucXL(hinhThucXuLy);
        xuLy.setSoTien(Integer.parseInt(tienPhat));
        xuLy.setTrangThaiXL(Integer.parseInt(trangThai));
        xuLy.setNgayXL(new Date());
        
        xuLyService.saveXuLy(xuLy);
        return "redirect:/xuly";
    }
    
    @GetMapping("/xuly/{maXL}/edit")
    public String editXuLy(@PathVariable("maXL") long maXL, Model model) {
        String username = SecurityUtil.getUserSession();
        if (username == null) {
            return "redirect:/login";
        }
        Long maTV = Long.parseLong(username);
        ThanhVienDTO user = thanhVienService.findMemberById(maTV);
        XuLyDTO xuLy = xuLyService.findXuLyByMaXL(maXL);
        
        model.addAttribute("user", user);
        model.addAttribute("xuLy", xuLy);
        
        return "xu-ly-edit";
    }

    @PostMapping("/xuly/{maXL}/edit")
    public String updateXuLy(@PathVariable("maXL") long maXL,
                               @Valid @ModelAttribute("xuLy") XuLyDTO xuLy,
                                BindingResult result, Model model) {

        String username = SecurityUtil.getUserSession();
        if (username == null) {
            return "redirect:/login";
        }
        int userId = Integer.parseInt(username);
        ThanhVienDTO user = thanhVienService.findMemberById(userId);
        model.addAttribute("user", user);

        if (result.hasErrors()) {
            return "xu-ly-edit";
        }

        xuLyService.updateXuLy(xuLy);
        return "redirect:/xuly";
    }

    @GetMapping("/xuly/{maXL}/delete")
    public String deleteXuLy(@PathVariable("maXL") long maXL) {
        String username = SecurityUtil.getUserSession();
        if (username == null) {
            return "redirect:/login";
        }
        
        xuLyService.deleteXuLy(maXL);
        return "redirect:/xuly";
    }

    @GetMapping("/xuly/search")
    public String searchThanhVien(@RequestParam(value = "query") String query, Model model) {
        String username = SecurityUtil.getUserSession();
        if (username == null) {
            return "redirect:/login";
        }
        Long maTV = Long.parseLong(username);
        ThanhVienDTO user = thanhVienService.findMemberById(maTV);
        List<XuLyDTO> xuLys = xuLyService.searchXuLy(query);
        
        model.addAttribute("user", user);
        model.addAttribute("xuLys", xuLys);
        return "xu-ly-list";
    }
}

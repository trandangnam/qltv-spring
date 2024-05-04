/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qltv.web.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import qltv.web.dto.ThanhVienDTO;
import qltv.web.dto.ThongTinSuDungDTO;
import qltv.web.security.SecurityUtil;
import qltv.web.services.ThanhVienService;
import qltv.web.services.ThietBiService;
import qltv.web.services.ThongTinSuDungService;

/**
 *
 * @author Acer
 */
@Controller
public class ThongTinSuDungController {
    
    ThietBiService thietBiService;
    ThanhVienService thanhVienService;
    ThongTinSuDungService thongTinSuDungService;
    
    @Autowired
    public ThongTinSuDungController(ThietBiService thietBiService, ThanhVienService thanhVienService, ThongTinSuDungService thongTinSuDungService) {
        this.thietBiService = thietBiService;
        this.thanhVienService = thanhVienService;
        this.thongTinSuDungService = thongTinSuDungService;
    }
    
    @GetMapping("/thongtinsudung")
    public String listThongTinSuDung(Model model) {
        String username = SecurityUtil.getUserSession();
        if (username == null) { // kiểm tra đăng nhập
            return "redirect:/login";
        }
        Long maTV = Long.parseLong(username);
        ThanhVienDTO user = thanhVienService.findMemberById(maTV);
        List<ThongTinSuDungDTO> ttsds = thongTinSuDungService.getAllThongTinSuDung();
        model.addAttribute("user", user);
        model.addAttribute("ttsds", ttsds);
        return "muon-tra-list";
    }
    
    @GetMapping("/thongtinsudung/search")
    @ResponseBody
    public List<ThongTinSuDungDTO> searchThongTinSuDung(@RequestParam(value = "query") String query, Model model) {
        String username = SecurityUtil.getUserSession();
        if (username == null) {
            return null;
        }
        Long maTV = Long.parseLong(username);
        ThanhVienDTO user = thanhVienService.findMemberById(maTV);
        List<ThongTinSuDungDTO> ttsds = thongTinSuDungService.searchThongTinSuDung(query);
        return ttsds;
    }
}

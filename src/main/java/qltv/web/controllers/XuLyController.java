/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qltv.web.controllers;

import jakarta.validation.Valid;
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
import qltv.web.security.SecurityUtil;
import qltv.web.services.ThanhVienService;
import qltv.web.services.XuLyService;

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
        
        model.addAttribute("user", user);
        model.addAttribute("xuLy", xuLy);
        return "xu-ly-create";
    }
    
    @PostMapping("/xuly/new")
    public String saveXuLy(@Valid @ModelAttribute("xuLy") XuLyDTO xuLy, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("xuLy", xuLy);
            return "xu-ly-create";
        }

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

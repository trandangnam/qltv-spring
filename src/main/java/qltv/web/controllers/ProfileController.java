/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qltv.web.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import qltv.web.dto.ThanhVienDTO;
import qltv.web.security.SecurityUtil;
import qltv.web.services.ThanhVienService;

/**
 *
 * @author DELL
 */
@Controller
public class ProfileController {

    private ThanhVienService tvService;

    @Autowired
    public ProfileController(ThanhVienService tvService) {
        this.tvService = tvService;
    }

//    @GetMapping("/profile")
//    public String Profile(Model model) {
//        String username = SecurityUtil.getUserSession();
//        if (username == null) {
//            return "redirect:/login";
//        }
//        int maTV = Integer.parseInt(username);
//        ThanhVienDTO user = tvService.findMemberById(maTV);
//        model.addAttribute("user", user);
//        return "profile";
//    }
    @GetMapping("/profile/{maTV}")
    public String editProfileForm(@PathVariable("maTV") long maTV, Model model) {
        String username = SecurityUtil.getUserSession();
        if (username == null) {
            return "redirect:/login";
        }
        int userId = Integer.parseInt(username);
        ThanhVienDTO user = tvService.findMemberById(userId);
        model.addAttribute("user", user);
        ThanhVienDTO thanhVien = tvService.findMemberById(maTV);
        model.addAttribute("thanhVien", thanhVien);
        return "profile";
    }

    @PostMapping("/profile/{maTV}")
    @ResponseBody
    public String updateProfile(@PathVariable("maTV") long maTV,
            @Valid @ModelAttribute("thanhVien") ThanhVienDTO thanhVien,
            BindingResult result, Model model, String hoTen, String khoa, String nganh, String soDienThoai, String email, String password) {

        String username = SecurityUtil.getUserSession();
        if (username == null) {
            return "redirect:/login";
        }
        int userId = Integer.parseInt(username);
        ThanhVienDTO user = tvService.findMemberById(userId);
        model.addAttribute("user", user);
        thanhVien.setHoTen(hoTen);
        thanhVien.setKhoa(khoa);
        thanhVien.setNganh(nganh);
        thanhVien.setSdt(soDienThoai);
        thanhVien.setEmail(email);
//        thanhVien = tvService.findMemberById(maTV);
//        thanhVien.setHoTen(hoTen);

//        if (result.hasErrors()) {
//            return "profile";
//        }
        tvService.updateProfile(thanhVien);
        return "Hello" + thanhVien; 
    }

}

package qltv.web.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import qltv.web.dto.ThanhVienDTO;
import qltv.web.mappers.ThanhVienMapper;
import qltv.web.models.ThanhVien;
import qltv.web.security.SecurityUtil;
import qltv.web.services.ThanhVienService;

@Controller
public class AuthController {

    private ThanhVienService tvService;

    public AuthController(ThanhVienService tvService) {
        this.tvService = tvService;
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        String username = SecurityUtil.getUserSession();
        if (username != null) {
            return "redirect:/thanhvien";
        }
        ThanhVienDTO thanhVien = new ThanhVienDTO();
        model.addAttribute("thanhVien", thanhVien);
        return "register";
    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("thanhVien") ThanhVienDTO thanhVien,
            BindingResult result, Model model) {
        String username = SecurityUtil.getUserSession();
        if (username != null) {
            return "redirect:/thanhvien";
        }

        ThanhVienDTO tvDTO = tvService.findMemberById(thanhVien.getMaTV());
        ThanhVien tv = ThanhVienMapper.mapToThanhVien(tvDTO);

        if (tv.getHoTen() != null) {
            return "redirect:/register?fail";
        }
        if (result.hasErrors()) {
            model.addAttribute("thanhVien", thanhVien);
            return "register";
        }
        tvService.saveThanhVien(thanhVien);
        return "redirect:/login?success";
    }

    @GetMapping("/login")
    public String loginPage() {
        String username = SecurityUtil.getUserSession();
        if (username != null) {
            return "redirect:/thanhvien";
        }
        return "login";
    }
}

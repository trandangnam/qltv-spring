package qltv.web.controllers;

import jakarta.validation.Valid;
import qltv.web.dto.ThanhVienDTO;
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
import qltv.web.security.SecurityUtil;
import qltv.web.services.ThanhVienService;

@Controller
public class ThanhVienController {

    private ThanhVienService tvService;

    @Autowired
    public ThanhVienController(ThanhVienService tvService) {
        this.tvService = tvService;
    }

    @GetMapping("/thanhvien")
    public String listThanhVien(Model model) {
        String username = SecurityUtil.getUserSession();
        if (username == null) {
            return "redirect:/login";
        }
        int maTV = Integer.parseInt(username);
        ThanhVienDTO user = tvService.findMemberById(maTV);
        model.addAttribute("user", user);
        List<ThanhVienDTO> listThanhVien = tvService.getAllThanhVien();
        model.addAttribute("listThanhVien", listThanhVien);
        return "thanh-vien-list";
    }

    @GetMapping("/thanhvien/new")
    public String createThanhVienForm(Model model) {
        String username = SecurityUtil.getUserSession();
        if (username == null) {
            return "redirect:/login";
        }
        int maTV = Integer.parseInt(username);
        ThanhVienDTO user = tvService.findMemberById(maTV);
        model.addAttribute("user", user);
        ThanhVienDTO thanhVien = new ThanhVienDTO();
        model.addAttribute("thanhVien", thanhVien);
        return "thanh-vien-create";
    }

    @PostMapping("/thanhvien/new")
    public String saveThanhVien(@Valid @ModelAttribute("thanhVien") ThanhVienDTO thanhVien, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("thanhVien", thanhVien);
            return "thanh-vien-create";
        }

        tvService.saveThanhVien(thanhVien);
        return "redirect:/thanhvien";
    }

    @GetMapping("/thanhvien/{maTV}/edit")
    public String editThanhVienForm(@PathVariable("maTV") long maTV, Model model) {
        String username = SecurityUtil.getUserSession();
        if (username == null) {
            return "redirect:/login";
        }
        int userId = Integer.parseInt(username);
        ThanhVienDTO user = tvService.findMemberById(userId);
        model.addAttribute("user", user);
        ThanhVienDTO thanhVien = tvService.findMemberById(maTV);
        model.addAttribute("thanhVien", thanhVien);
        return "thanh-vien-edit";
    }

    @PostMapping("/thanhvien/{maTV}/edit")
    public String updateThanhVien(@PathVariable("maTV") long maTV,
            @Valid @ModelAttribute("thanhVien") ThanhVienDTO thanhVien,
            BindingResult result, Model model) {

        String username = SecurityUtil.getUserSession();
        if (username == null) {
            return "redirect:/login";
        }
        int userId = Integer.parseInt(username);
        ThanhVienDTO user = tvService.findMemberById(userId);
        model.addAttribute("user", user);

        if (result.hasErrors()) {
            return "thanh-vien-edit";
        }

        tvService.updateThanhVien(thanhVien);
        return "redirect:/thanhvien";
    }

    @GetMapping("/thanhvien/{maTV}/delete")
    public String deleteThanhVien(@PathVariable("maTV") long maTV) {
        String username = SecurityUtil.getUserSession();
        if (username == null) {
            return "redirect:/login";
        }
        tvService.deleteThanhVien(maTV);
        return "redirect:/thanhvien";
    }

    @GetMapping("/thanhvien/search")
    public String searchThanhVien(@RequestParam(value = "query") String query, Model model) {
        String username = SecurityUtil.getUserSession();
        if (username == null) {
            return "redirect:/login";
        }
        int userId = Integer.parseInt(username);
        ThanhVienDTO user = tvService.findMemberById(userId);
        model.addAttribute("user", user);
        List<ThanhVienDTO> listThanhVien = tvService.searchThanhVien(query);
        model.addAttribute("listThanhVien", listThanhVien);
        return "thanh-vien-list";
    }

    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qltv.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import qltv.web.dto.ThanhVienDTO;
import qltv.web.security.SecurityUtil;
import qltv.web.services.ThanhVienService;

/**
 *
 * @author DELL
 */
public class ProfileController {
    private ThanhVienService tvService;

    @Autowired
    public ProfileController(ThanhVienService tvService) {
        this.tvService = tvService;
    }
    
    @GetMapping("/profile")
    public String Profile(Model model) {
        String username = SecurityUtil.getUserSession();
        if (username == null) {
            return "redirect:/login";
        }
        int maTV = Integer.parseInt(username);
        ThanhVienDTO user = tvService.findMemberById(maTV);
        model.addAttribute("user", user);
        return "profile";
    }
    
}

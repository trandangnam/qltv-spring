package qltv.web.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import qltv.web.dto.ThanhVienDTO;
import qltv.web.dto.sdi.ClientSdi;
import qltv.web.mappers.ThanhVienMapper;
import qltv.web.models.ThanhVien;
import qltv.web.repositories.ThanhVienRepository;
import static qltv.web.security.SecurityConfig.passwordEncoder;
import qltv.web.security.SecurityUtil;
import qltv.web.services.ClientService;
import qltv.web.services.ThanhVienService;
import qltv.web.services.impl.ClientServiceImpl;
import qltv.web.utils.DataUtils;

@Controller
public class AuthController {

    private ThanhVienService tvService;
    private String email;

    @Autowired
    private ClientService clientService;
    private ClientServiceImpl clientServiceImpl;

    public AuthController(ThanhVienService tvService) {
        this.tvService = tvService;
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        String username = SecurityUtil.getUserSession();
        if (username != null) {
            int maTV = Integer.parseInt(username);
            return maTV > 10 ? "redirect:/thanhvien" : "redirect:/";
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
            int maTV = Integer.parseInt(username);
            return maTV > 10 ? "redirect:/thanhvien" : "redirect:/";
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
            int maTV = Integer.parseInt(username);
            return maTV > 10 ? "redirect:/thanhvien" : "redirect:/";
        }
        return "login";
    }

    @GetMapping("/forgot-password")
    public String forgotPasswordPage() {
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    @ResponseBody
    public String sendEmailOtp(ClientSdi sdi, String email) {
        this.email = email;
        boolean checkEmailExist = tvService.existsByEmail(email);
        if (checkEmailExist) {
            ThanhVien thanhVien = tvService.findByEmail(email);
            sdi.setName(thanhVien.getHoTen());
            sdi.setUsername(String.valueOf(thanhVien.getMaTV()));
            sdi.setEmail(email);
            clientService.create(sdi);
            return "true";
        } else {
            return "false";
        }
    }

    @GetMapping("/forgot-password/confirm-otp")
    public String confirmOtpPage() {
        return "confirm-otp";
    }

    @PostMapping("/forgot-password/confirm-otp")
    @ResponseBody
    public boolean checkOtp(String otp) {
        String OtpSent = clientService.getGeneratedOTP();
        boolean check = otp.trim().equals(OtpSent);
        return check;
    }
    
    @GetMapping("/forgot-password/confirm-otp/change-pass")
    public String changePassPage() {
        return "change-pass";
    }
    
    @PostMapping("/forgot-password/confirm-otp/change-pass")
    @ResponseBody
    public String changePass(String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String newPasswordEncode = passwordEncoder.encode(newPassword);
        tvService.changePasswordByEmail(this.email, newPasswordEncode);
        return "true";
    }
}

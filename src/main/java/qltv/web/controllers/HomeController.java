package qltv.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import qltv.web.dto.ThanhVienDTO;
import qltv.web.dto.ThietBiResponse;
import qltv.web.security.SecurityUtil;
import qltv.web.services.ThanhVienService;
import qltv.web.services.ThietBiService;

@Controller
public class HomeController {

    private ThanhVienService tvService;
    private ThietBiService tbService;

    @Autowired
    public HomeController(ThanhVienService tvService, ThietBiService tbService) {
        this.tvService = tvService;
        this.tbService = tbService;
    }

    @GetMapping("/")
    public String getHomepage(Model model) {
        String username = SecurityUtil.getUserSession();
        ThanhVienDTO user = null;
        if (username != null) {
            int maTV = Integer.parseInt(username);
            user = tvService.findMemberById(maTV);
        }
        model.addAttribute("user", user);
        ThietBiResponse thietBiResponse = tbService.findThietBiDatChoTrongNgay(0, 10, "");
        model.addAttribute("thietBiResponse", thietBiResponse);
        return "index";
    }

    @GetMapping("/search")
    public String getThietBiList(
            @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "query", defaultValue = "", required = false) String query,
            Model model) {
        String username = SecurityUtil.getUserSession();
        ThanhVienDTO user = null;
        if (username != null) {
            int maTV = Integer.parseInt(username);
            user = tvService.findMemberById(maTV);
        }
        model.addAttribute("user", user);
        ThietBiResponse thietBiResponse = tbService.findThietBiDatChoTrongNgay(pageNo - 1, pageSize, query);
        model.addAttribute("thietBiResponse", thietBiResponse);
        model.addAttribute("query", query);
        return "index";
    }
}

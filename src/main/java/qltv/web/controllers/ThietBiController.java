package qltv.web.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import qltv.web.dto.ThanhVienDTO;
import qltv.web.dto.ThietBiDTO;
import qltv.web.dto.ThongTinSuDungDTO;
import qltv.web.security.SecurityUtil;
import qltv.web.services.ThanhVienService;
import qltv.web.services.ThietBiService;
import qltv.web.services.ThongTinSuDungService;

@Controller
public class ThietBiController {

    private ThietBiService tbService;
    private ThanhVienService thanhVienService;
    private ThongTinSuDungService ttsdService;

    @Autowired
    public ThietBiController(ThietBiService tbService, ThanhVienService thanhVienService, ThongTinSuDungService ttsdService) {
        this.tbService = tbService;
        this.thanhVienService = thanhVienService;
        this.ttsdService = ttsdService;
    }

    @GetMapping("/datchothietbi")
    public String listThietBi(Model model) {
        String username = SecurityUtil.getUserSession();
        if (username == null) { // kiểm tra đăng nhập
            return "redirect:/login";
        }
        Long maTV = Long.parseLong(username);
        ThanhVienDTO user = thanhVienService.findMemberById(maTV);
        List<ThietBiDTO> tblist = tbService.getAllThietBi();
        model.addAttribute("user", user);
        model.addAttribute("tblist", tblist);
        return "datchothietbi";
    }

    @GetMapping("/datchothietbi/search")
    public String searchThietBi(@RequestParam(value = "query") String query, Model model) {
        String username = SecurityUtil.getUserSession();
        if (username == null) {
            return "redirect:/login";
        }
        Long maTV = Long.parseLong(username);
        ThanhVienDTO user = thanhVienService.findMemberById(maTV);
        model.addAttribute("user", user);
        List<ThietBiDTO> tblist = tbService.searchThietBi(query);
        model.addAttribute("tblist", tblist);
        model.addAttribute("query", query);
        return "datchothietbi";

    }

    @GetMapping("/datchothietbi/{maTB}/datcho")
    public String DatchoThietBi(@PathVariable("maTB") long maTB, Model model) {
        String username = SecurityUtil.getUserSession();
        if (username == null) {
            return "redirect:/login";
        }
        Long maTV = Long.parseLong(username);
        ThanhVienDTO user = thanhVienService.findMemberById(maTV);
        model.addAttribute("user", user);
        ThietBiDTO thietbi = tbService.findByMaTB(maTB);
        model.addAttribute("thietbi", thietbi);
        List<ThongTinSuDungDTO> ttsds = ttsdService.getTtsdByMaTB(maTB);
        model.addAttribute("ttsds", ttsds);
        return "datcho";
    }

    @PostMapping("/datchothietbi/save")
    public String saveDatCho(@RequestParam("maTV") String maTV, @RequestParam("tenTV") String tenTV, @RequestParam("maTB") String maTB, @RequestParam("tenTB") String tenTB, @RequestParam("thoiGian") String thoiGian) {
        ThongTinSuDungDTO ttsdDTO = new ThongTinSuDungDTO();
        long maTVLong = Long.parseLong(maTV);
        long maTBLong = Long.parseLong(maTB);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date tgDatCho = null;
        try {
            tgDatCho = dateFormat.parse(thoiGian);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ThanhVienDTO tv = thanhVienService.findMemberById(maTVLong);
        ThietBiDTO tb = tbService.findByMaTB(maTBLong);
        int id = ttsdService.getMaxIdFlusOne();
        ttsdDTO.setMaTT(id);
        ttsdDTO.setThanhVien(tv);
        ttsdDTO.setThietBi(tb);
        ttsdDTO.setTgDatCho(tgDatCho);
        ttsdService.saveThongTinSuDung(ttsdDTO);
        return "redirect:/datchothietbi";
    }

}

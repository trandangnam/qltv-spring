package qltv.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import qltv.web.dto.ThietBiDTO;
import qltv.web.services.ThietBiService;

@Controller
public class ThietBiController {

    private ThietBiService tbService;

    public ThietBiController(ThietBiService tbService) {
        this.tbService = tbService;
    }
    
    // ---------------------------------hàm này của tiến nha đừng có xóa---------------------------------
    @GetMapping("/thietbi/getbyid")
    @ResponseBody
    public ThietBiDTO getThanhVienById(@RequestParam(value = "query") String query, Model model) {
        try {
            Long maTB = Long.parseLong(query);
            ThietBiDTO thietBi = tbService.findThietBiById(maTB);
            return thietBi;
        }catch(Exception e){
            return null;
        }
    }
    //--------------------------------------------------------------
}

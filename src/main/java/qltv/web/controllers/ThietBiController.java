package qltv.web.controllers;

import org.springframework.stereotype.Controller;
import qltv.web.services.ThietBiService;

@Controller
public class ThietBiController {

    private ThietBiService tbService;

    public ThietBiController(ThietBiService tbService) {
        this.tbService = tbService;
    }
}

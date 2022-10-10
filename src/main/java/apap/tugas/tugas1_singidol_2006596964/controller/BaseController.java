package apap.tugas.tugas1_singidol_2006596964.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {
    @GetMapping("/")
    private String Home(){
        return "home";
    }
}
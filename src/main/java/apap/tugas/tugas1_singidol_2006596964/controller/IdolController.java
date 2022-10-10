package apap.tugas.tugas1_singidol_2006596964.controller;

import apap.tugas.tugas1_singidol_2006596964.model.IdolModel;
import apap.tugas.tugas1_singidol_2006596964.service.IdolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class IdolController {
    @Qualifier("idolServiceImpl")
    @Autowired
    private IdolService idolService;


    @GetMapping("/idol")
    public String viewAllIdol(Model model){
        List<IdolModel> listIdol = idolService.getListIdol();
        model.addAttribute("listIdol", listIdol);
        return "viewall-idol";
    }

    @GetMapping("/idol/tambah")
    public String tambahIdolFormPage(Model model){
        IdolModel idol = new IdolModel();
        model.addAttribute("idol", idol);
        return "form-add-idol";
    }

    @PostMapping(value = "/idol/tambah")
    public String tambahIdolSubmit(@ModelAttribute IdolModel idol, Model model){
        idolService.addIdol(idol);
        model.addAttribute("namaIdol", idol.getNamaIdol());
        return "add-idol";
    }

    @GetMapping("/idol/{id}")
    public String viewDetailIdolPage(@PathVariable(value="id") String id, Model model){
        IdolModel idol = idolService.getIdolById(Long.parseLong(id));
        if (idol == null){
            return "error/404.html";
        }
        model.addAttribute("idol", idol);

        return "view-idol";
    }
}

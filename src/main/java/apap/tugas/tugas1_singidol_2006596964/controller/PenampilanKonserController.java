package apap.tugas.tugas1_singidol_2006596964.controller;

import apap.tugas.tugas1_singidol_2006596964.model.IdolModel;
import apap.tugas.tugas1_singidol_2006596964.model.KonserModel;
import apap.tugas.tugas1_singidol_2006596964.model.TipeModel;
import apap.tugas.tugas1_singidol_2006596964.service.PenampilanKonserService;
import apap.tugas.tugas1_singidol_2006596964.service.IdolService;
import apap.tugas.tugas1_singidol_2006596964.service.TipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PenampilanKonserController {
    @Qualifier("penampilanKonserServiceImpl")
    @Autowired
    private PenampilanKonserService penampilanKonserService;

    @Qualifier("idolServiceImpl")
    @Autowired
    private IdolService idolService;


    @GetMapping("/konser/cari")
    public String filterKonserPage(Model model){
        List<IdolModel> listIdolExisting = idolService.getListIdol();
        model.addAttribute("listIdolExisting", listIdolExisting);
        return "filter-konser";
    }

    @GetMapping("/carikonser")
    public String filterKonserSubmit(
            @RequestParam(value="totalPendapatan") String totalPendapatanstr,
            @RequestParam(value="idIdol") String idIdolstr,
            Model model){

        Long totalPendapatan = Long.parseLong(totalPendapatanstr);
        Long idIdol = Long.parseLong(idIdolstr);

        List<IdolModel> listIdolExisting = idolService.getListIdol();
        List<KonserModel> konserFiltered = penampilanKonserService.getFilterKonser(totalPendapatan, idIdol);

        model.addAttribute("listIdolExisting", listIdolExisting);
        model.addAttribute("listKonser", konserFiltered);
        return "filter-konser";
    }
}

package apap.tugas.tugas1_singidol_2006596964.controller;

import apap.tugas.tugas1_singidol_2006596964.model.IdolModel;
import apap.tugas.tugas1_singidol_2006596964.model.KonserModel;
import apap.tugas.tugas1_singidol_2006596964.model.PenampilanKonserModel;
import apap.tugas.tugas1_singidol_2006596964.model.TipeModel;
import apap.tugas.tugas1_singidol_2006596964.service.KonserService;
import apap.tugas.tugas1_singidol_2006596964.service.IdolService;
import apap.tugas.tugas1_singidol_2006596964.service.PenampilanKonserService;
import apap.tugas.tugas1_singidol_2006596964.service.TipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class KonserController {
    @Qualifier("konserServiceImpl")
    @Autowired
    private KonserService konserService;

    @Qualifier("idolServiceImpl")
    @Autowired
    private IdolService idolService;

    @Qualifier("penampilanKonserServiceImpl")
    @Autowired
    private PenampilanKonserService penampilanKonserService;

    @Qualifier("tipeServiceImpl")
    @Autowired
    private TipeService tipeService;


    @GetMapping("/konser")
    public String viewAllKonser(Model model){
        List<KonserModel> listKonser = konserService.getListKonser();
        model.addAttribute("listKonser", listKonser);
        return "viewall-konser";
    }

    @GetMapping("/konser/tambah")
    public String addKonserPage(Model model){
        KonserModel konser = new KonserModel();
        List<IdolModel> listIdolExisting = idolService.getListIdol();
        List<PenampilanKonserModel> listPenampilanKonserNew = new ArrayList<>();

        konser.setListPenampilanKonser(listPenampilanKonserNew);
        konser.getListPenampilanKonser().add(new PenampilanKonserModel());

        model.addAttribute("konser", konser);
        model.addAttribute("listIdolExisting", listIdolExisting);
        return "form-add-konser";
    }


    @PostMapping(value = "/konser/tambah", params={"addRowPenampilan"})
    public String addRowPenampilanMultiple(@ModelAttribute KonserModel konser, Model model){
    if (konser.getListPenampilanKonser() == null || konser.getListPenampilanKonser().size() == 0){
        konser.setListPenampilanKonser(new ArrayList<>());
    }

    konser.getListPenampilanKonser().add(new PenampilanKonserModel());
    List<IdolModel> listIdolExisting = idolService.getListIdol();

    model.addAttribute("konser", konser);
    model.addAttribute("listIdolExisting", listIdolExisting);
    return "form-add-konser";
}

    @PostMapping(value = "/konser/tambah", params={"deleteRowPenampilan"})
    public String deleteRowPenampilanMultiple(@ModelAttribute KonserModel konser, @RequestParam("deleteRowPenampilan") Integer row, Model model){
        final Integer rowId = Integer.valueOf(row);
        konser.getListPenampilanKonser().remove(rowId.intValue());

        List<IdolModel> listIdolExisting = idolService.getListIdol();

        model.addAttribute("konser", konser);
        model.addAttribute("listIdolExisting", listIdolExisting);
        return "form-add-konser";
    }

    @PostMapping(value = "/konser/tambah", params = {"save"})
    public String addCourseSubmit(@ModelAttribute KonserModel konser, Model model){
        if (konser.getListPenampilanKonser() == null){
            konser.setListPenampilanKonser(new ArrayList<>());
        }

        for (PenampilanKonserModel penampilan: konser.getListPenampilanKonser()){
            penampilan.setKonser(konser);
        }

        konserService.addKonser(konser);

        model.addAttribute("namaKonser", konser.getNamaKonser());
        return "add-konser";
    }

    @GetMapping("/konser/{id}")
    public String viewDetailKonserPage(@PathVariable(value="id") String id, Model model){
        KonserModel konser = konserService.getKonserById(Long.parseLong(id));
        if (konser == null){
            return "error/404.html";
        }

        List<PenampilanKonserModel> listPenampilanKonser = konser.getListPenampilanKonser();
        model.addAttribute("listPenampilanKonser", listPenampilanKonser);
        model.addAttribute("konser", konser);

        return "view-konser";
    }

    @GetMapping("/konser/ubah/{id}")
    public String updateKonserPage(@PathVariable(value="id") String id, Model model){
        KonserModel konser = konserService.getKonserById(Long.parseLong(id));
        if (konser == null){
            return "error/404.html";
        }

        List<IdolModel> listIdolExisting = idolService.getListIdol();
        model.addAttribute("listIdolExisting", listIdolExisting);
        model.addAttribute("konser", konser);

        return "form-update-konser";
    }


    @PostMapping(value = "/konser/ubah", params={"addRowPenampilan"})
    public String addRowPenampilanUbahMultiple(@ModelAttribute KonserModel konser, Model model){
        if (konser.getListPenampilanKonser() == null || konser.getListPenampilanKonser().size() == 0){
            konser.setListPenampilanKonser(new ArrayList<>());
        }

        konser.getListPenampilanKonser().add(new PenampilanKonserModel());
        List<IdolModel> listIdolExisting = idolService.getListIdol();

        model.addAttribute("konser", konser);
        model.addAttribute("listIdolExisting", listIdolExisting);
        return "form-update-konser";
    }

    @PostMapping(value = "/konser/ubah", params={"deleteRowPenampilan"})
    public String deleteRowPenampilanUbahMultiple(@ModelAttribute KonserModel konser, @RequestParam("deleteRowPenampilan") Integer row, Model model){
        final Integer rowId = Integer.valueOf(row);
        PenampilanKonserModel penampilanKonser = konser.getListPenampilanKonser().get(rowId.intValue());
        konser.getListPenampilanKonser().remove(rowId.intValue());
        penampilanKonserService.deletePenampilanKonser(penampilanKonser);

        List<IdolModel> listIdolExisting = idolService.getListIdol();

        model.addAttribute("konser", konser);
        model.addAttribute("listIdolExisting", listIdolExisting);
        return "form-update-konser";
    }

    @PostMapping(value = "/konser/ubah", params = {"save"})
    public String ubahKonserSubmit(@ModelAttribute KonserModel konser, Model model){
        if (konser.getListPenampilanKonser() == null){
            konser.setListPenampilanKonser(new ArrayList<>());
        }

        for (PenampilanKonserModel penampilan: konser.getListPenampilanKonser()){
            penampilan.setKonser(konser);
        }

        konserService.updateKonser(konser);

        model.addAttribute("namaKonser", konser.getNamaKonser());
        return "update-konser";
    }

    @GetMapping("/bonus")
    public String bonusKonserPage(Model model){
        List<TipeModel> listTipeExisting = tipeService.getListTipe();
        model.addAttribute("listTipeExisting", listTipeExisting);
        model.addAttribute("namaTipe", "");
        model.addAttribute("countTiket", 0);
        model.addAttribute("konser", null);
        return "bonus";
    }
    @GetMapping("/bonus/konser/top/")
    public String bonusKonserSubmit(
            @RequestParam(value="idTipe") String idTipe,
            Model model){

        TipeModel idTipeNew = tipeService.getTipeByID(Long.parseLong(idTipe));

        HashMap<KonserModel, Long> konserTop = konserService.getTopKonser(idTipeNew); //Cek konser
        KonserModel konser = konserTop.keySet().iterator().next();
        Long countTop = konserTop.get(konser);

        List<TipeModel> listTipeExisting = tipeService.getListTipe();
        model.addAttribute("listTipeExisting", listTipeExisting);
        model.addAttribute("namaTipe", idTipeNew.getNamaTipe());
        model.addAttribute("countTiket", countTop);
        model.addAttribute("konser", konser);
        return "bonus";
    }
}

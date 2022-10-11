package apap.tugas.tugas1_singidol_2006596964.controller;

import apap.tugas.tugas1_singidol_2006596964.model.TiketModel;
import apap.tugas.tugas1_singidol_2006596964.model.KonserModel;
import apap.tugas.tugas1_singidol_2006596964.model.TipeModel;
import apap.tugas.tugas1_singidol_2006596964.service.KonserService;
import apap.tugas.tugas1_singidol_2006596964.service.TiketService;
import apap.tugas.tugas1_singidol_2006596964.service.TipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class TiketController {
    @Qualifier("tiketServiceImpl")
    @Autowired
    private TiketService tiketService;

    @Qualifier("konserServiceImpl")
    @Autowired
    private KonserService konserService;

    @Qualifier("tipeServiceImpl")
    @Autowired
    private TipeService tipeService;

    @GetMapping("/tiket")
    public String viewAllTiket(Model model){
        List<TiketModel> listTiket = tiketService.getListTiket();
        model.addAttribute("listTiket", listTiket);
        return "viewall-tiket";
    }

    @GetMapping("/tiket/{id}")
    public String viewTiketDetail(@PathVariable(value="id") String id, Model model){
        TiketModel tiket = tiketService.getTiketByID(Long.parseLong(id));
        if (tiket == null){
            return "error/404.html";
        }
        model.addAttribute("tiket", tiket);
        return "view-tiket";
    }

    @PostMapping("/tiket/hapus/{id}")
    public String deleteTiket(@PathVariable(value="id") String id, Model model){
        TiketModel tiket = tiketService.getTiketByID(Long.parseLong(id));
        if (tiket == null){
            return "error/404.html";
        }
        tiketService.hapusTiket(tiket);
        model.addAttribute("tiket", tiket);
        return "delete-tiket";
    }

    @GetMapping("/tiket/pesan")
    public String pesanTiketFormPage(Model model){
        TiketModel tiket = new TiketModel();
        List<TipeModel> listTipeExisting = tipeService.getListTipe();
        List<KonserModel> ListKonserExisting = konserService.getListKonser();
        model.addAttribute("tiket", tiket);
        model.addAttribute("listTipeExisting", listTipeExisting);
        model.addAttribute("listKonserExisting", ListKonserExisting);
        return "form-add-tiket";
    }

    @PostMapping("/tiket/pesan")
    public String pesanTiketFormSubmit(@ModelAttribute TiketModel tiket, Model model){
        try{
            tiket.setTanggalPembelian(LocalDate.now());
            TiketModel tiketNew = tiketService.addTiket(tiket);
            model.addAttribute("noTiket", tiketNew.getNoTiket());
            return "add-tiket";
        } catch (UnsupportedOperationException e){
            return "error/error-tiket.html";
        }
    }
}

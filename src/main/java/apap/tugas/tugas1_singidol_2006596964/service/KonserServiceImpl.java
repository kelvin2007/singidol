package apap.tugas.tugas1_singidol_2006596964.service;

import apap.tugas.tugas1_singidol_2006596964.model.KonserModel;
import apap.tugas.tugas1_singidol_2006596964.model.PenampilanKonserModel;
import apap.tugas.tugas1_singidol_2006596964.model.TiketModel;
import apap.tugas.tugas1_singidol_2006596964.model.TipeModel;
import apap.tugas.tugas1_singidol_2006596964.repository.KonserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class KonserServiceImpl implements KonserService{
    @Autowired
    KonserDb konserDb;

    @Override
    public void addKonser(KonserModel konser) {
        konserDb.save(konser);
    }

    @Override
    public List<KonserModel> getListKonser() {
        return konserDb.findAll();
    }

    @Override
    public KonserModel getKonserById(Long idKonser) {
        Optional<KonserModel> konser = konserDb.findById(idKonser);
        if (konser.isPresent()){
            return konser.get();
        } else return null;
    }

    @Override
    public KonserModel updateKonser(KonserModel konser) {
        konserDb.save(konser);
        return konser;
    }

    @Override
    public HashMap<KonserModel, Long> getTopKonser(TipeModel tipe) {
        HashMap<KonserModel, Long> topKonser = new HashMap<>();
        Long countTiket = Long.valueOf(0);
        KonserModel konserTop = null;
        List<KonserModel> listKonser = konserDb.findAll();
        for (KonserModel konser: listKonser){
            Long countTemp = Long.valueOf(0);
            List<TiketModel> listTiket = konser.getListTiket();
            for (TiketModel tiket: listTiket) {
                if (tiket.getTipe().getNamaTipe().equalsIgnoreCase(tipe.getNamaTipe())){
                    countTemp += 1;
                }
            }
            if (konserTop == null && countTemp != 0){
                countTiket = countTemp;
                konserTop = konser;
            } else if (konserTop != null && countTemp != 0) {
                if (countTemp > countTiket){
                    countTiket = countTemp;
                    konserTop = konser;
                } else if (countTemp.equals(countTiket)){
                    if(konserTop.getNamaKonser().compareTo(konser.getNamaKonser()) > 0){
                        countTiket = countTemp;
                        konserTop = konser;
                    }
                }
            }
        }
        topKonser.put(konserTop, countTiket);
        return topKonser;
    }
}

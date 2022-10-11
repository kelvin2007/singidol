package apap.tugas.tugas1_singidol_2006596964.service;

import apap.tugas.tugas1_singidol_2006596964.model.KonserModel;
import apap.tugas.tugas1_singidol_2006596964.model.PenampilanKonserModel;
import apap.tugas.tugas1_singidol_2006596964.model.TipeModel;

import java.util.List;

public interface PenampilanKonserService {
    List<KonserModel> getFilterKonser(Long totalPendapatan, Long idIdol);
    void deletePenampilanKonser(PenampilanKonserModel penampilanKonser);
}

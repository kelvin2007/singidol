package apap.tugas.tugas1_singidol_2006596964.service;

import apap.tugas.tugas1_singidol_2006596964.model.KonserModel;

import java.util.List;

public interface KonserService {
    void addKonser(KonserModel konser);
    List<KonserModel> getListKonser();

    KonserModel getKonserById(Long id);

    KonserModel updateKonser(KonserModel konser);
}

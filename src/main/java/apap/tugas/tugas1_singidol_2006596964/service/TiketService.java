package apap.tugas.tugas1_singidol_2006596964.service;

import apap.tugas.tugas1_singidol_2006596964.model.TiketModel;

import java.util.List;

public interface TiketService {
    TiketModel addTiket(TiketModel tiket);
    List<TiketModel> getListTiket();

    TiketModel getTiketByID(Long id);
    TiketModel getTiketByNoTiket(String noTiket);

    void hapusTiket(TiketModel tiket);
}

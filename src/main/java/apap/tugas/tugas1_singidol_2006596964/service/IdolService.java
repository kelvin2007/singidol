package apap.tugas.tugas1_singidol_2006596964.service;

import apap.tugas.tugas1_singidol_2006596964.model.IdolModel;

import java.util.List;

public interface IdolService {
    void addIdol(IdolModel idol);
    List<IdolModel> getListIdol();

    IdolModel getIdolById(Long id);
}


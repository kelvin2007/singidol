package apap.tugas.tugas1_singidol_2006596964.service;

import apap.tugas.tugas1_singidol_2006596964.model.TipeModel;
import java.util.List;

public interface TipeService {
    TipeModel getTipeByID(Long id);
    List<TipeModel> getListTipe();
}

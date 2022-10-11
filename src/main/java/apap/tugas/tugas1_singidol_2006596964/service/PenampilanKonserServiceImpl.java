package apap.tugas.tugas1_singidol_2006596964.service;

import apap.tugas.tugas1_singidol_2006596964.model.KonserModel;
import apap.tugas.tugas1_singidol_2006596964.model.PenampilanKonserModel;
import apap.tugas.tugas1_singidol_2006596964.model.TipeModel;
import apap.tugas.tugas1_singidol_2006596964.repository.PenampilanKonserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional

public class PenampilanKonserServiceImpl implements PenampilanKonserService{
    @Autowired
    PenampilanKonserDb penampilanKonserDb;

    @Override
    public List<KonserModel> getFilterKonser(Long totalPendapatan, Long idIdol) {
        return penampilanKonserDb.findAllFilter(totalPendapatan, idIdol);
    }

    @Override
    public void deletePenampilanKonser(PenampilanKonserModel penampilanKonser) {
        penampilanKonserDb.delete(penampilanKonser);
    }
}

package apap.tugas.tugas1_singidol_2006596964.service;

import apap.tugas.tugas1_singidol_2006596964.model.IdolModel;
import apap.tugas.tugas1_singidol_2006596964.repository.IdolDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class IdolServiceImpl implements IdolService{
    @Autowired
    IdolDb idolDb;

    @Override
    public void addIdol(IdolModel idol) {
        idolDb.save(idol);
    }

    @Override
    public List<IdolModel> getListIdol() {
        return idolDb.findAll();
    }

    @Override
    public IdolModel getIdolById(Long idIdol) {
        Optional<IdolModel> idol = idolDb.findById(idIdol);
        if (idol.isPresent()){
            return idol.get();
        } else return null;
    }
}

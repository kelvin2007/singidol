package apap.tugas.tugas1_singidol_2006596964.service;

import apap.tugas.tugas1_singidol_2006596964.model.TipeModel;
import apap.tugas.tugas1_singidol_2006596964.repository.TipeDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TipeServiceImpl implements TipeService {
    @Autowired
    TipeDb tipeDb;

    @Override
    public TipeModel getTipeByID(Long id) {
        Optional<TipeModel> tipe = tipeDb.findById(id);
        if (tipe.isPresent()){
            return tipe.get();
        } else return null;
    }

    @Override
    public List<TipeModel> getListTipe() {
        return tipeDb.findAll();
    }
}

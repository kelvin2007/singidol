package apap.tugas.tugas1_singidol_2006596964.repository;

import apap.tugas.tugas1_singidol_2006596964.model.TipeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface TipeDb extends JpaRepository<TipeModel, Long> {
    Optional<TipeModel> findById(Long idTipe);
    List<TipeModel> findAll();
}

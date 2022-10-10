package apap.tugas.tugas1_singidol_2006596964.repository;

import apap.tugas.tugas1_singidol_2006596964.model.KonserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface KonserDb extends JpaRepository<KonserModel, Long>{
    // JPA
    Optional<KonserModel> findById(Long idKonser);

    List<KonserModel> findAll();
}

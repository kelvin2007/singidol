package apap.tugas.tugas1_singidol_2006596964.repository;

import apap.tugas.tugas1_singidol_2006596964.model.TiketModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;
import java.util.List;

@Repository
public interface TiketDb extends JpaRepository<TiketModel, Long> {
    // JPA
    Optional<TiketModel> findById(Long idTiket);

    @Query("SELECT t FROM TiketModel t WHERE t.noTiket = :noTiket")
    Optional<TiketModel> findByNomorTiket(@Param("noTiket") String noTiket);

    List<TiketModel> findAll();
}

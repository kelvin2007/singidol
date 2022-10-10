package apap.tugas.tugas1_singidol_2006596964.repository;

import apap.tugas.tugas1_singidol_2006596964.model.KonserModel;
import apap.tugas.tugas1_singidol_2006596964.model.PenampilanKonserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PenampilanKonserDb extends JpaRepository<PenampilanKonserModel, Long>{
    // JPA
    @Query("SELECT DISTINCT k.konser FROM PenampilanKonserModel k WHERE k.konser.totalPendapatan >= :totalPendapatan AND k.idol.idIdol = :idIdol")
    List<KonserModel> findAllFilter(@Param("totalPendapatan") Long totalPendapatan, @Param("idIdol") Long idIdol);
}

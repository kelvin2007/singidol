package apap.tugas.tugas1_singidol_2006596964.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "idol")
public class IdolModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_idol")
    private Long idIdol;

    @NotNull
    @Size(max=255)
    @Column(name = "nama_idol", nullable = false)
    private String namaIdol;

    @NotNull
    @Column(name = "jumlah_anggota", nullable = false)
    private Integer jumlahAnggota;

    @NotNull
    @Column(name = "tanggal_debut", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggalDebut;

    @NotNull
    @Size(max=255)
    @Column(name = "asal_negara", nullable = false)
    private String asalNegara;

    @OneToMany(mappedBy = "idol", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PenampilanKonserModel> listPenampilanKonser;
}

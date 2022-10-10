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
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "konser")
public class KonserModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_konser")
    private Long idKonser;

    @NotNull
    @Size(max=255)
    @Column(name = "nama_konser", nullable = false)
    private String namaKonser;

    @NotNull
    @Column(name = "waktu", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime waktuKonser;

    @NotNull
    @Column(name = "total_pendapatan", nullable = false)
    private Long totalPendapatan = Long.valueOf(0);

    @NotNull
    @Size(max=255)
    @Column(name = "tempat", nullable = false)
    private String tempatKonser;

    @OneToMany(mappedBy = "konser", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TiketModel> listTiket;

    @OneToMany(mappedBy = "konser", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PenampilanKonserModel> listPenampilanKonser;
}


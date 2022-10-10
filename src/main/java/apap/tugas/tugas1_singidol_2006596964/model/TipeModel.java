package apap.tugas.tugas1_singidol_2006596964.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tipe")
public class TipeModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipe")
    private Long idTipe;

    @NotNull
    @Column(name = "harga", nullable = false)
    private Long hargaTipe;

    @NotNull
    @Size(max=255)
    @Column(name = "nama", nullable = false)
    private String namaTipe;

    @NotNull
    @Size(max=255)
    @Column(name = "deskripsi_tipe", nullable = false)
    private String deskripsiTipe;

    @OneToMany(mappedBy = "tipe", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TiketModel> listTiket;
}


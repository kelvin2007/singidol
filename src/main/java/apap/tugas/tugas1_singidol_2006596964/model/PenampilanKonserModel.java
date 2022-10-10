package apap.tugas.tugas1_singidol_2006596964.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "penampilan_konser")
public class PenampilanKonserModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_penampilan_konser")
    private Long idPenampilanKonser;

    @NotNull
    @Column(name = "jam_mulai_tampil", nullable = false)
    @DateTimeFormat(pattern="HH:mm")
    private LocalTime jamMulaiTampil;

    // Relasi dengan TipeModel
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_idol", referencedColumnName = "id_idol", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private IdolModel idol;

    // Relasi dengan KonserModel
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_konser", referencedColumnName = "id_konser", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private KonserModel konser;
}
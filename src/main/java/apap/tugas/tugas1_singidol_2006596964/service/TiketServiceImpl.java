package apap.tugas.tugas1_singidol_2006596964.service;

import apap.tugas.tugas1_singidol_2006596964.model.KonserModel;
import apap.tugas.tugas1_singidol_2006596964.model.TiketModel;
import apap.tugas.tugas1_singidol_2006596964.model.TipeModel;
import apap.tugas.tugas1_singidol_2006596964.repository.TiketDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Random;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TiketServiceImpl implements TiketService {
    @Autowired
    TiketDb tiketDb;


    @Override
    public TiketModel addTiket(TiketModel tiket) {
        String noTiket = extractNomor(tiket);
        TiketModel tiketEx = getTiketByNoTiket(noTiket);
        KonserModel konser = tiket.getKonser();
        TipeModel tipe = tiket.getTipe();

        if (tiketEx != null) {
            throw new UnsupportedOperationException();
        } else {
            tiket.setNoTiket(noTiket);
            konser.setTotalPendapatan(konser.getTotalPendapatan() + tipe.getHargaTipe());
            return tiketDb.save(tiket);
        }
    }

    @Override
    public List<TiketModel> getListTiket() {
        return tiketDb.findAll();
    }

    @Override
    public TiketModel getTiketByID(Long id) {
        Optional<TiketModel> tiket = tiketDb.findById(id);
        if (tiket.isPresent()){
            return tiket.get();
        } else return null;
    }

    @Override
    public TiketModel getTiketByNoTiket(String noTiket) {
        Optional<TiketModel> tiket = tiketDb.findByNomorTiket(noTiket);
        if (tiket.isPresent()){
            return tiket.get();
        } else return null;
    }

    @Override
    public void hapusTiket(TiketModel tiket) {
        KonserModel konser = tiket.getKonser();
        TipeModel tipe = tiket.getTipe();
        konser.setTotalPendapatan(konser.getTotalPendapatan() - tipe.getHargaTipe());
        tiketDb.delete(tiket);
    }

    public String extractNomor(TiketModel tiket){
        try {
            String tigaNamaDepan = tiket.getNamaLengkap().trim().substring(0, 3);
            int abjadKonser = ((int) (
                    tiket.getKonser().getNamaKonser().trim()
                            .charAt(0))) - 64;
            String abjadKonserStr = String.format("%02d", abjadKonser);

            Random rd = new Random();
            String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String abjadRandom = Character.toString(abc.charAt(rd.nextInt(abc.length())));

            String domLahir = String.format("%02d", tiket.getTanggalLahir().getDayOfMonth());
            String monLahir = String.format("%02d", tiket.getTanggalLahir().getMonthValue());
            String tanggalLahirStr = domLahir + monLahir;
            Long tanggalLahir = Long.parseLong(tanggalLahirStr);

            String domBeli = String.format("%02d", tiket.getTanggalPembelian().getDayOfMonth());
            String monBeli = String.format("%02d", tiket.getTanggalPembelian().getMonthValue());
            String tanggalBeliStr = domBeli + monBeli;
            Long tanggalBeli = Long.parseLong(tanggalBeliStr);

            String hasilTgl = String.format("%04d", tanggalLahir + tanggalBeli);

            String tigaTipeTiket = tiket.getTipe().getNamaTipe();
            if (tigaTipeTiket.equalsIgnoreCase("vip")) {
                tigaTipeTiket = "VIP";
            } else if (tigaTipeTiket.equalsIgnoreCase("platinum")) {
                tigaTipeTiket = "PLT";
            } else if (tigaTipeTiket.equalsIgnoreCase("gold")) {
                tigaTipeTiket = "GLD";
            } else if (tigaTipeTiket.equalsIgnoreCase("silver")) {
                tigaTipeTiket = "SLV";
            }
            String hasil = tigaNamaDepan + hasilTgl + abjadKonserStr + tigaTipeTiket + abjadRandom;
            return hasil.toUpperCase();
        } catch (Exception e){
            throw new UnsupportedOperationException();
        }
    }
}

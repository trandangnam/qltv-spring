package qltv.web.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import lombok.Data;

@Data
@Entity
@Table(name = "thongtinsd")
public class ThongTinSuDung {

    @Id
    private int maTT;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date tgVao;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date tgMuon;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date tgTra;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date tgDatCho;

    @ManyToOne
    @JoinColumn(name = "MaTV")
    private ThanhVien thanhVien;

    @ManyToOne
    @JoinColumn(name = "MaTB", nullable = true)
    private ThietBi thietBi;

    public ThongTinSuDung() {
    }

    public ThongTinSuDung(int maTT, Date tgVao, Date tgMuon, Date tgTra, Date tgDatCho, ThanhVien thanhVien, ThietBi thietBi) {
        this.maTT = maTT;
        this.tgVao = tgVao;
        this.tgMuon = tgMuon;
        this.tgTra = tgTra;
        this.tgDatCho = tgDatCho;
        this.thanhVien = thanhVien;
        this.thietBi = thietBi;
    }
}

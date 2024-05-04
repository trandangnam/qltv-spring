package qltv.web.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ThongTinSuDungDTO {

    private int maTT;
    @Temporal(TemporalType.TIMESTAMP)
    private Date tgVao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date tgMuon;
    @Temporal(TemporalType.TIMESTAMP)
    private Date tgTra;
    @Temporal(TemporalType.TIMESTAMP)
    private Date tgDatCho;

    @ManyToOne
    @JoinColumn(name = "MaTV")
    private ThanhVienDTO thanhVien;

    @ManyToOne
    @JoinColumn(name = "MaTB", nullable = true)
    private ThietBiDTO thietBi;

    public ThongTinSuDungDTO() {
    }
    
    public ThongTinSuDungDTO(int maTT, Date tgVao, Date tgMuon, Date tgTra, Date tgDatCho, ThanhVienDTO thanhVien, ThietBiDTO thietBi) {
        this.maTT = maTT;
        this.tgVao = tgVao;
        this.tgMuon = tgMuon;
        this.tgTra = tgTra;
        this.tgDatCho = tgDatCho;
        this.thanhVien = thanhVien;
        this.thietBi = thietBi;
    }
    
    
}

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
@Table(name = "xuly")
public class XuLy {

    @Id
    private int maXL;
    @Column
    private String hinhThucXL;
    @Column(nullable = true)
    private int soTien;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayXL;
    @Column
    private int trangThaiXL;

    @ManyToOne
    @JoinColumn(name = "MaTV")
    private ThanhVien thanhVien;

    public XuLy() {
    }

    public XuLy(int maXL, String hinhThucXL, int soTien, Date ngayXL, int trangThaiXL, ThanhVien thanhVien) {
        this.maXL = maXL;
        this.hinhThucXL = hinhThucXL;
        this.soTien = soTien;
        this.ngayXL = ngayXL;
        this.trangThaiXL = trangThaiXL;
        this.thanhVien = thanhVien;
    }
}

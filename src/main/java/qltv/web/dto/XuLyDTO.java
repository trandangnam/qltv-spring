package qltv.web.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class XuLyDTO {

    private int maXL;
    @NotEmpty(message = "Hình thức xử lý không được để trống")
    private String hinhThucXL;
    private int soTien;
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayXL;
    @NotEmpty(message = "Trạng thái xử lý không được để trống")
    private int trangThaiXL;

    @ManyToOne
    @JoinColumn(name = "MaTV")
    private ThanhVienDTO thanhVien;

    public XuLyDTO() {
    }

    public XuLyDTO(int maXL, String hinhThucXL, int soTien, Date ngayXL, int trangThaiXL, ThanhVienDTO thanhVien) {
        this.maXL = maXL;
        this.hinhThucXL = hinhThucXL;
        this.soTien = soTien;
        this.ngayXL = ngayXL;
        this.trangThaiXL = trangThaiXL;
        this.thanhVien = thanhVien;
    }
}

package qltv.web.dto;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ThietBiDTO {

    private int maTB;
    @NotEmpty(message = "Tên thiết bị không được để trống")
    private String tenTB;
    @NotEmpty(message = "Mô tả không được để trống")
    private String moTaTB;
    private List<ThongTinSuDungDTO> thongTinSuDung;

    public ThietBiDTO() {
    }

    public ThietBiDTO(int maTB, String tenTB, String moTaTB, List<ThongTinSuDungDTO> thongTinSuDung) {
        this.maTB = maTB;
        this.tenTB = tenTB;
        this.moTaTB = moTaTB;
        this.thongTinSuDung = thongTinSuDung;
    }

}

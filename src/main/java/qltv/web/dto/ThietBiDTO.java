package qltv.web.dto;

import java.util.List;

import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ThietBiDTO {

    private int maTB;
    private String tenTB;
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

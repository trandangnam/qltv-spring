package qltv.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ThietBiDTO {

    private int maTB;
    private String tenTB;
    private String moTaTB;

    public ThietBiDTO() {
    }

    public ThietBiDTO(int maTB, String tenTB, String moTaTB) {
        this.maTB = maTB;
        this.tenTB = tenTB;
        this.moTaTB = moTaTB;
    }
}

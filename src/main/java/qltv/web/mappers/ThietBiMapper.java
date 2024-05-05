package qltv.web.mappers;

import java.util.stream.Collectors;
import qltv.web.dto.ThietBiDTO;
import qltv.web.models.ThietBi;

public class ThietBiMapper {

    public static ThietBi mapToThietBi(ThietBiDTO tb) {
        if (tb == null) {
            return new ThietBi();
        }
        ThietBi model = ThietBi.builder()
                .maTB(tb.getMaTB())
                .tenTB(tb.getTenTB())
                .moTaTB(tb.getMoTaTB())
                .build();
        return model;
    }

    public static ThietBiDTO mapToThietBiDto(ThietBi tb) {
        if (tb == null) {
            return new ThietBiDTO();
        }
        ThietBiDTO dto = ThietBiDTO.builder()
                .maTB(tb.getMaTB())
                .tenTB(tb.getTenTB())
                .moTaTB(tb.getMoTaTB())
                .build();
        return dto;
    }
}

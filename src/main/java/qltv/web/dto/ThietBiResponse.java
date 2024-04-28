package qltv.web.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ThietBiResponse {

    private List<ThietBiDTO> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;

    public ThietBiResponse() {
    }

    public ThietBiResponse(List<ThietBiDTO> content, int pageNo, int pageSize, long totalElements, int totalPages) {
        this.content = content;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }
}

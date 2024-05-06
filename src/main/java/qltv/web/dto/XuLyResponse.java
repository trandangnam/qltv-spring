package qltv.web.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class XuLyResponse {

    private List<XuLyDTO> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;

    public XuLyResponse() {
    }

    public XuLyResponse(List<XuLyDTO> content, int pageNo, int pageSize, long totalElements, int totalPages) {
        this.content = content;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }
}

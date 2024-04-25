package qltv.web.dto;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ThanhVienDTO {

    private int maTV;
    @NotEmpty(message = "Họ tên không được để trống")
    private String hoTen;
    @NotEmpty(message = "Khoa không được để trống")
    private String khoa;
    @NotEmpty(message = "Ngành không được để trống")
    private String nganh;
    @NotEmpty(message = "Số điện thoại không được để trống")
    private String sdt;
    @NotEmpty(message = "Mật khẩu không được để trống")
    private String password;
    @NotEmpty(message = "Email không được để trống")
    private String email;
    private List<ThongTinSuDungDTO> thongTinSuDung;
    private List<XuLyDTO> xuLy;

    public ThanhVienDTO() {
    }

    public ThanhVienDTO(int maTV, String hoTen, String khoa, String nganh, String sdt, String password, String email, List<ThongTinSuDungDTO> thongTinSuDung, List<XuLyDTO> xuLy) {
        this.maTV = maTV;
        this.hoTen = hoTen;
        this.khoa = khoa;
        this.nganh = nganh;
        this.sdt = sdt;
        this.password = password;
        this.email = email;
        this.thongTinSuDung = thongTinSuDung;
        this.xuLy = xuLy;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qltv.web.dto;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author DELL
 */
@Data
@Builder
public class ThanhVienPasswordDTO {

    private int maTV;
    @NotEmpty(message = "Mật khẩu cũ không được để trống")
    private String currentPassword;
    @NotEmpty(message = "Mật khẩu mới không được để trống")
    private String newPassword;
    @NotEmpty(message = "Xác nhận mật khẩu không được để trống")
    private String confirmPassword;
    private List<ThongTinSuDungDTO> thongTinSuDung;
    private List<XuLyDTO> xuLy;

    public ThanhVienPasswordDTO() {
    }

    public ThanhVienPasswordDTO(int maTV, String currentPassword, String newPassword, String confirmPassword, List<ThongTinSuDungDTO> thongTinSuDung, List<XuLyDTO> xuLy) {
        this.maTV = maTV;
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
        this.thongTinSuDung = thongTinSuDung;
        this.xuLy = xuLy;
    }
}

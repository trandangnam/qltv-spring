/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qltv.web.dto;

import lombok.Builder;
import lombok.Data;

/**
 *
 * @author DELL
 */
@Data
@Builder
public class ThanhVienChangePassDTO {
    private String email;
    private String newPassword;
    private String confirmPassword;

    public ThanhVienChangePassDTO() {
    }

    public ThanhVienChangePassDTO(String email, String newPassword, String confirmPassword) {
        this.email = email;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }
}

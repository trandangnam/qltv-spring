package qltv.web.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "thanhvien")
public class ThanhVien {

    @Id
    private int maTV;
    @Column(name = "HoTen")
    private String hoTen;
    @Column
    private String khoa;
    @Column
    private String nganh;
    @Column
    private String sdt;
    @Column
    private String password;
    @Column
    private String email;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "thanhVien", cascade = CascadeType.ALL)
    private Set<ThongTinSuDung> thongTinSuDung = new HashSet<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "thanhVien", cascade = CascadeType.ALL)
    private Set<XuLy> xuLy = new HashSet<>();

    public ThanhVien() {
    }

    public ThanhVien(int maTV, String hoTen, String khoa, String nganh, String sdt, String password, String email, Set<ThongTinSuDung> thongTinSuDung, Set<XuLy> xuLy) {
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

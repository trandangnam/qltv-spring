package qltv.web.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "thietbi")
public class ThietBi {

    @Id
    private int maTB;
    @Column
    private String tenTB;
    @Column
    private String moTaTB;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "thietBi", cascade = CascadeType.ALL)
    private List<ThongTinSuDung> thongTinSuDung = new ArrayList<>();

    public ThietBi() {
    }

    public ThietBi(int maTB, String tenTB, String moTaTB, List<ThongTinSuDung> thongTinSuDung) {
        this.maTB = maTB;
        this.tenTB = tenTB;
        this.moTaTB = moTaTB;
        this.thongTinSuDung = thongTinSuDung;
    }
}

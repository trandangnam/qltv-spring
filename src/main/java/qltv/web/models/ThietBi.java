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
import lombok.Data;

@Data
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
    private Set<ThongTinSuDung> thongTinSuDung = new HashSet<>();

    public ThietBi() {
    }

    public ThietBi(int maTB, String tenTB, String moTaTB) {
        this.maTB = maTB;
        this.tenTB = tenTB;
        this.moTaTB = moTaTB;
    }
}

package qltv.web.security;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import qltv.web.models.ThanhVien;
import qltv.web.repositories.ThanhVienRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private ThanhVienRepository tvRepository;

    @Autowired
    public CustomUserDetailsService(ThanhVienRepository tvRepository) {
        this.tvRepository = tvRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ThanhVien user = tvRepository.findFirstByMaTV(username);
        
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        
        if (user != null) {
            User authUser = new User(
                    String.valueOf(user.getMaTV()),
                    user.getPassword(),
                    authorities
            );
            return authUser;
        } else {
            throw new UsernameNotFoundException("Mã thành viên hoặc mật khẩu không hợp lệ");
        }
        
    }

}

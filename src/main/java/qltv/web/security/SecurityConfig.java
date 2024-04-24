package qltv.web.security;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Autowired
//    private DataSource dataSource;
    
    private CustomUserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorizeHttpRequests)
                        -> authorizeHttpRequests
                        .requestMatchers("/login/**").permitAll()
                        .requestMatchers("/register/**").permitAll()
                        .requestMatchers("/assets/**").permitAll()
                        .requestMatchers("/thanhvien/**").permitAll()
                )
                .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/thanhvien")
                .loginProcessingUrl("/login")
                .failureUrl("/login?error=true")
                .permitAll()
                ).logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
                );
        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        JdbcDaoImpl jdbcDaoImpl = new JdbcDaoImpl();
//        jdbcDaoImpl.setDataSource(dataSource);
//        jdbcDaoImpl.setUsersByUsernameQuery("SELECT maTV, password, 1 as enabled FROM thanhvien WHERE maTV =?");
//        jdbcDaoImpl.setAuthoritiesByUsernameQuery("SELECT maTV, 'ROLE_USER' as authority FROM thanhvien WHERE maTV =?");
//        return jdbcDaoImpl;
//    }
//
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(userDetailsService());
//        provider.setPasswordEncoder(passwordEncoder());
//        return provider;
//    }
}

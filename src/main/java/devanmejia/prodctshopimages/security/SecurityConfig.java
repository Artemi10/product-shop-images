package devanmejia.prodctshopimages.security;

import devanmejia.prodctshopimages.security.jwt.JWTProvider;
import devanmejia.prodctshopimages.security.jwt.JWTSecurityConfig;
import devanmejia.prodctshopimages.security.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ComponentScan("devanmejia.prodctshopimages")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JWTProvider jwtProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable();
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/test/**").permitAll()
                .antMatchers(HttpMethod.GET, "/images/storage/file/**").permitAll()
                .anyRequest().hasAuthority(UserRole.ROLE_ADMIN.name())
                .and()
                .apply(new JWTSecurityConfig(jwtProvider));
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

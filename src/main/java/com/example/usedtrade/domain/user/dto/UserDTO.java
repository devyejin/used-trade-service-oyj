package com.example.usedtrade.domain.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;
/*
    JwtUserDetailsService의 loadUserByUsername() 결과를 처리하기 위한 dto
    security.core.userdetails.User 클래스는 UserDetails 인터페이스를 구현한 클래스, 직접 UserDetails구현하기 번거롭기 때문에 제공하는 User 클래스 이용
 */

@Getter
@Setter
@ToString
public class UserDTO extends User implements OAuth2User {

    private String username;
    private String pwd;
    private String email;
    private boolean social;
    private Map<String, Object> props; //소셜 로그인 정보

    public UserDTO(String username, String password, String email, boolean social, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.username = username;
        this.pwd = password;
        this.email = email; //소셜 로그인할 때 값 받아옴
        this.social = social;
    }


    @Override
    public Map<String, Object> getAttributes() { //소셜 로그인 정보 반환
        return this.getProps();
    }

    @Override
    public String getName() {
        return this.username;
    }
}

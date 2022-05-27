package az.unibank.unitech.security;

import az.unibank.unitech.entity.RoleEntity;
import az.unibank.unitech.entity.UserEntity;
import az.unibank.unitech.enums.RoleEnum;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class JwtUserDetails implements UserDetails {
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public static JwtUserDetails of(UserEntity userEntity) {
        return JwtUserDetails.builder()
                .username(userEntity.getPin())
                .password("")
                .authorities(getGrantedAuthority(userEntity.getRoles()))
                .build();
    }

    private static Collection<? extends GrantedAuthority> getGrantedAuthority(List<RoleEntity> roleEnumList) {
        return roleEnumList.stream()
                .map(roleEntity -> new SimpleGrantedAuthority(roleEntity.getName().name()))
                .collect(Collectors.toList());
    }
}

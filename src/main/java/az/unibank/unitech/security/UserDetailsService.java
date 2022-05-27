package az.unibank.unitech.security;

import az.unibank.unitech.entity.UserEntity;
import az.unibank.unitech.enums.BusinessStatusEnum;
import az.unibank.unitech.enums.UserStatusEnum;
import az.unibank.unitech.exception.CommonException;
import az.unibank.unitech.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String pin) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByPin(pin)
                .orElseThrow(() -> CommonException.of(BusinessStatusEnum.USERNAME_OR_PASSWORD_INCORRECT));
        if (UserStatusEnum.NON_ACTIVE.equals(userEntity.getStatus())) {
            CommonException.of(BusinessStatusEnum.NON_ACTIVE_USER).throwEx();
        }
        return JwtUserDetails.of(userEntity);
    }
}

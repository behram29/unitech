package az.unibank.unitech.service.business;

import az.unibank.unitech.controller.request.LoginRequest;
import az.unibank.unitech.controller.request.RegistrationRequest;
import az.unibank.unitech.controller.response.LoginResponse;
import az.unibank.unitech.controller.response.RegistrationResponse;
import az.unibank.unitech.dto.RoleDto;
import az.unibank.unitech.dto.UserDto;
import az.unibank.unitech.dto.UserTokenDto;
import az.unibank.unitech.entity.UserEntity;
import az.unibank.unitech.enums.BusinessStatusEnum;
import az.unibank.unitech.enums.JwtTokenTypeEnum;
import az.unibank.unitech.enums.RoleEnum;
import az.unibank.unitech.exception.CommonException;
import az.unibank.unitech.mapping.UserMapping;
import az.unibank.unitech.mapping.UserTokenMapping;
import az.unibank.unitech.security.JwtTokenUtil;
import az.unibank.unitech.service.functional.RoleFunctionalService;
import az.unibank.unitech.service.functional.UserFunctionalService;
import az.unibank.unitech.service.functional.UserTokenFunctionalService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserFunctionalService userFunctionalService;
    private final RoleFunctionalService roleFunctionalService;
    private final UserTokenFunctionalService userTokenFunctionalService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    public LoginResponse login(LoginRequest request) {
        UserDto userDto = userFunctionalService.findByUserPin(request.getPin())
                .orElseThrow(() -> CommonException.of(BusinessStatusEnum.USERNAME_OR_PASSWORD_INCORRECT));
        if (!passwordEncoder.matches(request.getPassword(), userDto.getPassword()))
            CommonException.of(BusinessStatusEnum.USERNAME_OR_PASSWORD_INCORRECT).throwEx();
        String accessToken = jwtTokenUtil.createToken(request.getPin(), JwtTokenTypeEnum.ACCESS);
        String refreshToken = jwtTokenUtil.createToken(request.getPin(), JwtTokenTypeEnum.REFRESH);
        UserTokenDto userTokenDto = userTokenFunctionalService.findByUserPin(request.getPin())
                .orElse(
                        UserTokenDto
                                .builder()
                                .userPin(request.getPin())
                                .build()
                );
        userTokenDto.setAccessToken(accessToken);
        userTokenDto.setRefreshToken(refreshToken);
        UserTokenDto save = userTokenFunctionalService.save(UserTokenMapping.toEntity(userTokenDto));
        return LoginResponse.builder()
                .accessToken(save.getAccessToken())
                .refreshToken(save.getRefreshToken())
                .build();
    }

    public RegistrationResponse register(RegistrationRequest request) {
        if (userFunctionalService.existsUserPin(request.getPin()))
            CommonException.of(BusinessStatusEnum.USERNAME_EXISTS).throwEx();
        RoleDto roleDto = roleFunctionalService.findByRoleName(RoleEnum.USER)
                .orElseThrow(() -> CommonException.of(BusinessStatusEnum.ROLE_EMPTY));
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        UserEntity userEntity = UserMapping.newInstanceSignUp(request, Collections.singletonList(roleDto));
        UserDto userDto = userFunctionalService.save(userEntity);
        return RegistrationResponse.builder()
                .id(userDto.getId())
                .userPin(userDto.getUserPin())
                .build();
    }
}

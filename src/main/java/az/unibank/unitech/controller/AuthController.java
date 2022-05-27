package az.unibank.unitech.controller;

import az.unibank.unitech.controller.request.LoginRequest;
import az.unibank.unitech.controller.request.RegistrationRequest;
import az.unibank.unitech.controller.response.CommonResponse;
import az.unibank.unitech.controller.response.LoginResponse;
import az.unibank.unitech.controller.response.RegistrationResponse;
import az.unibank.unitech.enums.BusinessStatusEnum;
import az.unibank.unitech.service.business.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public CommonResponse<LoginResponse> signIn(@Validated @RequestBody LoginRequest signInRequest,
                                                @PathParam("lang") String lang) {
        return CommonResponse.newInstance(authService.login(signInRequest), BusinessStatusEnum.SUCCESS, lang);
    }

    @PostMapping("/register")
    public CommonResponse<RegistrationResponse> signUp(@Validated @RequestBody RegistrationRequest registrationRequest,
                                                       @PathParam("lang") String lang) {
        return CommonResponse.newInstance(authService.register(registrationRequest),
                BusinessStatusEnum.SUCCESS, lang);
    }



}

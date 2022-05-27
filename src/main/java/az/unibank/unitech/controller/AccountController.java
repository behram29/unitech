package az.unibank.unitech.controller;

import az.unibank.unitech.controller.request.TransferMoneyRequest;
import az.unibank.unitech.controller.response.AccountInfoResponse;
import az.unibank.unitech.controller.response.CommonResponse;
import az.unibank.unitech.controller.response.TransferMoneyResponse;
import az.unibank.unitech.enums.BusinessStatusEnum;
import az.unibank.unitech.security.JwtUserDetails;
import az.unibank.unitech.service.business.AccountService;
import az.unibank.unitech.service.business.TransferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountBusinessService;
    private final TransferService transferBusinessService;

    @GetMapping("info")
    public CommonResponse<List<AccountInfoResponse>> accountInfo(@AuthenticationPrincipal JwtUserDetails userDetails,
                                                                 @RequestParam("lang") String lang) {
        return CommonResponse.response(accountBusinessService.findUserAccounts(userDetails), BusinessStatusEnum.SUCCESS, null, lang);
    }

    @PostMapping("account-to-account")
    public CommonResponse<TransferMoneyResponse> accountInfo(@AuthenticationPrincipal JwtUserDetails userDetails,
                                                             @RequestBody TransferMoneyRequest request,
                                                             @RequestParam("lang") String lang) {
        return CommonResponse.response(transferBusinessService.account2Account(userDetails.getUsername(), request), BusinessStatusEnum.SUCCESS, null, lang);
    }
}

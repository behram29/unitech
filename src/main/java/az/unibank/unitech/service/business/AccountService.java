package az.unibank.unitech.service.business;

import az.unibank.unitech.controller.response.AccountInfoResponse;
import az.unibank.unitech.security.JwtUserDetails;
import az.unibank.unitech.service.functional.AccountFunctionalService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
    private final AccountFunctionalService accountFunctionalService;

    public AccountService(AccountFunctionalService accountFunctionalService) {
        this.accountFunctionalService = accountFunctionalService;
    }

    public List<AccountInfoResponse> findUserAccounts(JwtUserDetails userDetails) {
        return accountFunctionalService.findAccountsByUserPin(userDetails.getUsername())
                .stream().map(AccountInfoResponse::fillDataByAccountsDto)
                .collect(Collectors.toList());
    }
}

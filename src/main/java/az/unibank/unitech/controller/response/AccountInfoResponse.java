package az.unibank.unitech.controller.response;

import az.unibank.unitech.dto.AccountsDto;
import az.unibank.unitech.dto.CurrencyDto;
import az.unibank.unitech.enums.AccountStatusEnum;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccountInfoResponse {
    private CurrencyDto currency;
    private Long accountId;
    private BigDecimal balance;
    private AccountStatusEnum status;

    public static AccountInfoResponse fillDataByAccountsDto(AccountsDto accountsDto) {
        return AccountInfoResponse.builder()
                .currency(accountsDto.getCurrency())
                .accountId(accountsDto.getAccountId())
                .balance(accountsDto.getBalance())
                .status(accountsDto.getStatus())
                .build();
    }
}

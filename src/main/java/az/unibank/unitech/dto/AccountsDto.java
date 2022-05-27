package az.unibank.unitech.dto;

import az.unibank.unitech.entity.AccountsEntity;
import az.unibank.unitech.enums.AccountStatusEnum;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccountsDto {
    private Long id;
    private String userPin;
    private CurrencyDto currency;
    private Long accountId;
    private BigDecimal balance;
    private AccountStatusEnum status;

    public static AccountsDto toDto(AccountsEntity entity) {
        return AccountsDto.builder()
                .id(entity.getId())
                .accountId(entity.getAccountId())
                .balance(entity.getBalance())
                .currency(CurrencyDto.toDto(entity.getCurrency()))
                .status(entity.getStatus())
                .build();
    }
}

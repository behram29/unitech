package az.unibank.unitech.service.business;

import az.unibank.unitech.controller.request.TransferMoneyRequest;
import az.unibank.unitech.controller.response.TransferMoneyResponse;
import az.unibank.unitech.dto.AccountsDto;
import az.unibank.unitech.entity.TransferHistoryEntity;
import az.unibank.unitech.enums.AccountStatusEnum;
import az.unibank.unitech.enums.BusinessStatusEnum;
import az.unibank.unitech.enums.TransferStatusEnum;
import az.unibank.unitech.exception.CommonException;
import az.unibank.unitech.service.functional.AccountFunctionalService;
import az.unibank.unitech.service.functional.TransferFunctionalService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransferService {

    private final TransferFunctionalService transferFunctionalService;
    private final AccountFunctionalService accountFunctionalService;

    public TransferService(TransferFunctionalService transferFunctionalService, AccountFunctionalService accountFunctionalService) {
        this.transferFunctionalService = transferFunctionalService;
        this.accountFunctionalService = accountFunctionalService;
    }

    public TransferMoneyResponse account2Account(String userPin, TransferMoneyRequest request) {
        checkAccountsIdEquals(request.getDrAccountId(), request.getCrAccountId());
        checkDrAccountBelongUserPin(userPin, request.getDrAccountId());
        AccountsDto drAccountDto = findDrAccount(request.getDrAccountId());
        AccountsDto crAccountDto = findCrAccount(request.getCrAccountId());
        checkEnoughMoney(drAccountDto, request.getAmount());
        checkAccountBlocks(drAccountDto, crAccountDto);
        return transferMoney(request);
    }

    private void checkDrAccountBelongUserPin(String userPin, Long accountId) {
        if (!accountFunctionalService.existsAccountIdBelongUserPin(userPin, accountId)) {
            CommonException.newInstance(BusinessStatusEnum.DR_ACCOUNT_DOES_NOT_EXISTS).throwEx();
        }
    }

    private AccountsDto findCrAccount(Long crAccount) {
        return accountFunctionalService.findAccountByAccountId(crAccount)
                .orElseThrow(() -> CommonException.newInstance(BusinessStatusEnum.CR_ACCOUNT_DOES_NOT_EXISTS));
    }

    private AccountsDto findDrAccount(Long drAccount) {
        return accountFunctionalService.findAccountByAccountId(drAccount)
                .orElseThrow(() -> CommonException.newInstance(BusinessStatusEnum.DR_ACCOUNT_DOES_NOT_EXISTS));
    }

    private void checkAccountsIdEquals(Long drAccount, Long crAccount) {
        if (drAccount.equals(crAccount)) {
            CommonException.newInstance(BusinessStatusEnum.SAME_ACCOUNT_ERROR).throwEx();
        }
    }

    private void checkEnoughMoney(AccountsDto drAccount, BigDecimal amount) {
        if (drAccount.getBalance().compareTo(amount) < 0) {
            CommonException.newInstance(BusinessStatusEnum.NOT_ENOUGH_MONEY).throwEx();
        }
    }

    private void checkAccountBlocks(AccountsDto drAccountDto, AccountsDto crAccountDto) {
        if (AccountStatusEnum.NONACTIVE.equals(drAccountDto.getStatus())) {
            CommonException.newInstance(BusinessStatusEnum.DR_ACCOUNT_NON_ACITVE).throwEx();
        } else if (AccountStatusEnum.NONACTIVE.equals(crAccountDto.getStatus())) {
            CommonException.newInstance(BusinessStatusEnum.CR_ACCOUNT_NON_ACITVE).throwEx();
        } else if (AccountStatusEnum.BLOCKED.equals(crAccountDto.getStatus())) {
            CommonException.newInstance(BusinessStatusEnum.CR_ACCOUNT_BLOCKED).throwEx();
        } else if (AccountStatusEnum.BLOCKED.equals(drAccountDto.getStatus())) {
            CommonException.newInstance(BusinessStatusEnum.DR_ACCOUNT_BLOCKED).throwEx();
        }
    }

    private TransferMoneyResponse transferMoney(TransferMoneyRequest request) {
        TransferHistoryEntity transferHistory = transferFunctionalService.saveOrUpdate(TransferHistoryEntity.builder()
                .amount(request.getAmount())
                .crAccountId(request.getCrAccountId())
                .drAccountId(request.getDrAccountId())
                .status(TransferStatusEnum.ERROR)
                .date(LocalDateTime.now())
                .build());
        AccountsDto drAccount = findDrAccount(request.getDrAccountId());
        checkEnoughMoney(drAccount, request.getAmount());
        AccountsDto crAccount = findCrAccount(request.getCrAccountId());
        drAccount.setBalance(drAccount.getBalance().subtract(request.getAmount()));
        crAccount.setBalance(crAccount.getBalance().add(request.getAmount()));
        transferHistory.setStatus(TransferStatusEnum.SUCCESS);
        transferHistory.setDate(LocalDateTime.now());
        TransferHistoryEntity transferHistory1 = transferFunctionalService.saveOrUpdate(transferHistory);
        return TransferMoneyResponse.builder()
                .transactionId(transferHistory.getId())
                .transactionTime(transferHistory1.getDate())
                .build();
    }


}

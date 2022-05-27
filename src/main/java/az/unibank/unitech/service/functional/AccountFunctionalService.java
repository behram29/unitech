package az.unibank.unitech.service.functional;

import az.unibank.unitech.dto.AccountsDto;
import az.unibank.unitech.repository.AccountsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountFunctionalService {
    private final AccountsRepository accountsRepository;

    public AccountFunctionalService(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    public List<AccountsDto> findAccountsByUserPin(String userPin) {
        return accountsRepository.findByUserPin(userPin).stream()
                .map(AccountsDto::toDto).collect(Collectors.toList());
    }

    public Optional<AccountsDto> findAccountByAccountId(Long accountId) {
        return accountsRepository.findByAccountId(accountId)
                .map(AccountsDto::toDto);
    }

    public boolean existsAccountIdBelongUserPin(String userPin, Long accountId) {
        return accountsRepository.existsByUserPinAndAccountId(userPin, accountId);
    }
}

package az.unibank.unitech.repository;

import az.unibank.unitech.entity.AccountsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<AccountsEntity, Long> {
    List<AccountsEntity> findByUserPin(String userPin);

    Optional<AccountsEntity> findByAccountId(Long accountId);

    boolean existsByUserPinAndAccountId(String userPin, Long accountId);
}

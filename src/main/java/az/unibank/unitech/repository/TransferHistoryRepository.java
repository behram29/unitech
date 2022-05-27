package az.unibank.unitech.repository;

import az.unibank.unitech.entity.TransferHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferHistoryRepository extends JpaRepository<TransferHistoryEntity, Long> {
}

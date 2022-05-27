package az.unibank.unitech.service.functional;

import az.unibank.unitech.entity.TransferHistoryEntity;
import az.unibank.unitech.repository.TransferHistoryRepository;
import org.springframework.stereotype.Service;

@Service
public class TransferFunctionalService {
    private final TransferHistoryRepository transferHistoryRepository;

    public TransferFunctionalService(TransferHistoryRepository transferHistoryRepository) {
        this.transferHistoryRepository = transferHistoryRepository;
    }

    public TransferHistoryEntity saveOrUpdate(TransferHistoryEntity transferHistoryEntity) {
        return transferHistoryRepository.save(transferHistoryEntity);
    }
}

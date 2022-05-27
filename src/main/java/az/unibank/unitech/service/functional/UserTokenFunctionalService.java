package az.unibank.unitech.service.functional;


import az.unibank.unitech.dto.UserTokenDto;
import az.unibank.unitech.entity.UserTokenEntity;
import az.unibank.unitech.mapping.UserTokenMapping;
import az.unibank.unitech.repository.UserTokenRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserTokenFunctionalService {
    private final UserTokenRepository userTokenRepository;

    public UserTokenFunctionalService(UserTokenRepository userTokenRepository) {
        this.userTokenRepository = userTokenRepository;
    }

    public Optional<UserTokenDto> findByUserPin(String userPin) {
        return userTokenRepository.findByUserPin(userPin)
                .map(UserTokenMapping::toDto);
    }

    public UserTokenDto save(UserTokenEntity entity) {
        return UserTokenMapping.toDto(userTokenRepository.save(entity));
    }
}

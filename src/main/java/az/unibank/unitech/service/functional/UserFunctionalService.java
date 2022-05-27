package az.unibank.unitech.service.functional;


import az.unibank.unitech.dto.UserDto;
import az.unibank.unitech.entity.UserEntity;
import az.unibank.unitech.mapping.UserMapping;
import az.unibank.unitech.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserFunctionalService {

    private final UserRepository userRepository;

    public UserFunctionalService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserDto> findByUserPin(String userPin) {
        return userRepository.findByPin(userPin).map(UserMapping::toDto);
    }

    public boolean existsUserPin(String userPin) {
        return userRepository.existsByPin(userPin);
    }

    public UserDto save(UserEntity userEntity) {
        return UserMapping.toDto(userRepository.save(userEntity));
    }
}

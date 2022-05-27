package az.unibank.unitech.repository;

import az.unibank.unitech.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByPin(String userPin);

    boolean existsByPin(String userPin);
}

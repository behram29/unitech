package az.unibank.unitech.repository;


import az.unibank.unitech.entity.UserTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTokenRepository extends JpaRepository<UserTokenEntity, Long> {

    Optional<UserTokenEntity> findByUserPin(String userPin);

    boolean existsByAccessTokenAndUserPin(String accessToken, String userPin);

    boolean existsByRefreshTokenAndUserPin(String refreshToken, String userPin);
}

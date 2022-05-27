package az.unibank.unitech.mapping;


import az.unibank.unitech.dto.UserTokenDto;
import az.unibank.unitech.entity.UserTokenEntity;

public final class UserTokenMapping {
    public static UserTokenDto toDto(UserTokenEntity entity) {
        return UserTokenDto.builder()
                .id(entity.getId())
                .userPin(entity.getUserPin())
                .accessToken(entity.getAccessToken())
                .refreshToken(entity.getRefreshToken())
                .build();
    }

    public static UserTokenEntity toEntity(UserTokenDto dto) {
        return UserTokenEntity.builder()
                .id(dto.getId())
                .userPin(dto.getUserPin())
                .accessToken(dto.getAccessToken())
                .refreshToken(dto.getRefreshToken())
                .build();
    }
}

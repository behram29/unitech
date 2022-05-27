package az.unibank.unitech.mapping;


import az.unibank.unitech.controller.request.RegistrationRequest;
import az.unibank.unitech.dto.RoleDto;
import az.unibank.unitech.dto.UserDto;
import az.unibank.unitech.entity.UserEntity;
import az.unibank.unitech.enums.UserStatusEnum;

import java.util.List;
import java.util.stream.Collectors;

public final class UserMapping {
    public static UserDto toDto(UserEntity entity) {
        return UserDto.builder()
                .id(entity.getId())
                .userPin(entity.getPin())
                .password(entity.getPassword())
                .status(entity.getStatus())
                .roleDtos(entity.getRoles()
                        .stream()
                        .map(RoleMapping::toDto)
                        .collect(Collectors.toList()))
                .build();
    }

    public static UserEntity toEntity(UserDto dto) {
        return UserEntity.builder()
                .id(dto.getId())
                .pin(dto.getUserPin())
                .password(dto.getPassword())
                .status(dto.getStatus())
                .roles(dto.getRoleDtos()
                        .stream()
                        .map(RoleMapping::toEntity)
                        .collect(Collectors.toList()))
                .build();
    }

    public static UserEntity newInstanceSignUp(RegistrationRequest request, List<RoleDto> roleEntity) {
        return UserEntity.builder()
                .pin(request.getPin())
                .password(request.getPassword())
                .status(UserStatusEnum.ACTIVE)
                .roles(roleEntity.stream().map(RoleMapping::toEntity).collect(Collectors.toList()))
                .build();
    }

}

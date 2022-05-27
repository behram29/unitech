package az.unibank.unitech.mapping;


import az.unibank.unitech.dto.RoleDto;
import az.unibank.unitech.entity.RoleEntity;

public final class RoleMapping {
    public static RoleDto toDto(RoleEntity entity) {
        return RoleDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public static RoleEntity toEntity(RoleDto dto) {
        return RoleEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }
}

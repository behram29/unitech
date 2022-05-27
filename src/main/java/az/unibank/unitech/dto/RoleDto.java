package az.unibank.unitech.dto;

import az.unibank.unitech.enums.RoleEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDto {
    private Long id;
    private RoleEnum name;
}

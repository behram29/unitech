package az.unibank.unitech.dto;

import az.unibank.unitech.enums.UserStatusEnum;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDto {
    private Long id;
    private String userPin;
    private String password;
    private List<RoleDto> roleDtos;
    private UserStatusEnum status;
}

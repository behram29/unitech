package az.unibank.unitech.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserTokenDto {
    private Long id;
    private String userPin;
    private String accessToken;
    private String refreshToken;
}

package az.unibank.unitech.dto;

import az.unibank.unitech.entity.CurrencyEntity;
import az.unibank.unitech.enums.CurrencyEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurrencyDto {
    private Long id;
    private CurrencyEnum name;


    public static CurrencyDto toDto(CurrencyEntity entity) {
        return CurrencyDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}

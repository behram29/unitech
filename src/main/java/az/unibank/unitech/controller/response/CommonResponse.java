package az.unibank.unitech.controller.response;

import az.unibank.unitech.enums.BusinessStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonResponse<T> {
    private Long id;
    private String systemMessage;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime time;
    private Integer statusCode;
    private String message;
    private T data;

    public static <T> CommonResponse<T> newInstance(T data, BusinessStatusEnum businessResultEnum, String lang) {
        return CommonResponse.<T>builder()
                .id(Long.valueOf(businessResultEnum.getCode()))
                .data(data)
                .message(businessResultEnum.getMessageByLang(lang))
                .statusCode(businessResultEnum.getHttpStatus().value())
                .systemMessage(businessResultEnum.getSystemMessage())
                .time(LocalDateTime.now())
                .build();
    }

    public static <T> CommonResponse<T> newInstance(T data, String systemMessage, BusinessStatusEnum businessResultEnum, String lang) {
        return CommonResponse.<T>builder()
                .id(Long.valueOf(businessResultEnum.getCode()))
                .data(data)
                .message(businessResultEnum.getMessageByLang(lang))
                .statusCode(businessResultEnum.getHttpStatus().value())
                .systemMessage(systemMessage)
                .time(LocalDateTime.now())
                .build();
    }

    public static <T> CommonResponse<T> response(T data, BusinessStatusEnum businessStatusEnum, String systemMessage, String lang) {
        return CommonResponse.<T>builder()
                .id(Long.valueOf(businessStatusEnum.getCode()))
                .message(businessStatusEnum.getMessageByLang(lang))
                .data(data)
                .systemMessage(systemMessage == null ? businessStatusEnum.getSystemMessage() : systemMessage)
                .statusCode(businessStatusEnum.getHttpStatus().value())
                .build();
    }
}

package az.unibank.unitech.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransferMoneyResponse implements Serializable {
    private Long transactionId;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:yy")
    private LocalDateTime transactionTime;
}

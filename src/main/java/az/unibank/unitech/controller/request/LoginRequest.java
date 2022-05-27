package az.unibank.unitech.controller.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class LoginRequest implements Serializable {

    @NotNull
    @Size(min = 7, max = 7)
    private String pin;
    @NotNull
    @Size(min = 8, max = 32)
    private String password;

}

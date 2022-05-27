package az.unibank.unitech.exception;

import az.unibank.unitech.controller.response.CommonResponse;
import az.unibank.unitech.enums.BusinessStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final HttpServletRequest request;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResponse<?> unknownException(Exception exception) {
        exception.printStackTrace();
        String lang = request.getParameter("lang");
        return CommonResponse.newInstance(null, exception.getMessage(), BusinessStatusEnum.INTERNAL_ERROR, lang);
    }

    @ExceptionHandler(CommonException.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse<?> unknownException(CommonException exception) {
        String lang = request.getParameter("lang");
        return CommonResponse.newInstance(null, exception.getBusinessResultEnum(), lang);
    }
}

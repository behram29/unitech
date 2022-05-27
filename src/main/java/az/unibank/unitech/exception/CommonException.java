package az.unibank.unitech.exception;



import az.unibank.unitech.enums.BusinessStatusEnum;

import java.io.Serializable;

public class CommonException extends RuntimeException implements Serializable {

    private BusinessStatusEnum businessResultEnum;

    public CommonException(BusinessStatusEnum businessResultEnum) {
        this.businessResultEnum = businessResultEnum;
    }

    public static CommonException of(BusinessStatusEnum businessResultEnum) {
        return new CommonException(businessResultEnum);
    }

    public static CommonException newInstance(BusinessStatusEnum businessStatusEnum) {
        return new CommonException(businessStatusEnum);
    }

    public BusinessStatusEnum getBusinessResultEnum() {
        return businessResultEnum;
    }

    public void throwEx() {
        throw this;
    }
}

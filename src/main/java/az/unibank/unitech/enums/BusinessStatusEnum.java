package az.unibank.unitech.enums;

import org.springframework.http.HttpStatus;

public enum BusinessStatusEnum {
    SUCCESS(0, "Successfully Process", "Az", "Ru", "En", HttpStatus.OK),
    ACCESS_TOKEN_EXPIRED(1, "Access token expired", "messageAz", "messageRu", "messageEn", HttpStatus.ACCEPTED),
    REFRESH_TOKEN_EXPIRED(2, "Refresh token expired", "messageAz", "messageRu", "messageEn", HttpStatus.ACCEPTED),
    USER_BLOCKED(3, "User is blocked", "messageAz", "messageRu", "messageEn", HttpStatus.ACCEPTED),
    USERNAME_OR_PASSWORD_INCORRECT(5, "Username or password is incorrect", "messageAz", "messageRu", "messageEn", HttpStatus.ACCEPTED),
    TOKEN_INVALID(6, "Token is invalid", "messageAz", "messageRu", "messageEn", HttpStatus.ACCEPTED),
    USERNAME_EXISTS(7, "Username is already exists", "messageAz", "messageRu", "messageEn", HttpStatus.ACCEPTED),
    ROLE_EMPTY(8, "Role is empty", "messageAz", "messageRu", "messageEn", HttpStatus.ACCEPTED),
    NON_ACTIVE_USER(9, "User is not active", "messageAz", "messageRu", "messageEn", HttpStatus.ACCEPTED),
    DR_ACCOUNT_DOES_NOT_EXISTS(10, "DR_ACCOUNT_DOES_NOT_EXISTS", "DR_ACCOUNT_DOES_NOT_EXISTS", "DR_ACCOUNT_DOES_NOT_EXISTS", "DR_ACCOUNT_DOES_NOT_EXISTS", HttpStatus.BAD_REQUEST),
    CR_ACCOUNT_DOES_NOT_EXISTS(11, "CR_ACCOUNT_DOES_NOT_EXISTS", "CR_ACCOUNT_DOES_NOT_EXISTS", "CR_ACCOUNT_DOES_NOT_EXISTS", "CR_ACCOUNT_DOES_NOT_EXISTS", HttpStatus.BAD_REQUEST),
    SAME_ACCOUNT_ERROR(12, "SAME_ACCOUNT_ERROR", "You cannot transfer money to the same account", "You cannot transfer money to the same account", "You cannot transfer money to the same account", HttpStatus.BAD_REQUEST),
    NOT_ENOUGH_MONEY(13, "NOT_ENOUGH_MONEY", "Not enough money in the account", "Not enough money in the account", "Not enough money in the account", HttpStatus.BAD_REQUEST),
    DR_ACCOUNT_NON_ACITVE(14, "DR_ACCOUNT_NON_ACITVE", "DR_ACCOUNT_NON_ACITVE", "DR_ACCOUNT_NON_ACITVE", "DR_ACCOUNT_NON_ACITVE", HttpStatus.BAD_REQUEST),
    CR_ACCOUNT_NON_ACITVE(15, "CR_ACCOUNT_NON_ACITVE", "CR_ACCOUNT_NON_ACITVE", "CR_ACCOUNT_NON_ACITVE", "CR_ACCOUNT_NON_ACITVE", HttpStatus.BAD_REQUEST),
    DR_ACCOUNT_BLOCKED(16, "DR_ACCOUNT_BLOCKED", "DR_ACCOUNT_BLOCKED", "DR_ACCOUNT_BLOCKED", "DR_ACCOUNT_BLOCKED", HttpStatus.BAD_REQUEST),
    CR_ACCOUNT_BLOCKED(17, "CR_ACCOUNT_BLOCKED", "CR_ACCOUNT_BLOCKED", "CR_ACCOUNT_BLOCKED", "CR_ACCOUNT_BLOCKED", HttpStatus.BAD_REQUEST),
    INTERNAL_ERROR(99, "General Error", "MessageAz", "MessageRu", "MessageEn", HttpStatus.INTERNAL_SERVER_ERROR);

    private final Integer code;
    private final String systemMessage;
    private final String messageAz;
    private final String messageRu;
    private final String messageEn;
    private final HttpStatus httpStatus;

    BusinessStatusEnum(Integer code, String systemMessage, String messageAz, String messageRu, String messageEn, HttpStatus httpStatus) {
        this.code = code;
        this.systemMessage = systemMessage;
        this.messageAz = messageAz;
        this.messageRu = messageRu;
        this.messageEn = messageEn;
        this.httpStatus = httpStatus;
    }

    public Integer getCode() {
        return code;
    }

    public String getSystemMessage() {
        return systemMessage;
    }

    public String getMessageAz() {
        return messageAz;
    }

    public String getMessageRu() {
        return messageRu;
    }

    public String getMessageEn() {
        return messageEn;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessageByLang(String lang) {
        if ("en".equalsIgnoreCase(lang)) {
            return messageEn;
        } else if ("ru".equalsIgnoreCase(lang)) {
            return messageRu;
        } else {
            return messageAz;
        }
    }
}

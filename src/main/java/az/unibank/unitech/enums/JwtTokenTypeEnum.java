package az.unibank.unitech.enums;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.function.Function;

public enum JwtTokenTypeEnum {

    ACCESS(pin -> {
        Claims claims = Jwts.claims().setSubject(pin);
        claims.put("roles", "ACCESS");
        return claims;
    }, 3600000, BusinessStatusEnum.ACCESS_TOKEN_EXPIRED, "ACCESS"),
    REFRESH(pin -> {
        Claims claims = Jwts.claims().setSubject(pin);
        claims.put("roles", "REFRESH");
        return claims;
    }, 86400000, BusinessStatusEnum.REFRESH_TOKEN_EXPIRED, "REFRESH");

    private final Function<String, Claims> createClaims;
    private final long validityTime;
    private BusinessStatusEnum businessResultEnum;
    private String roles;

    JwtTokenTypeEnum(Function<String, Claims> createClaims, long validityTime, BusinessStatusEnum businessResultEnum, String roles) {
        this.createClaims = createClaims;
        this.validityTime = validityTime;
        this.businessResultEnum = businessResultEnum;
        this.roles = roles;
    }

    public Claims apply(String pin) {
        return createClaims.apply(pin);
    }

    public long getValidityTime() {
        return validityTime;
    }

    public BusinessStatusEnum getBusinessResultEnum() {
        return businessResultEnum;
    }

    public void setBusinessResultEnum(BusinessStatusEnum businessResultEnum) {
        this.businessResultEnum = businessResultEnum;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public static JwtTokenTypeEnum getTokenTypeByRoles(String role) {
        for (JwtTokenTypeEnum type : values()) {
            if (type.getRoles().equalsIgnoreCase(role)) {
                return type;
            }
        }
        return ACCESS;
    }

}

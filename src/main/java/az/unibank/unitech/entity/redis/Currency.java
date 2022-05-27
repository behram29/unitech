package az.unibank.unitech.entity.redis;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("currency")
public class Currency implements Serializable {
    private String nominal;
    private String name;
    private String value;
}
package az.unibank.unitech.repository.redis;

import az.unibank.unitech.entity.redis.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Currency, String> {}
package co.misiontic.p53.account_ms.repositories;

import co.misiontic.p53.account_ms.models.Account;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {
    List<Account> findAllByBalance(Integer balance);
}

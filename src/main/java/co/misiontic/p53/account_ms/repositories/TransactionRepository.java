package co.misiontic.p53.account_ms.repositories;

import co.misiontic.p53.account_ms.models.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findByUsernameOrigin (String usernameOrigin);
    List<Transaction> findByUsernameDestiny (String usernameDestiny);
}

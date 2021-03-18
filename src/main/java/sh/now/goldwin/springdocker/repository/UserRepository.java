package sh.now.goldwin.springdocker.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import sh.now.goldwin.springdocker.model.User;

public interface UserRepository extends ReactiveMongoRepository<User, String> {

  Mono<User> findByUsername(String username);
}

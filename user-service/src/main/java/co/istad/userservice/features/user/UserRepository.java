package co.istad.userservice.features.user;

import co.istad.userservice.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

}

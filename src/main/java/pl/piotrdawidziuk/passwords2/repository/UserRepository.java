package pl.piotrdawidziuk.passwords2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piotrdawidziuk.passwords2.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}

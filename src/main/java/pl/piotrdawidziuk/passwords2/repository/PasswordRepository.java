package pl.piotrdawidziuk.passwords2.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.piotrdawidziuk.passwords2.model.Password;
import pl.piotrdawidziuk.passwords2.model.User;

import java.util.List;

public interface PasswordRepository extends JpaRepository<Password, Long> {

    public List<Password> findAllByUser(User id);

}

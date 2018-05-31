
package pl.piotrdawidziuk.passwords2.model;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @NotBlank(message = "Nazwa użytkownika nie może być pusta!")
    String username;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Password> passwords;

    @NotBlank(message = "E-mail nie może być pusty!")
    @Email(message = "Podaj poprawny adres e-mail!")
    String email;

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Password> getPasswords() {
        return passwords;
    }

    public void setPasswords(List<Password> passwords) {
        this.passwords = passwords;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

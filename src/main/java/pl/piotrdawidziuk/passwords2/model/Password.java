package pl.piotrdawidziuk.passwords2.model;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "passwords")

public class Password {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String value;

    @ManyToOne
    User user;

    public Password() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
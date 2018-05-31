package pl.piotrdawidziuk.passwords2.converter;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.convert.converter.Converter;

import org.springframework.stereotype.Component;

import pl.piotrdawidziuk.passwords2.model.User;
import pl.piotrdawidziuk.passwords2.repository.UserRepository;

@Component
public class UserConverter implements Converter<String, User> {

    @Autowired
    private UserRepository userRepository;

    public UserConverter() {

    }

    @Override
    public User convert(String s) {

        User user = userRepository.findById(Long.valueOf(s)).orElse(null);

        return user;

    }
}

package pl.piotrdawidziuk.passwords2.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.piotrdawidziuk.passwords2.model.User;
import pl.piotrdawidziuk.passwords2.repository.PasswordRepository;
import pl.piotrdawidziuk.passwords2.repository.UserRepository;

import javax.validation.Valid;

@Controller
public class UserController {


    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordRepository passwordRepository;

    @GetMapping("/reg")
    String registrationForm(Model model) {
        model.addAttribute("user", new User());

        return "form/registration";
    }

    @PostMapping("/reg")
    String registration(@Valid User user, BindingResult result) {

        if (result.hasErrors()) {
            return "form/registration";
        } else {
            userRepository.save(user);
            long id = user.getId();
            return "redirect:/pass/" + id;
        }
    }
}

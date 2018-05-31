package pl.piotrdawidziuk.passwords2.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.format.FormatterRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.piotrdawidziuk.passwords2.converter.UserConverter;
import pl.piotrdawidziuk.passwords2.lib.FileLoader;
import pl.piotrdawidziuk.passwords2.model.Messages;
import pl.piotrdawidziuk.passwords2.model.Password;
import pl.piotrdawidziuk.passwords2.model.User;
import pl.piotrdawidziuk.passwords2.repository.PasswordRepository;
import pl.piotrdawidziuk.passwords2.repository.UserRepository;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PasswordController {

    @Autowired
    PasswordRepository passwordRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/pass/{id}")
    String passForm(@PathVariable long id, Model model) {
        model.addAttribute("password", new Password());

        return "form/pass";
    }


    @Autowired
    FileLoader fileLoader;

    //  File file =


    //   String[] words = readFile().toArray(new String[0]);
    /// public List<String> readFile() {

    //  List<String> list = new ArrayList<>();
    //try (BufferedReader br = new BufferedReader(new FileReader(fileLoader.get("english.txt");))) {

    //  String sCurrentLine;

    //while ((sCurrentLine = br.readLine()) != null) {
    //  list.add(sCurrentLine);
    //}

//        } catch (IOException e, ) {
//            e.printStackTrace();
//        }
//
//        return list;
//    }


    @PostMapping("/pass/{id}")
    String pass(@PathVariable long id, Model model, @Valid Password password, BindingResult result) throws IOException {

        File file = fileLoader.get("static/english.txt");
        String sCurrentLine;
        List<String> lista = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        while ((sCurrentLine = br.readLine()) != null) {
            lista.add(sCurrentLine);
        }


        String[] words = lista.toArray(new String[0]);


        if (result.hasErrors()) {
            return "form/pass";
        } else {

            password.setId(0);
            password.setUser(userRepository.findById(id).orElse(null));
            passwordRepository.save(password);

            Messages messages = new Messages(password);


            List<String> list = messages.createList(words);
            List<String> list2 = messages.createListPositive();
            model.addAttribute("messages", list);
            model.addAttribute("messages2", list2);
            model.addAttribute("user_id", id);


            return "form/pass";
        }
    }

    @ModelAttribute("users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    //
//    @ModelAttribute("messages")
//    public List<String> messages() {
//
//        List<String> list = new ArrayList<>();
//
//        list.add("cos");
//        list.add("costam");
//
//        return list;
//    }


    String deleteLink = "fin";

    @GetMapping("/fin/{id}")
    public String allPasswords(@PathVariable long id, Model model) {
        List<Password> passwords = passwordRepository.findAllByUser(userRepository.getOne(id));
        model.addAttribute("passwords", passwords);
        model.addAttribute("user_id", id);

        return deleteLink;
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable long id, Model model) {

        String link = "deleted";

        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            deleteLink = "/";
        }
        return link;
    }


    @Autowired
    UserConverter userConverter;

    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(userConverter);
    }

}

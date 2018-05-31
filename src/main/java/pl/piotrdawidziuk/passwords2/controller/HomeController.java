package pl.piotrdawidziuk.passwords2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.piotrdawidziuk.passwords2.lib.FileLoader;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String homePage() {
        return "home";
    }


    @Autowired
    FileLoader fileLoader;

    @RequestMapping("/list")
    @ResponseBody
    public String workersAction() throws IOException {
        StringBuffer result = new StringBuffer();
        File file = fileLoader.get("static/english.txt");
        Scanner scan = new Scanner(file);
        while(scan.hasNextLine()) {
            result.append(scan.nextLine()+"<br/>");
        }
        scan.close();
        return result.toString();
    }

}

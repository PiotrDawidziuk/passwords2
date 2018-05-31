package pl.piotrdawidziuk.passwords2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FinController {

    @RequestMapping("/fin")
    public String finPage(){
        return "fin";
    }

}

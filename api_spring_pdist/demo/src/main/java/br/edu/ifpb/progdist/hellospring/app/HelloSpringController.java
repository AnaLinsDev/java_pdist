package br.edu.ifpb.progdist.hellospring.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloSpringController {

    @GetMapping("/hello")
    static String hello() {
        return "Hello Ana Julia Lins";
    }


}

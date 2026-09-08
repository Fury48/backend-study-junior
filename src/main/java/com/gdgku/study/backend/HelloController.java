package com.gdgku.study.backend;

<<<<<<< HEAD
import org.springframework.web.bind.annotation.*;


@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring!";
    }

=======
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Server!";
    }
>>>>>>> 8df161d633308ddf4cbb854a591f3f4f30bfd9a3
}

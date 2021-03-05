package com.portfolio1;

import com.portfolio1.base.BaseController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@SpringBootApplication
@RestController
public class Portfolio1Application extends BaseController {

    public static void main(String[] args) {
        SpringApplication.run(Portfolio1Application.class, args);
    }

    @RequestMapping("/hello/{what}")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name, HttpSession session, HttpServletRequest request ) {
        HashMap<Object, Object> parameters = getAllParameters(request);

        session.setAttribute("hello", "eric");
        return String.format("Hello %s!", name);
    }
}

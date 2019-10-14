package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import test.domain.User;
import test.repos.UserRepo;

import java.util.Map;

@Controller
public class GreetingController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String main(Map<String,Object> model){
        Iterable<User> users = userRepo.findAll();

        model.put("users", users);
        return "main";
    }

    @PostMapping
    public String add(@RequestParam String login, @RequestParam String password, Map<String,Object> model){
        User user = new User(login, password);

        userRepo.save(user);

        Iterable<User> users = userRepo.findAll();

        model.put("users", users);

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String,Object> model){
        Iterable<User> users;
        if(filter != null && !filter.isEmpty()){
            users = userRepo.findByLogin(filter);
        } else {
            users = userRepo.findAll();
        }
        model.put("users", users);
        return "main";
    }

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Map<String, Object> model
    ) {
        model.put("name", name);
        return "greeting";
    }
}
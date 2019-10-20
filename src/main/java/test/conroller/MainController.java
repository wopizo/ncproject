package test.conroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import test.domain.Message;
import test.domain.User;
import test.repos.MessageRepo;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter,
                       Model model){
        Iterable<Message> messages = messageRepo.findAll();

        if(filter != null && !filter.isEmpty()){
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String tag,
            @RequestParam String text, Map<String,Object> model){
        Message message = new Message(tag, text, user);

        messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();

        model.put("messages", messages);

        return "main";
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }
}
package domain.company.project.module.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class DemoController {
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public String index(){
        return "GitOps";
    }
}

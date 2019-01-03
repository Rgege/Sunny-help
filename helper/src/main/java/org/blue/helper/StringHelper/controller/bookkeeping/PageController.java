package org.blue.helper.StringHelper.controller.bookkeeping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tally/")
public class PageController {

    @GetMapping("nav")
    public String top(){
        return "bookkeeping/top";
    }

    /**
     * about user
     * @return
     */
    @GetMapping("login")
    public String login(){
        return "bookkeeping/login";
    }
    @GetMapping("register")
    public String register(){
        return "bookkeeping/register";
    }
    @GetMapping("userInfo")
    public String userInfo(){
        return "bookkeeping/userInfo";
    }
    /**
     * about business
     * @return
     */
    @GetMapping("home")
    public String home(){
        return "bookkeeping/homePage";
    }
    @GetMapping("record")
    public String record(){
        return "bookkeeping/record";
    }

    @GetMapping("directory")
    public String directory(){
        return "bookkeeping/directory";
    }

    @GetMapping("detailsYear")
    public String detailsYear(){
        return "bookkeeping/detailsYear";
    }
    @GetMapping("detailsMonth")
    public String detailsMonth(){
        return "bookkeeping/detailsMonth";
    }
    @GetMapping("detailsDay")
    public String detailsDay(){
        return "bookkeeping/detailsDay";
    }


    @GetMapping("test")
    public String test(){
        return "bookkeeping/test";
    }


}

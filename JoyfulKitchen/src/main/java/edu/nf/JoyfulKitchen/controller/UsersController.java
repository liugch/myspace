package edu.nf.JoyfulKitchen.controller;

import edu.nf.JoyfulKitchen.bean.Users;
import edu.nf.JoyfulKitchen.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/3/29.
 */
@Controller
public class UsersController {

    @Resource
    private UsersService usersService;

    @RequestMapping("/index")
    public String index(Model model){
        List<Users> list = usersService.getAllUsers();
        model.addAttribute("list",list);
        return "index";
    }
}

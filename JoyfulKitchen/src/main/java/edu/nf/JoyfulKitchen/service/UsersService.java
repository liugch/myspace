package edu.nf.JoyfulKitchen.service;

import edu.nf.JoyfulKitchen.bean.Users;
import edu.nf.JoyfulKitchen.dao.UsersDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("usersService")
public class UsersService {

    @Resource
    private UsersDao usersDao;

    @Transactional(readOnly = true)

    public List<Users> getAllUsers(){

        return usersDao.getAllUsers();
    }




}

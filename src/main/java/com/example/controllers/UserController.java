package com.example.controllers;

import com.example.models.Response;
import com.example.models.User;
import com.example.models.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/create")
    @ResponseBody
    public Response create(String email, String name) {

        User user = null;

        try {

            user = new User(email, name);
            userDao.save(user);

        } catch (Exception e) {

            return new Response(e.getMessage(), e);
        }

        return new Response("Successfully created new user", user);
    }

    @RequestMapping("/get")
    @ResponseBody
    public Response get(long id) {

        User user = userDao.findOne(id);
        return new Response( user != null ?  "User successfully received" : "User not found", user);
    }

    @RequestMapping("/get-by-email")
    @ResponseBody
    public Response getByEmail(String email) {

        User user;
        try {

            user = userDao.findByEmail(email);

        } catch (Exception e) {
            return new Response(e.getMessage(), e);
        }

        return new Response( user != null ? "Successfully find user by email" : "User not found", user);
    }

    @RequestMapping("/get-all")
    @ResponseBody
    public Response getAll() {

        return new Response("Successfully received all users", userDao.findAll());
    }

    @RequestMapping("/update")
    @ResponseBody
    public Response updateUser(long id, String email, String name) {

        User user = userDao.findOne(id);

        if(user != null) {

            user.setEmail(email);
            user.setName(name);
            userDao.save(user);

            return new Response("User successfully updated", user);
        }
        else {
            return new Response("User not found", null);
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Response delete(long id) {

        User user = userDao.findOne(id);

        if(user != null) {

            userDao.delete(user);

            return new Response("User successfully deleted", user);
        }
        else {
            return new Response("User not found", null);
        }
    }
}

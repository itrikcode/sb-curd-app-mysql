package com.itsp.curdappmysql.controller;

import com.itsp.curdappmysql.bean.EmployeeLogin;
import com.itsp.curdappmysql.bean.Login;
import com.itsp.curdappmysql.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class Controller {

    @Autowired
    EmployeeService service;
    @RequestMapping(value = "/login",method = RequestMethod.POST,consumes = "application/json")
    public String login(@RequestBody Login login){
        return service.loginEmployee(login);
    }

    @PostMapping("/registre")
    public String registryEmployee(@RequestBody EmployeeLogin employeeLogin){
        return service.registryValidUser(employeeLogin);
    }
}

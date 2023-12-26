package com.itsp.curdappmysql.controller;

import com.itsp.curdappmysql.bean.Employee;
import com.itsp.curdappmysql.bean.EmployeeRequest;
import com.itsp.curdappmysql.bean.Login;
import com.itsp.curdappmysql.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String registryEmployee(@RequestBody EmployeeRequest employeeLogin){
        return service.registryValidUser(employeeLogin);
    }

    @PutMapping("/update")
    public String updateEmpoyee(@RequestBody EmployeeRequest employeeRequest){
        return service.updateEmployee(employeeRequest);
    }

    @GetMapping("/getall")
    public List<Employee> updateEmpoyee(){
        return service.getAll();
    }
}

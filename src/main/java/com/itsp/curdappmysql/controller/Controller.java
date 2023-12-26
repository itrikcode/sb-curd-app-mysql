package com.itsp.curdappmysql.controller;

import com.itsp.curdappmysql.bean.Employee;
import com.itsp.curdappmysql.bean.EmployeeRequest;
import com.itsp.curdappmysql.bean.Login;
import com.itsp.curdappmysql.service.EmployeeService;
import jakarta.websocket.server.PathParam;
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

    @GetMapping("/get/{id}")
    public Employee updateEmpoyee(@PathVariable("id") Long id){
        return service.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public Long deleteEmpoyeeById(@PathVariable("id") Long id){
        return service.deleteEmployeeById(id);
    }

    @DeleteMapping("/delete")
    public List<Long> deleteEmpoyeeById(){
        return service.deleteAllEmployee();
    }
}

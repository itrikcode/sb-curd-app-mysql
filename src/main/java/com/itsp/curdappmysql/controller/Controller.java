package com.itsp.curdappmysql.controller;

import com.itsp.curdappmysql.bean.Employee;
import com.itsp.curdappmysql.bean.EmployeeRequest;
import com.itsp.curdappmysql.bean.Login;
import com.itsp.curdappmysql.service.EmployeeService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> registryEmployee(@RequestBody EmployeeRequest employeeLogin){
        String message = service.registryValidUser(employeeLogin);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateEmpoyee(@RequestBody EmployeeRequest employeeRequest){
        return new ResponseEntity<>(service.updateEmployee(employeeRequest),HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Employee>> updateEmpoyee(){
        return new ResponseEntity<>(service.getAll(),HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Employee> updateEmpoyee(@PathVariable("id") Long id){
        return new ResponseEntity<>(service.getById(id),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteEmpoyeeById(@PathVariable("id") Long id){
        return new ResponseEntity<>(service.deleteEmployeeById(id),HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<List<Long>> deleteEmpoyeeById(){
        List<Long> deletedEmployeeIds =  service.deleteAllEmployee();
        return new ResponseEntity<>(deletedEmployeeIds,HttpStatus.OK);
    }
}

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
    @PostMapping("/login")
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
    public ResponseEntity<List<Employee>> getEmpoyee(){
        return new ResponseEntity<>(service.getAll(),HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Employee> getByEmpoyee(@PathVariable("id") Long id){
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
    
    @GetMapping("/getlogin/{id}")
    public ResponseEntity<Login> getLoginById(@PathVariable Long id){
        return new ResponseEntity<>(service.getLoginEmployeeById(id),HttpStatus.OK);
    }
    @GetMapping("/getlogin")
    public ResponseEntity<List<Login>> getAllLogin(){
        return new ResponseEntity<>(service.getAllLoginEmployee(),HttpStatus.OK);
    }
    @DeleteMapping("/deletelogin/{id}")
    public ResponseEntity<Long> deleteLoginEmployee(@PathVariable Long id){
        return new ResponseEntity<>(service.deleteLoginEmployee(id),HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<List<Long>> deleteAllLoginEmployee(){
        return new ResponseEntity<>(service.deleteAllLoginEmployee(),HttpStatus.OK);
    }
}

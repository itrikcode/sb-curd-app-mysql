package com.itsp.curdappmysql.service;

import com.itsp.curdappmysql.bean.Employee;
import com.itsp.curdappmysql.bean.EmployeeRequest;
import com.itsp.curdappmysql.bean.Login;
import com.itsp.curdappmysql.excepiton.CustomException;
import com.itsp.curdappmysql.repository.EmployeeLoginRepo;
import com.itsp.curdappmysql.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    EmployeeLoginRepo employeeLoginRepo;

    @Autowired
    EmployeeRepo employee;

    public String loginEmployee(Login login) {
        if (employeeLoginRepo.save(login) != null) {
            return "success";
        } else {
            return "falied";
        }
    }

    public String registryValidUser(EmployeeRequest employeeLogin) {
        Login existingUser = employeeLoginRepo.findByUsername(employeeLogin.getUsername());
        if (existingUser != null && verifyUser(employeeLogin.getPassword(), existingUser.getPassword())) {
            if (employeeLogin != null) {
                Employee e = employeeLogin.getEmployee();
                if (employee.save(e) != null) {
                    return "Login successful! Employee data registered.";
                }
            }
            return "Login successful! No employee data provided.";

        } else {
            return "Invalid username or password.";
        }
    }

    private boolean verifyUser(String inputPasswrod, String storedPassword) {
        return inputPasswrod.equals(storedPassword);
    }

    public String updateEmployee(@RequestBody EmployeeRequest request){
      Login exitingUser=  employeeLoginRepo.findByUsername(request.getUsername());
      if(exitingUser!=null && request.getPassword().equals(exitingUser.getPassword())){
          if(request!=null){
             Employee e = request.getEmployee();
             if(employeeRepo.updateEmployee(e.getEmpName(),e.getEmpAddress(),e.getEmpMobile(),e.getEmpAddhar(),e.getEmpId())!=0){
                 return "update successful! Employee data.";
             }
          }
          return "Login successful! No employee data provided";
      }else {
          return "Invalid username or password.";
      }
    }

    public List<Employee> getAll(){
     List<Employee> empList =   employeeRepo.findAll().stream().toList();
        if(!empList.isEmpty()){
            return empList;
        } else {
            throw  new CustomException("No Employee exist","Employee Data ", HttpStatus.NOT_FOUND);
        }
    }

    public Employee getById(Long id){
       Optional<Employee> optional = employeeRepo.findById(id);
       if(optional.isPresent()){
           if(optional.get().getEmpId().equals(id)){
               return optional.get();
           }else {
               throw  new CustomException("Employee with ID " + id + " not found","Not Found ", HttpStatus.NOT_FOUND);
           }
        }else {
           throw  new CustomException("Employee with ID " + id + " not found","Not Found ", HttpStatus.NOT_FOUND);
       }
    }

    public Long deleteEmployeeById(Long id){
        Optional<Employee> optional = employeeRepo.findById(id);
        if(optional.isPresent()){
            if(optional.get().getEmpId().equals(id)){
               employeeRepo.deleteById(optional.get().getEmpId());
               return optional.get().getEmpId();
            }else {
                throw  new CustomException("Employee with ID " + id + " not found","Not Found ", HttpStatus.NOT_FOUND);
            }
        }else {
            throw  new CustomException("Employee with ID " + id + " not found","Not Found ", HttpStatus.NOT_FOUND);
        }
    }

    public List<Long> deleteAllEmployee(){
        List<Long> deleteIds = new ArrayList<>();
      employeeRepo.findAll().forEach(employee ->{
          deleteIds.add(employee.getEmpId());
       });
       employeeRepo.deleteAll();
       return deleteIds;
    }

    public Login getLoginEmployeeById(Long id) {
       Optional<Login> login = employeeLoginRepo.findById(id);
       if(login.isPresent()){
           if(login.get().getId().equals(id)){
               return login.get();
           } else {
               throw  new CustomException("Login with ID " + id + " not found","Not Found ", HttpStatus.NOT_FOUND);
           }
       }else {
           throw  new CustomException("Login with ID " + id + " not found","Not Found ", HttpStatus.NOT_FOUND);
       }
    }
    public List<Login> getAllLoginEmployee() {
        List<Login> login = employeeLoginRepo.findAll().stream().toList();
        if(!login.isEmpty()){
                return login;
            } else {
                throw  new CustomException("No Registration exist","Login Data ", HttpStatus.NOT_FOUND);
            }
    }

    public Long deleteLoginEmployee(@PathVariable Long id) {
     Optional<Login> loginEmployee= employeeLoginRepo.findById(id);
        if(loginEmployee.isPresent()){
            if(loginEmployee.get().getId().equals(id)){
                employeeLoginRepo.deleteById(id);
                return loginEmployee.get().getId();
            }else {
                throw  new CustomException("Id Not exist",+id +" Login Data ", HttpStatus.NOT_FOUND);
            }
        } else {
            throw  new CustomException("Id Not exist",+id +" Login Data ", HttpStatus.NOT_FOUND);
        }
    }

    public List<Long> deleteAllLoginEmployee() {
        List<Long> deleteIds = new ArrayList<>();
        employeeLoginRepo.findAll().forEach(emp -> {
            deleteIds.add(emp.getId());
        });
        if(!deleteIds.isEmpty()) {
            employeeLoginRepo.deleteAll();
        }else {
            throw new CustomException("No Login exist ","Login Data",HttpStatus.NOT_FOUND);
        }
        return deleteIds;
    }
}


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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
      return   employeeRepo.findAll().stream().toList();
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


}

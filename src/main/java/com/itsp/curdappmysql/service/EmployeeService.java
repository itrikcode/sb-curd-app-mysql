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

    public String batchCreateEmployee(List<EmployeeRequest> employeeRequests) {
        StringBuilder result = new StringBuilder();
        if (employeeRequests != null && !employeeRequests.isEmpty()) {
            for (EmployeeRequest empData : employeeRequests) {
                Login existingUser = employeeLoginRepo.findByUsername(empData.getUsername());
                String addharNo = empData.getEmployee().getEmpAddhar();
                Employee existingEmployee = employeeRepo.findEmployeeByEmpAddhar(addharNo);
                if (existingEmployee != null) {
                    result.append("Employee with Addhar No ").append(addharNo).append(" Exited.\n");
                }else {
                    if (existingUser != null && verifyUser(empData.getPassword(), existingUser.getPassword())) {
                        Employee employee1 = empData.getEmployee();
                        employeeRepo.save(employee1);
                        result.append("Login successful\n").append(" Employees with Addhar ").append(addharNo).append(" Created.\n");

                    } else {
                        result.append("Invalid username or password.");
                    }
                }
            }
        } else {
            result.append("No Data Provided");
        }
        return result.toString();
    }

    public ResponseEntity<String> usertMultipleEmployee(@RequestBody List<EmployeeRequest> employeeRequest){
        StringBuilder result = new StringBuilder();
        if(!employeeRequest.isEmpty()) {
            for (EmployeeRequest emp : employeeRequest) {
                Login login = employeeLoginRepo.findByUsername(emp.getUsername());
                if (login != null && verifyUser(emp.getPassword(), login.getPassword())) {
                    //  result.append("Loing success \n");
                    String empAddharNo = emp.getEmployee().getEmpAddhar();
                    Employee existingEmployee = employeeRepo.findEmployeeByEmpAddhar(empAddharNo);
                    if (existingEmployee != null) {
                        existingEmployee.setEmpName(emp.getEmployee().getEmpName());
                        existingEmployee.setEmpAddress(emp.getEmployee().getEmpAddress());
                        existingEmployee.setEmpMobile(emp.getEmployee().getEmpMobile());
                        existingEmployee.setEmpAddhar(emp.getEmployee().getEmpAddhar());
                        employeeRepo.save(existingEmployee);
                        result.append("Login Success ").append("Employee with Addhar No ").append(empAddharNo).append(" Updated.\n");
                    } else {
                        Employee newEmployee = emp.getEmployee();
                        employeeRepo.save(newEmployee);
                        result.append("New Employee with Addhar No ").append(empAddharNo).append(" Created.\n");
                    }
                } else {
                        result.append("Invalid Username ").append(emp.getUsername()).append(" and Password ").append(emp.getPassword()).append("\n");
                }
            }
        }else {
            result.append("Please Provied Proper Data \n");
        }
        return ResponseEntity.status(HttpStatus.MULTI_STATUS).body(result.toString());
    }

    public ResponseEntity<String> deleteMultipeEmployee(List<String> addharNumbers){
        StringBuilder result = new StringBuilder();
        if(!addharNumbers.isEmpty()){
            for (String addhar : addharNumbers){
             Employee empForDeletion =   employeeRepo.findEmployeeByEmpAddhar(addhar);
             if(empForDeletion!=null) {
                 employeeRepo.delete(empForDeletion);
                 result.append("Employee with Addhar No ").append(addhar).append(" Deleted \n");
             }else {
                 result.append("Employee with Addhar No ").append(addhar).append(" Not Existed \n");
             }
            }
        }else {
            result.append("Provide Addhar No ");
        }
        return ResponseEntity.status(HttpStatus.OK).body(result.toString());
    }

}


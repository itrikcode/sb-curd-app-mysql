package com.itsp.curdappmysql.controller;

import com.itsp.curdappmysql.bean.EmployeeRequest;
import com.itsp.curdappmysql.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class BatchController {

    @Autowired
    EmployeeService employeeService;
    @PostMapping("/batch")
    public String createBatchOpt(@RequestBody List<EmployeeRequest> employeeRequestList){
        return employeeService.batchCreateEmployee(employeeRequestList);
   }

   @PostMapping("/upsert")
    public ResponseEntity<String> upsertMultipleEmployee(@RequestBody List<EmployeeRequest> employeeRequestList){
        return employeeService.usertMultipleEmployee(employeeRequestList);
   }

   @PostMapping("/all/delete")
   public ResponseEntity<String> deleteBatchEmployeeByAddhar(@RequestBody List<String> addharNumbers){
        return employeeService.deleteMultipeEmployee(addharNumbers);
   }
}

package com.itsp.curdappmysql.repository;

import com.itsp.curdappmysql.bean.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    @Modifying
    @Transactional
    @Query("update Employee e set e.empName=:empName, e.empAddress=:empAddress, e.empMobile=:empMobile, e.empAddhar=:empAddhar where e.id=:id")
    public int updateEmployee(String empName, String empAddress, String empMobile, String empAddhar, Long id);


    public Employee findEmployeeByEmpAddhar(String empAddhar);

    @Query("SELECT e FROM Employee e WHERE " +
            "(:id IS NULL OR e.id = :id) " +
            "AND (:empName IS NULL OR e.empName = :empName) " +
            "AND (:empAddress IS NULL OR e.empAddress = :empAddress) " +
            "AND (:empMobile IS NULL OR e.empMobile = :empMobile) " +
            "AND (:empAddhar IS NULL OR e.empAddhar = :empAddhar)")
    List<Employee> findEmployeeByConditions(
            Long id,
            String empName,
            String empAddress,
            String empMobile,
            String empAddhar
    );
}

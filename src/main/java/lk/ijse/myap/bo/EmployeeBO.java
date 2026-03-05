package lk.ijse.myap.bo;

import lk.ijse.myap.dto.EmployeeDTO;
import lk.ijse.myap.entity.Employee;
import lk.ijse.myap.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeBO extends SuperBO {



    boolean saveEmployee(Employee employee) throws SQLException;

    boolean updateEmployee(Employee employee) throws SQLException;

    public boolean deleteEmployee(String id) throws SQLException ;


    public Employee searchEmployee(String id) throws SQLException ;


    public List<Employee> getAllEmployees() throws SQLException ;
}

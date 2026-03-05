package lk.ijse.myap.bo;

import lk.ijse.myap.dao.custom.EmployeeDAO;
import lk.ijse.myap.dao.impl.EmployeeDAOImpl;
import lk.ijse.myap.dto.EmployeeDTO;
import lk.ijse.myap.entity.Employee;

import java.sql.SQLException;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = new EmployeeDAOImpl();



    @Override
    public boolean saveEmployee(Employee employee) throws SQLException {
        return employeeDAO.save(
                new Employee(
                        employee.getId(),
                        employee.getName(),
                        employee.getRole(),
                        employee.getSalary(),
                        employee.getTele()
                )
        );
    }

    @Override
    public boolean updateEmployee(Employee employee) throws SQLException {
        return employeeDAO.update(
                new Employee(
                        employee.getName(),
                        employee.getRole(),
                        employee.getSalary(),
                        employee.getTele(),
                        employee.getId()
                )
        );
    }



    @Override
    public boolean deleteEmployee(String id) throws SQLException {
        return employeeDAO.delete(id);
    }

    @Override
    public Employee searchEmployee(String id) throws SQLException {
        return employeeDAO.search(id);
    }

    @Override
    public List<Employee> getAllEmployees() throws SQLException {
        return employeeDAO.getAll();
    }
}

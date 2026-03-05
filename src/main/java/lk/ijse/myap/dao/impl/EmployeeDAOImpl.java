package lk.ijse.myap.dao.impl;

import lk.ijse.myap.dao.custom.EmployeeDAO;
import lk.ijse.myap.dto.EmployeeDTO;
import lk.ijse.myap.entity.Employee;
import lk.ijse.myap.util.CrudUtil;
import net.sf.jasperreports.engine.JRException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {



    public boolean save(Employee employee) throws SQLException {
        return CrudUtil.execute("INSERT INTO employee(emId, emName, role, salary, tele) VALUES (?, ?, ?, ?, ?)",
                employee.getId(),
                employee.getName(),
                employee.getRole(),
                employee.getSalary(),
                employee.getTele()
        );


    }


    public boolean update(Employee employee) throws SQLException {
        return CrudUtil.execute("UPDATE employee SET emName=?, role=?, salary=?, tele=? WHERE emId=?",
                employee.getName(),
                employee.getRole(),
                employee.getSalary(),
                employee.getTele(),
                employee.getId()
        );

    }


    public boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE FROM employee WHERE emId=?",
                id);

    }


    public Employee search(String id) throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM employee WHERE emId = ?",
                id
        );
        if (rs.next()) {
            return new Employee(
                    rs.getString("emId"),
                     rs.getString("emName"),
                     rs.getString("role"),
                     rs.getDouble("salary"),
                     rs.getString("tele")

            );

        }
        return null;

    }


    public List<Employee> getAll() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM employee ORDER BY emId DESC");

        List<Employee> employeeList = new ArrayList<>();

        while (rs.next()) {
            employeeList.add(new Employee(
                    rs.getString("emId"),
                    rs.getString("emName"),
                    rs.getString("role"),
                    rs.getDouble("salary"),
                    rs.getString("tele")
            ));

        }
        return employeeList;

    }

    @Override
    public void printReport(String id) throws SQLException, JRException {

    }

    @Override
    public void printReport() throws SQLException, JRException {

    }


}

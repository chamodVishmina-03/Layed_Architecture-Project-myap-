//package lk.ijse.myap.model;
//
//import java.sql.SQLException;
//import java.util.List;
//
//import lk.ijse.myap.dao.custom.EmployeeDAO;
//import lk.ijse.myap.dao.impl.EmployeeDAOImpl;
//import lk.ijse.myap.dto.EmployeeDTO;
//
//public class EmployeeModel {
//
//    EmployeeDAO employeeDAO = new EmployeeDAOImpl();
//
//    ////// Save a new employee/////
//
//    public boolean saveEmployee(EmployeeDTO employeeDTO) throws SQLException {
//
//        //  INSERT INTO employee(emId, emName, role, salary, tele) VALUES (?, ?, ?, ?, ?)
//
//       /* boolean result = CrudUtil.execute("INSERT INTO employee(emId, emName, role, salary, tele) VALUES (?, ?, ?, ?, ?)",
//                employeeDTO.getId(),
//                employeeDTO.getName(),
//                employeeDTO.getRole(),
//                employeeDTO.getSalary(),
//                employeeDTO.getTele()
//        );
//
//        return result;*/
//        return employeeDAO.saveEmployee(employeeDTO);
//
//    }
//
//
//
//    // Update an existing employee
//    public boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException {
//
//
//        //  UPDATE employee SET emName=?, role=?, salary=?, tele=? WHERE emId=?
//      /*  boolean result = CrudUtil.execute("UPDATE employee SET emName=?, role=?, salary=?, tele=? WHERE emId=?",
//                employeeDTO.getName(),
//                employeeDTO.getRole(),
//                employeeDTO.getSalary(),
//                employeeDTO.getTele(),
//                employeeDTO.getId()
//        );
//
//        return result;*/
//        return employeeDAO.updateEmployee(employeeDTO);
//    }
//
//
//
//
//    // Delete an employee by ID
//    public boolean deleteEmployee(String id) throws SQLException {
//
//
//
//        //  DELETE FROM employee WHERE emId=?
//       /* boolean result = CrudUtil.execute("DELETE FROM employee WHERE emId=?",
//                id
//        );
//        return result;*/
//        return employeeDAO.deleteEmployee(id);
//    }
//
//
//
//
//
//    // Search employee by ID
//    public EmployeeDTO searchEmployee(String id) throws SQLException {
//
//
//        //  SELECT * FROM employee WHERE emId = ?
//       /* ResultSet rs = CrudUtil.execute("SELECT * FROM employee WHERE emId = ?",
//                id
//        );
//
//        if (rs.next()) {
//            String empId = rs.getString("emId");
//            String empName = rs.getString("emName");
//            String empRole = rs.getString("role");
//            double empSalary = rs.getDouble("salary");
//            String empTele = rs.getString("tele");
//
//            return new EmployeeDTO(empId, empName, empRole, empSalary, empTele);
//        }
//        return null;*/
//        return employeeDAO.searchEmployee(id);
//
//
//    }
//
//
//
//    // get all employees
//    public List<EmployeeDTO> getAllEmployees() throws SQLException {
//
//
//        // SELECT * FROM employee ORDER BY emId DESC
//       /* ResultSet rs = CrudUtil.execute("SELECT * FROM employee ORDER BY emId DESC");
//
//        List<EmployeeDTO> employeeList = new ArrayList<>();
//
//        while (rs.next()) {
//            EmployeeDTO employeeDTO = new EmployeeDTO(
//                rs.getString("emId"),
//                rs.getString("emName"),
//                rs.getString("role"),
//                rs.getDouble("salary"),
//                rs.getString("tele")
//            );
//            employeeList.add(employeeDTO);
//        }
//        return employeeList;*/
//        return employeeDAO.getAllEmployees();
//    }
//}
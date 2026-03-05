package lk.ijse.myap.bo;

import lk.ijse.myap.db.DBConnection;
import lk.ijse.myap.dto.CustomerDTO;
import lk.ijse.myap.entity.Customer;
import lk.ijse.myap.util.CrudUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerBO extends SuperBO {


    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException;


    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException ;


    public boolean deleteCustomer(String id) throws SQLException;


    public Customer searchCustomer(String id) throws SQLException;


    public List<Customer> getCustomer() throws SQLException;


    public void printCustomerReport()throws SQLException, JRException;
}

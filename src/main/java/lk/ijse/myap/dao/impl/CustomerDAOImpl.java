package lk.ijse.myap.dao.impl;

import lk.ijse.myap.dao.custom.CustomerDAO;
import lk.ijse.myap.db.DBConnection;
import lk.ijse.myap.dto.CustomerDTO;
import lk.ijse.myap.entity.Category;
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

public class CustomerDAOImpl implements CustomerDAO {



    public boolean save(Customer customer) throws SQLException {
        return CrudUtil.execute(" INSERT INTO customer(name, address, tele) VALUES (?, ?, ?)",
                customer.getName() ,
                customer.getAddress() ,
                customer.getTele());

    }


    public boolean update(Customer customer) throws SQLException {
        return CrudUtil.execute("UPDATE customer SET name=?, address=?, tele=? WHERE id=?",
                customer.getId(),
                customer.getName(),
                customer.getAddress(),
                customer.getTele());

    }


    public boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE FROM customer WHERE id=?", id);

    }


    public Customer search(String id) throws SQLException {
        ResultSet rs=CrudUtil.execute("SELECT * FROM customer WHERE id = ?",id);

        if(rs.next()){
            return new Customer(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("tele")
            );
        }
        return null;

    }


    public List<Customer> getAll() throws SQLException {
        ResultSet rs=CrudUtil.execute("SELECT * FROM customer ORDER BY id DESC");

        List<Customer> customerList = new ArrayList<>();

        while (rs.next()){
            customerList.add(new Customer(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("tele")
            ));
        }
        return customerList;

    }

    @Override
    public void printReport(String id) throws SQLException, JRException {
        Connection conn= DBConnection.getInstance().getConnection();
        InputStream inputstream =getClass().getResourceAsStream("/lk/ijse/myap/reports/customer_report.jrxml");

        JasperReport jr = JasperCompileManager.compileReport(inputstream);
        JasperPrint jp = JasperFillManager.fillReport(jr,null,conn);

        JasperViewer.viewReport(jp);
    }

    @Override
    public void printReport() throws SQLException, JRException {

    }


}

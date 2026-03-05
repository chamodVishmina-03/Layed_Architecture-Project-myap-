package lk.ijse.myap.dao.impl;

import lk.ijse.myap.dao.custom.OrderDAO;
import lk.ijse.myap.db.DBConnection;
import lk.ijse.myap.dto.OrdersDTO;
import lk.ijse.myap.entity.Orders;
import lk.ijse.myap.util.CrudUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDAOImpl implements OrderDAO {



    public String getNextOrderId() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1");


        if (rs.next()) {

            String lastId = rs.getString(1);

            int nextIdNum = Integer.parseInt(lastId.substring(1)) + 1;
            return String.format("O%03d", nextIdNum);


        }

        return "O001";
    }


    public boolean save(Orders order) throws SQLException {
        return CrudUtil.execute("INSERT INTO orders(orderId, oderDate, totalPrice) VALUES (?, ?, ?)",
                order.getId(),
                order.getDate(),
                order.getTotalPrice()
        );
    }

    @Override
    public boolean update(Orders category) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public Orders search(String id) throws SQLException {
        return null;
    }

    @Override
    public List<Orders> getAll() throws SQLException {
        return List.of();
    }


    public void printReport(String orderId) throws SQLException, JRException {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("p_orderId", orderId);

        InputStream reportStream = getClass().getResourceAsStream("/lk/ijse/myap/reports/order.jrxml");

        if (reportStream == null) {
            throw new JRException("Report file not found!");
        }


        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        //  Get the connection
        Connection conn = DBConnection.getInstance().getConnection();


        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);


        if (jasperPrint.getPages().isEmpty()) {
            throw new JRException("No data found for Order ID: " + orderId);
        }

        // view report
        JasperViewer.viewReport(jasperPrint, false);
    }

    @Override
    public void printReport() throws SQLException, JRException {
        return;
    }


}

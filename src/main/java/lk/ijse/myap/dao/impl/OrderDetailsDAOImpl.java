package lk.ijse.myap.dao.impl;

import lk.ijse.myap.dao.custom.OrderDetailsDAO;
import lk.ijse.myap.dto.OrderDetailsDTO;
import lk.ijse.myap.entity.OrderDetails;
import lk.ijse.myap.util.CrudUtil;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {


    public boolean save(List<OrderDetails> detailsList) throws SQLException {
        for (OrderDetails detail : detailsList) {


            // assumes order_item table has 5 fields
            boolean result = CrudUtil.execute("INSERT INTO order_item VALUES (?, ?, ?, ?, ?)",
                    detail.getOrderId(),
                    detail.getItemId(),
                    detail.getOrderQty(),
                    detail.getUnitPrice(),
                    detail.getLineTotal()
            );
            if (!result) return false;
        }
        return true;
    }


    public boolean update(List<OrderDetails> detailsList) throws SQLException {
        for (OrderDetails detail : detailsList) {
            boolean result = CrudUtil.execute("UPDATE item SET qty = qty - ? WHERE itemId = ?",
                    detail.getOrderQty(),
                    detail.getItemId()
            );
            if (!result) return false;
        }
        return true;
    }








    @Override
    public boolean save(OrderDetails category) throws SQLException {
        return false;
    }

    @Override
    public boolean update(OrderDetails category) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public OrderDetails search(String id) throws SQLException {
        return null;
    }

    @Override
    public List<OrderDetails> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public void printReport(String id) throws SQLException, JRException {

    }

    @Override
    public void printReport() throws SQLException, JRException {

    }
}

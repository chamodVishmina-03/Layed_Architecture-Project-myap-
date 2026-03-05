package lk.ijse.myap.bo;

import lk.ijse.myap.dao.custom.OrderDAO;
import lk.ijse.myap.dao.custom.OrderDetailsDAO;
import lk.ijse.myap.dao.impl.OrderDAOImpl;
import lk.ijse.myap.dao.impl.OrderDetailsDAOImpl;
import lk.ijse.myap.dto.OrderDetailsDTO;
import lk.ijse.myap.dto.OrdersDTO;
import lk.ijse.myap.entity.OrderDetails;
import lk.ijse.myap.entity.Orders;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;
import java.util.List;

public class PlacedOrderBOImpl implements PlacedOrderBO {
    OrderDAO orderDAO = new OrderDAOImpl();
    OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAOImpl();

    @Override
    public String getNextOrderId() throws SQLException {
        return orderDAO.getNextOrderId();
    }

    @Override
    public boolean save(Orders order) throws SQLException {
        return orderDAO.save(order);
    }
    public boolean placeOrder(Orders order, List<OrderDetails> detailsList) throws SQLException {
        return orderDAO.save(order) && orderDetailsDAO.save((OrderDetails) detailsList);
    }

    @Override
    public void printOrderReport(String orderId) throws SQLException, JRException {
       orderDAO.printReport(orderId);
    }

    @Override
    public boolean saveOrderDetails(List<OrderDetails> detailsList) throws SQLException {
        return orderDetailsDAO.save((OrderDetails) detailsList);
    }

    @Override
    public boolean updateItemStock(List<OrderDetails> detailsList) throws SQLException {
        return orderDetailsDAO.update((OrderDetails) detailsList);
    }
}

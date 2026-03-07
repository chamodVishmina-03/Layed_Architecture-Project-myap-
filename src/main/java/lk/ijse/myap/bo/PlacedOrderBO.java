package lk.ijse.myap.bo;

import lk.ijse.myap.dto.OrderDetailsDTO;
import lk.ijse.myap.dto.OrdersDTO;
import lk.ijse.myap.entity.OrderDetails;
import lk.ijse.myap.entity.Orders;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;
import java.util.List;

public interface PlacedOrderBO extends SuperBO {

    public String getNextOrderId() throws SQLException;

    public boolean save(Orders order) throws SQLException ;


    public boolean saveOrderDetails(List<OrderDetails> detailsList) throws SQLException ;


    public boolean updateItemStock(List<OrderDetails> detailsList) throws SQLException ;

    public boolean placeOrder(Orders order, List<OrderDetails> detailsList) throws SQLException ;

    public void printOrderReport(String orderId) throws SQLException, JRException;


}
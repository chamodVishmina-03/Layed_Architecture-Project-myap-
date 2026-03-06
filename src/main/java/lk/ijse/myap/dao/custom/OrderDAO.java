package lk.ijse.myap.dao.custom;

import lk.ijse.myap.dao.CrudDAO;
import lk.ijse.myap.dao.SuperDAO;
import lk.ijse.myap.dto.OrdersDTO;
import lk.ijse.myap.entity.Orders;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Orders> {
   public  String getNextOrderId() throws SQLException;

}

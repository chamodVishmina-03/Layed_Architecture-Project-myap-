package lk.ijse.myap.dao.custom;

import lk.ijse.myap.dao.CrudDAO;
import lk.ijse.myap.dao.SuperDAO;
import lk.ijse.myap.db.DBConnection;
import lk.ijse.myap.dto.OrderDetailsDTO;
import lk.ijse.myap.entity.OrderDetails;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface OrderDetailsDAO extends CrudDAO<OrderDetails> {

        boolean save(List<OrderDetails> detailsList) throws SQLException;

        boolean update(List<OrderDetails> detailsList) throws SQLException;






}

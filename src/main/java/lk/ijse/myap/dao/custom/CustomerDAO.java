package lk.ijse.myap.dao.custom;

import lk.ijse.myap.dao.CrudDAO;
import lk.ijse.myap.dao.SuperDAO;
import lk.ijse.myap.db.DBConnection;
import lk.ijse.myap.dto.CustomerDTO;
import lk.ijse.myap.entity.Customer;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer>{


}

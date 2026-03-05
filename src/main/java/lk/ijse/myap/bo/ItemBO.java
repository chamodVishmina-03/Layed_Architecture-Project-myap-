package lk.ijse.myap.bo;

import lk.ijse.myap.db.DBConnection;
import lk.ijse.myap.dto.ItemDTO;
import lk.ijse.myap.entity.Item;
import lk.ijse.myap.util.CrudUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemBO extends SuperBO {

    public boolean saveItem(Item item) throws SQLException;


    public boolean updateItem(Item item) throws SQLException ;


    public boolean deleteItem(String id) throws SQLException ;


    public Item searchItem(String id) throws SQLException ;


    public Item serchItems(String name) throws SQLException ;


    public List<Item> getAllItems() throws SQLException;

    //report ek
    public void printItemReport()throws SQLException, JRException ;
}

package lk.ijse.myap.dao.impl;

import lk.ijse.myap.dao.custom.ItemDAO;
import lk.ijse.myap.db.DBConnection;
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

public class ItemDAOImpl implements ItemDAO {


    public boolean save(Item item) throws SQLException {
        return  CrudUtil.execute("INSERT INTO item(itemId, itemName, qty, unitPrice, ctId) VALUES (?, ?, ?, ?, ?)",
                item.getId(),
                item.getName(),
                item.getQty(),
                item.getUnitPrice(),
                item.getCategoryId()
        );

    }


    public boolean update(Item item) throws SQLException {
        return CrudUtil.execute("INSERT INTO item(itemId, itemName, qty, unitPrice, ctId) VALUES (?, ?, ?, ?, ?)",
                item.getId(),
                item.getName(),
                item.getQty(),
                item.getUnitPrice(),
                item.getCategoryId()
        );

    }


    public boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE FROM item WHERE itemId=?",
                id
        );

    }

      public Item search(String id) throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM item WHERE itemId = ?",
                id );
        if (rs.next()) {
            return  new Item(
                    rs.getString("itemId"),
                    rs.getString("itemName"),
                    rs.getInt("qty"),
                    rs.getDouble("unitPrice"),
                    rs.getInt("ctId")

            );
        }
        return null;

    }


    public Item serch(String name) throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM item WHERE itemName = ?",
                name);
        if (rs.next()) {
            return  new Item(
                   rs.getString("itemId"),
                    rs.getString("itemName"),
                    rs.getInt("qty"),
                    rs.getDouble("unitPrice"),
                    rs.getInt("ctId")
            );
        }
        return null;

    }


    public List<Item> getAll() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM item ORDER BY itemId DESC");

        List<Item> itemList = new ArrayList<>();
        while (rs.next()) {
            Item item = new Item(
                    rs.getString("itemId"),
                    rs.getString("itemName"),
                    rs.getInt("qty"),
                    rs.getDouble("unitPrice"),
                    rs.getInt("ctId")

            );
            itemList.add(item);
        }
        return itemList;

    }



    //report ek

    public void printReport(String id)throws SQLException,JRException{
        Connection conn= DBConnection.getInstance().getConnection();
        InputStream inputstream =getClass().getResourceAsStream("/lk/ijse/myap/reports/item_report.jrxml");

        JasperReport jr = JasperCompileManager.compileReport(inputstream);
        JasperPrint jp = JasperFillManager.fillReport(jr,null,conn);


        JasperViewer.viewReport(jp);
    }

    @Override
    public void printReport() throws SQLException, JRException {

    }

}

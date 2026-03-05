//package lk.ijse.myap.model;
//
//import java.io.InputStream;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import lk.ijse.myap.dao.custom.ItemDAO;
//import lk.ijse.myap.dao.impl.ItemDAOImpl;
//import lk.ijse.myap.db.DBConnection;
//import lk.ijse.myap.util.CrudUtil;
//import lk.ijse.myap.dto.ItemDTO;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.view.JasperViewer;
//
//
//
//public class ItemModel {
//    ItemDAO itemDAO = new ItemDAOImpl();
//
//    // Save  new Item
//    public boolean saveItem(ItemDTO itemDTO) throws SQLException {
//
//
//
//        //  INSERT INTO item(itemId, itemName, qty, unitPrice, ctId) VALUES (?, ?, ?, ?, ?)
//
//      /*  boolean result = CrudUtil.execute("INSERT INTO item(itemId, itemName, qty, unitPrice, ctId) VALUES (?, ?, ?, ?, ?)",
//                itemDTO.getId(),
//                itemDTO.getName(),
//                itemDTO.getQty(),
//                itemDTO.getUnitPrice(),
//                itemDTO.getCategoryId()
//        );
//
//
//        return result;*/
//        itemDAO.saveItem(itemDTO);
//        return true;
//    }
//
//    // update Item
//    public boolean updateItem(ItemDTO itemDTO) throws SQLException {
//
//
////
////        //UPDATE item SET itemName=?, qty=?, unitPrice=?, ctId=? WHERE itemId=?
////        boolean result = CrudUtil.execute("UPDATE item SET itemName=?, qty=?, unitPrice=?, ctId=? WHERE itemId=?",
////                itemDTO.getName(),
////                itemDTO.getQty(),
////                itemDTO.getUnitPrice(),
////                itemDTO.getCategoryId(),
////                itemDTO.getId()
////        );
////
////
////        return result;
//        itemDAO.updateItem(itemDTO);
//        return true;
//    }
//
//    // Delete  Item using by id
//    public boolean deleteItem(String id) throws SQLException {
//
//
//
//        //  DELETE FROM item WHERE itemId=?
//
////        boolean result = CrudUtil.execute("DELETE FROM item WHERE itemId=?",
////                id
////        );
////        return result;
//        itemDAO.deleteItem(id);
//        return true;
//    }
//
//    // search Item  id
//    public ItemDTO searchItem(String id) throws SQLException {
//
//
////        //  SELECT * FROM item WHERE itemId = ?
////        ResultSet rs = CrudUtil.execute("SELECT * FROM item WHERE itemId = ?",
////
////                id
////
////
////        );
////
////        if (rs.next()) {
////            String itemId = rs.getString("itemId");
////            String itemName = rs.getString("itemName");
////            int qty = rs.getInt("qty");
////            double unitPrice = rs.getDouble("unitPrice");
////            int categoryId = rs.getInt("ctId");
////
////            return new ItemDTO(itemId, itemName, qty, unitPrice, categoryId);
////        }
////
////
////        return null;
//        return itemDAO.searchItem(id);
//
//    }
//
//    public ItemDTO serchItems(String name)throws SQLException {
////        ResultSet rs = CrudUtil.execute("SELECT * FROM item WHERE itemName = ?",
////                name);
////        if (rs.next()) {
////            String itemId = rs.getString("itemId");
////            String itemName = rs.getString("itemName");
////            int qty = rs.getInt("qty");
////            double unitPrice = rs.getDouble("unitPrice");
////            int categoryId = rs.getInt("ctId");
////            return new ItemDTO(itemId, itemName, qty, unitPrice, categoryId);
////        }
////        return null;
//        return itemDAO.serchItems(name);
//
//    }
//
//    // get all Items
//
//    public List<ItemDTO> getAllItems() throws SQLException {
//
//
////        //SELECT * FROM item ORDER BY itemId DESC
////        ResultSet rs = CrudUtil.execute("SELECT * FROM item ORDER BY itemId DESC");
////
////        List<ItemDTO> itemList = new ArrayList<>();
////
////        while (rs.next()) {
////            ItemDTO itemDTO = new ItemDTO(
////                rs.getString("itemId"),
////                rs.getString("itemName"),
////                rs.getInt("qty"),
////                rs.getDouble("unitPrice"),
////                rs.getInt("ctId")
////            );
////            itemList.add(itemDTO);
////        }
////        return itemList;
//        return itemDAO.getAllItems();
//
//
//    }
//
//
//
//    public void printItemReport()throws SQLException,JRException{
//    /*   Connection conn= DBConnection.getInstance().getConnection();
//        InputStream  inputstream =getClass().getResourceAsStream("/lk/ijse/myap/reports/item_report.jrxml");
//
//        JasperReport jr =JasperCompileManager.compileReport(inputstream);
//        JasperPrint jp =JasperFillManager.fillReport(jr,null,conn);
//
//
//        JasperViewer.viewReport(jp);*/
//
//        itemDAO.printItemReport();
//
//
//    }
//}
//package lk.ijse.myap.model;
//
//import java.sql.SQLException;
//import java.util.List;
//
//import lk.ijse.myap.dao.custom.SupplierDAO;
//import lk.ijse.myap.dao.impl.SupplierDAOImpl;
//import lk.ijse.myap.dto.SupplierDTO;
//
//public class SupplierModel {
//
//   SupplierDAO supplierDAO =new SupplierDAOImpl();
//
//    // save Supplier
//    public boolean saveSupplier(SupplierDTO supplier) throws SQLException {
//
//
//        /*return CrudUtil.execute("INSERT INTO supplier(id, name, tele, ctId) VALUES (?, ?, ?, ?)",
//               supplier.getId(),
//               supplier.getName(),
//               supplier.getTele(),
//              supplier.getCtId()
//
//
//        );*/
//        return supplierDAO.saveSupplier(supplier);
//    }
//
//    // Search supplier
//    public SupplierDTO searchSupplier(int id) throws SQLException {
//
//      /*  ResultSet rs = CrudUtil.execute("SELECT * FROM supplier WHERE id = ?", id);
//
//
//        if (rs.next()) {
//
//            return new SupplierDTO(
//                rs.getInt("id"),
//                 rs.getString("name"),
//                rs.getString("tele"),
//                rs.getInt("ctId")
//
//            );
//        }
//
//        return null;*/
//        return supplierDAO.searchSupplier(id);
//    }
//
//    // update  supplier
//    public boolean updateSupplier(SupplierDTO supplier) throws SQLException {
//
//     /*
//        return CrudUtil.execute("UPDATE supplier SET name = ?, tele = ?, ctId = ? WHERE id = ?",
//            supplier.getName(),
//            supplier.getTele(),
//            supplier.getCtId(),
//            supplier.getId()
//
//
//        );*/
//        return supplierDAO.updateSupplier(supplier);
//
//    }
//
//    // delete Supplier
//    public boolean deleteSupplier(int id) throws SQLException {
//
//       // return CrudUtil.execute("DELETE FROM supplier WHERE id = ?", id);
//        return supplierDAO.deleteSupplier(id);
//    }
//
//    // get all Suppliers
//    public List<SupplierDTO> getAllSuppliers() throws SQLException {
//
//       /* List<SupplierDTO> supplierList = new ArrayList<>();
//
//           ResultSet rs = CrudUtil.execute("SELECT * FROM supplier");
//
//        while (rs.next()) {
//
//
//            supplierList.add(new SupplierDTO(
//                rs.getInt("id"),
//                 rs.getString("name"),
//                rs.getString("tele"),
//                rs.getInt("ctId")
//
//
//            ));
//        }
//        return supplierList;*/
//        return supplierDAO.getAllSuppliers();
//    }
//}
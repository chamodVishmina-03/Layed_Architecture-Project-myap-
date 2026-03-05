//
//package lk.ijse.myap.model;
//
//import java.io.InputStream;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.List;
//
//import lk.ijse.myap.dao.impl.CustomerDAOImpl;
//import lk.ijse.myap.dto.CustomerDTO;
//import lk.ijse.myap.db.DBConnection;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.view.JasperViewer;
//
//
//public class CustomerModel {
//
//    CustomerDAOImpl customerDAO = new CustomerDAOImpl();
//
//    public boolean saveCustomer(CustomerDTO customerDTO)throws SQLException{
//
//         /* Connection conn = DBConnection.getInstance().getConnection();
//            String sql = "INSERT INTO customer(name, address, tele) VALUES (?, ?, ?)";
//
//            PreparedStatement ptsm = conn.prepareStatement(sql);
//            ptsm.setString(1, customerDTO.getName());
//            ptsm.setString(2, customerDTO.getAddress());
//            ptsm.setString(3, customerDTO.getTele());
//
//            int result = ptsm.executeUpdate();
//            return result>0; */
//         /*boolean result=CrudUtil.execute("INSERT INTO customer(name, address, tele) VALUES (?, ?, ?)",
//                 customerDTO.getName() ,
//                 customerDTO.getAddress(),
//                 customerDTO.getTele()
//         );
//
//        return  result;*/
//        return customerDAO.saveCustomer(customerDTO);
//
//
//     }
//
//
//    public boolean updateCustomer(CustomerDTO customerDTO)throws SQLException{
//
//        /* Connection conn = DBConnection.getInstance().getConnection();
//
//            // *** FIXED QUERY ***
//            String sql = "UPDATE customer SET name=?, address=?, tele=? WHERE id=?";
//
//            PreparedStatement ptsm = conn.prepareStatement(sql);
//            ptsm.setString(1, customerDTO.getName());
//            ptsm.setString(2, customerDTO.getAddress());
//            ptsm.setString(3, customerDTO.getTele());
//            ptsm.setInt(4, customerDTO.getId());
//
//            int result = ptsm.executeUpdate();
//            return result>0;*/
//
//             /* boolean result=CrudUtil.execute("UPDATE customer SET name=?, address=?, tele=? WHERE id=?",
//                 customerDTO.getName() ,
//                 customerDTO.getAddress(),
//                 customerDTO.getTele(),
//                 customerDTO.getId()
//         );
//
//        return  result;*/
//        return customerDAO.updateCustomer(customerDTO);
//
//
//    }
//
//
//
//    public boolean deleteCustomer(String id)throws SQLException{
//
//        /* Connection conn = DBConnection.getInstance().getConnection();
//
//                      String sql ="DELETE FROM customer WHERE id=?";
//                      PreparedStatement ptsm= conn.prepareStatement(sql);
//                      ptsm.setInt(1, Integer.parseInt(id));
//
//                      int result =ptsm.executeUpdate();
//                      return result>0;*/
//         /*  boolean result=CrudUtil.execute("DELETE FROM customer WHERE id=?",
//                                     id   );
//
//        return  result;*/
//        return customerDAO.deleteCustomer(id);
//
//
//    }
//
//
//
//
//    public CustomerDTO searchCustomer(String id) throws SQLException {
//
//   /* Connection conn = DBConnection.getInstance().getConnection();
//    String sql = "SELECT * FROM customer WHERE id = ?";
//
//    PreparedStatement ptsm = conn.prepareStatement(sql);
//    ptsm.setInt(1, Integer.parseInt(id));
//
//    ResultSet rs = ptsm.executeQuery();
//
//*/
//    /* ResultSet rs=CrudUtil.execute("SELECT * FROM customer WHERE id = ?",
//                        id
//         );
//
//    if (rs.next()) {
//        int cusId = rs.getInt("id");          // FIXED
//        String cusName = rs.getString("name");
//        String cusAddress = rs.getString("address");
//        String cusTele = rs.getString("tele"); // FIXED
//
//        return new CustomerDTO(cusId, cusName, cusAddress, cusTele);
//    }
//    return null;*/
//        return customerDAO.searchCustomer(id);
//
//
//
//
//}
//
//
//
//
//    public List<CustomerDTO> getCustomer() throws SQLException {
//
//        /*Connection conn = DBConnection.getInstance().getConnection();
//    String sql = "SELECT * FROM customer ORDER BY id DESC";
//
//    Statement stm = conn.createStatement();
//    ResultSet rs = stm.executeQuery(sql);*/
//
//      /*    ResultSet rs=CrudUtil.execute("SELECT * FROM customer ORDER BY id DESC"
//
//         );
//
//    List<CustomerDTO> customerList = new ArrayList<>();
//
//    while (rs.next()) {
//        CustomerDTO customerDTO = new CustomerDTO(
//                rs.getInt("id"),        // FIXED
//                rs.getString("name"),
//                rs.getString("address"),
//                rs.getString("tele")
//        );
//
//        customerList.add(customerDTO);
//    }
//    return customerList;*/
//
//        return customerDAO.getCustomer();
//
//
//}
//
//
//
//   public void printCustomerReport()throws SQLException,JRException{
//       Connection conn= DBConnection.getInstance().getConnection();
//        InputStream  inputstream =getClass().getResourceAsStream("/lk/ijse/myap/reports/customer_report.jrxml");
//
//        JasperReport jr =JasperCompileManager.compileReport(inputstream);
//        JasperPrint jp =JasperFillManager.fillReport(jr,null,conn);
//
//        JasperViewer.viewReport(jp);
//
//    }
//
//}

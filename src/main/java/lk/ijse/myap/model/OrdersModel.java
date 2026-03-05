//package lk.ijse.myap.model;
//
//import java.io.InputStream;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import lk.ijse.myap.dao.custom.OrderDAO;
//import lk.ijse.myap.dao.impl.OrderDAOImpl;
//import lk.ijse.myap.dao.custom.OrderDetailsDAO;
//import lk.ijse.myap.dao.impl.OrderDetailsDAOImpl;
//import lk.ijse.myap.db.DBConnection;
//import lk.ijse.myap.dto.OrdersDTO;
//import lk.ijse.myap.dto.OrderDetailsDTO;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.view.JasperViewer;
//
//public class OrdersModel {
//    OrderDAO orderDAO = new OrderDAOImpl();
//    OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAOImpl();
//
//
//
//
//    // This is part of the transaction
//    private boolean saveOrderDetails(List<OrderDetailsDTO> detailsList) throws SQLException {
//      /*  for (OrderDetailsDTO detail : detailsList) {
//
//
//            // assumes order_item table has 5 fields
//            boolean result = CrudUtil.execute("INSERT INTO order_item VALUES (?, ?, ?, ?, ?)",
//                    detail.getOrderId(),
//                    detail.getItemId(),
//                    detail.getOrderQty(),
//                    detail.getUnitPrice(),
//                    detail.getLineTotal()
//            );
//            if (!result) return false;
//        }
//        return true;*/
//        return orderDetailsDAO.saveOrderDetails(detailsList);
//    }
//
//    // Helper method to update Item Stock
//
//    private boolean updateItemStock(List<OrderDetailsDTO> detailsList) throws SQLException {
//       /* for (OrderDetailsDTO detail : detailsList) {
//            //  UPDATE item SET qty = qty - orderQty WHERE itemId = ?
//            boolean result = CrudUtil.execute("UPDATE item SET qty = qty - ? WHERE itemId = ?",
//                    detail.getOrderQty(),
//                    detail.getItemId()
//            );
//            if (!result) return false;
//        }
//        return true;*/
//        return orderDetailsDAO.updateItemStock(detailsList);
//    }
//
//
//
//    public boolean placeOrder(OrdersDTO order, List<OrderDetailsDTO> detailsList) throws SQLException {
//
//        Connection conn = null;
//
//
//        try {
//
//
//            conn = DBConnection.getInstance().getConnection();
//            conn.setAutoCommit(false);
//
//
//
//
//            // calculate total prices
//
//            double calculatedTotalPrice = 0;
//            for (OrderDetailsDTO detail : detailsList) {
//                calculatedTotalPrice += detail.getLineTotal();
//            }
//            order.setTotalPrice(calculatedTotalPrice); // update DTO
//
//
//
//            //  Save order to orders table
//           /* boolean isOrderSaved = CrudUtil.execute("INSERT INTO orders(orderId, oderDate, totalPrice) VALUES (?, ?, ?)",
//                    order.getId(),
//                    order.getDate(),
//                    order.getTotalPrice()
//            );*/
//            boolean isOrderSaved= orderDAO.save(order);
//
//
//
//            if (isOrderSaved) {
//
//                // save order details
//
//                boolean isDetailsSaved = orderDetailsDAO.saveOrderDetails(detailsList);
//
//                if (isDetailsSaved) {
//
//                    // update Item Stock
//                    boolean isStockUpdated = orderDetailsDAO.updateItemStock(detailsList);
//
//                    if (isStockUpdated) {
//
//                        conn.commit();
//                        return true;
//                    }
//                }
//            }
//
//            conn.rollback();
//            return false;
//
//
//
//        } catch (SQLException e) {
//
//            if (conn != null) {
//                conn.rollback();
//
//            }
//
//
//            throw e;
//        } finally {
//
//
//            if (conn != null) {
//                conn.setAutoCommit(true);
//
//            }
//
//
//        }
//
//
//
//
//
//    }
//
//
//
//    //get next order
//
//    public String getNextOrderId() throws SQLException {
//
//      /*  ResultSet rs = CrudUtil.execute("SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1");
//
//
//        if (rs.next()) {
//
//            String lastId = rs.getString(1);
//
//            int nextIdNum = Integer.parseInt(lastId.substring(1)) + 1;
//            return String.format("O%03d", nextIdNum);
//
//
//        }
//
//        return "O001";*/
//        return orderDAO.getNextOrderId();
//    }
//
//
//
//
//    // print button
//    public void printOrderReport(String orderId) throws SQLException, JRException {
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("p_orderId", orderId);
//
//        InputStream reportStream = getClass().getResourceAsStream("/lk/ijse/myap/reports/order.jrxml");
//
//        if (reportStream == null) {
//            throw new JRException("Report file not found!");
//        }
//
//
//        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
//
//        //  Get the connection
//        Connection conn = DBConnection.getInstance().getConnection();
//
//
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
//
//
//        if (jasperPrint.getPages().isEmpty()) {
//            throw new JRException("No data found for Order ID: " + orderId);
//        }
//
//        // view report
//        JasperViewer.viewReport(jasperPrint, false);
//    }
//
//
//
//
//}
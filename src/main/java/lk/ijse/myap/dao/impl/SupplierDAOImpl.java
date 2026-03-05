package lk.ijse.myap.dao.impl;

import lk.ijse.myap.dao.custom.SupplierDAO;
import lk.ijse.myap.dto.SupplierDTO;
import lk.ijse.myap.entity.Supplier;
import lk.ijse.myap.util.CrudUtil;
import net.sf.jasperreports.engine.JRException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {

    public boolean save(Supplier supplier) throws SQLException {
        return  CrudUtil.execute("INSERT INTO supplier(id, name, tele, ctId) VALUES (?, ?, ?, ?)",
                supplier.getId(),
                supplier.getName(),
                supplier.getTele(),
                supplier.getCtId()


        );

    }


    public Supplier search(String id) throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM supplier WHERE id = ?", id);
        if (rs.next()) {
            return new Supplier(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("tele"),
                    rs.getInt("ctId")
            );
        }
        return null;

    }


    public boolean update(Supplier supplier) throws SQLException {
        return CrudUtil.execute("UPDATE supplier SET name = ?, tele = ?, ctId = ? WHERE id = ?",
                supplier.getName(),
                supplier.getTele(),
                supplier.getCtId(),
                supplier.getId()


        );

    }


    public boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE FROM supplier WHERE id = ?",
                id);

    }


    public List<Supplier> getAll() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM supplier");
        List<Supplier> supplierList = new ArrayList<>();
        while (rs.next()) {
            supplierList.add(new Supplier(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("tele"),
                    rs.getInt("ctId")
            ));
        }
        return supplierList;

    }

    @Override
    public void printReport(String id) throws SQLException, JRException {

    }

    @Override
    public void printReport() throws SQLException, JRException {

    }
}

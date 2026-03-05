package lk.ijse.myap.bo;

import lk.ijse.myap.dto.SupplierDTO;
import lk.ijse.myap.entity.Supplier;
import lk.ijse.myap.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplierBO extends SuperBO {

    public boolean saveSupplier(Supplier supplier) throws SQLException ;


    public Supplier searchSupplier(int id) throws SQLException ;


    public boolean updateSupplier(Supplier supplier) throws SQLException ;


    public boolean deleteSupplier(int id) throws SQLException ;


    public List<Supplier> getAllSuppliers() throws SQLException ;
}

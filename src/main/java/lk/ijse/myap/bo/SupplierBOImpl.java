package lk.ijse.myap.bo;

import lk.ijse.myap.dao.custom.SupplierDAO;
import lk.ijse.myap.dao.impl.SupplierDAOImpl;
import lk.ijse.myap.dto.SupplierDTO;
import lk.ijse.myap.entity.Supplier;

import java.sql.SQLException;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO = new SupplierDAOImpl();

    @Override
    public boolean saveSupplier(Supplier supplier) throws SQLException {
        return supplierDAO.save(
                new Supplier(
                        supplier.getId(),
                        supplier.getName(),
                        supplier.getTele(),
                        supplier.getCtId()
                )
        );
    }

    @Override
    public Supplier searchSupplier(int id) throws SQLException {
        return supplierDAO.search(String.valueOf(id));
    }

    @Override
    public boolean updateSupplier(Supplier supplier) throws SQLException {
        return supplierDAO.update(supplier);
    }

    @Override
    public boolean deleteSupplier(int id) throws SQLException {
        return supplierDAO.delete(String.valueOf(id));
    }

    @Override
    public List<Supplier> getAllSuppliers() throws SQLException {
        return supplierDAO.getAll();
    }
}

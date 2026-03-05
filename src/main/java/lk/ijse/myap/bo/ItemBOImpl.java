package lk.ijse.myap.bo;

import lk.ijse.myap.dao.custom.ItemDAO;
import lk.ijse.myap.dao.impl.ItemDAOImpl;
import lk.ijse.myap.dto.ItemDTO;
import lk.ijse.myap.entity.Item;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;
import java.util.List;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = new ItemDAOImpl();

    @Override
    public boolean saveItem(Item item) throws SQLException {
        return itemDAO.save(
                new Item(
                        item.getId(),
                        item.getName(),
                        item.getQty(),
                        item.getUnitPrice(),
                        item.getCategoryId()
                )
        );
    }

    @Override
    public boolean updateItem(Item item) throws SQLException {
        return itemDAO.update(
                new Item(
                        item.getId(),
                        item.getName(),
                        item.getQty(),
                        item.getUnitPrice(),
                        item.getCategoryId()
                )
        );
    }

    @Override
    public boolean deleteItem(String id) throws SQLException {
        return itemDAO.delete(id);
    }

    @Override
    public Item searchItem(String id) throws SQLException {
        return itemDAO.search(id);
    }

    @Override
    public Item serchItems(String name) throws SQLException {
        return itemDAO.search(name);
    }

    @Override
    public List<Item> getAllItems() throws SQLException {
        return itemDAO.getAll();
    }


    public void printItemReport( ) throws SQLException, JRException {
       itemDAO.printReport();
    }
}

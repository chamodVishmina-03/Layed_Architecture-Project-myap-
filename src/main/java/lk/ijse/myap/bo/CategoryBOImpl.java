package lk.ijse.myap.bo;

import lk.ijse.myap.dao.custom.CategoryDAO;
import lk.ijse.myap.dao.impl.CategoryDAOImpl;
import lk.ijse.myap.dto.CategoryDTO;
import lk.ijse.myap.entity.Category;

import java.sql.SQLException;
import java.util.List;

public class CategoryBOImpl implements CategoryBO {
    CategoryDAO categoryDAO = new CategoryDAOImpl();

    @Override
    public boolean save(CategoryDTO dto) throws SQLException {
       return categoryDAO.save(
               new Category(dto.getId(), dto.getName())
       );
    }

    @Override
    public boolean update(CategoryDTO dto) throws SQLException {
        return categoryDAO.update(
                new Category(dto.getId(), dto.getName())
        );
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return categoryDAO.delete(id);
    }

    @Override
    public Category search(String id) throws SQLException {
        Category category = categoryDAO.search(id);
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        return  categoryDAO.search(id);

    }

    @Override
    public List<Category> getAll() throws SQLException {
        return categoryDAO.getAll();
    }
}

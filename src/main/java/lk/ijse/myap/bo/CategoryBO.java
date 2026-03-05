package lk.ijse.myap.bo;

import lk.ijse.myap.dto.CategoryDTO;
import lk.ijse.myap.entity.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryBO extends SuperBO {



    public boolean save(CategoryDTO dto) throws SQLException;


    public boolean update(CategoryDTO dto) throws SQLException ;


    public boolean delete(String id) throws SQLException ;


    public Category search(String id) throws SQLException;


    public List<Category> getAll() throws SQLException ;
}

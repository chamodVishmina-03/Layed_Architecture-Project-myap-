package lk.ijse.myap.dao.impl;

import lk.ijse.myap.dao.custom.CategoryDAO;
import lk.ijse.myap.dto.CategoryDTO;
import lk.ijse.myap.entity.Category;
import lk.ijse.myap.util.CrudUtil;
import net.sf.jasperreports.engine.JRException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {


    public boolean save(Category category) throws SQLException {

        return CrudUtil.execute(
                "INSERT INTO category (ctname) VALUES (?)",
                category.getName()
        );
    }



    public boolean update(Category category) throws SQLException {
        return CrudUtil.execute(
                "UPDATE category SET ctname=? WHERE ctId=?",
                category.getName(),
                category.getId()
        );
    }


    public boolean delete(String id) throws SQLException {
        return CrudUtil.execute(
                "DELETE FROM category WHERE ctId=?",
                id
        );
    }


    public Category search(String id) throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM category WHERE ctId = ?", id);

        if (rs.next()) {
            return new Category(
                    rs.getInt("ctId"),
                    rs.getString("ctname")
            );
        }
        return null;
    }


    public List<Category> getAll() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM category ORDER BY ctId DESC");
        List<Category> categoryList = new ArrayList<>();

        while (rs.next()) {
            categoryList.add(new Category(
                    rs.getInt("ctId"),
                    rs.getString("ctname")
            ));
        }
        return categoryList;
    }

    @Override
    public void printReport(String id) throws SQLException, JRException {

    }

    @Override
    public void printReport() throws SQLException, JRException {

    }


}
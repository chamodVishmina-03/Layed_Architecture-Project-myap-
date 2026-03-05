//package lk.ijse.myap.model;
//
//import java.sql.SQLException;
//import java.util.List;
//
//import lk.ijse.myap.dao.custom.CategoryDAO;
//import lk.ijse.myap.dao.impl.CategoryDAOImpl;
//import lk.ijse.myap.dto.CategoryDTO;
//
//
//public class CategoryModel {
//    CategoryDAO categoryDAO = new CategoryDAOImpl();
//
//    // Save a new Category
//    public boolean saveCategory(CategoryDTO categoryDTO) throws SQLException {
//
//        /*boolean result = CrudUtil.execute("INSERT INTO category (ctname) VALUES (?)",
//                categoryDTO.getName()
//        );
//        return result;*/
//        return categoryDAO.save(categoryDTO);
//    }
//
//    // Update an existing Category
//    public boolean updateCategory(CategoryDTO categoryDTO) throws SQLException {
//        // Query: UPDATE category SET ctname=? WHERE ctId=?
//        // Note: CrudUtil must accept Integer or String for the ID parameter
//       /* boolean result = CrudUtil.execute("UPDATE category SET ctname=? WHERE ctId=?",
//                categoryDTO.getName(),
//                categoryDTO.getId()
//        );
//        return result;*/
//        return categoryDAO.update(categoryDTO);
//    }
//
//
//
//
//// Delete a category by ID
//
//
//    public boolean deleteCategory(String id) throws SQLException {
//
//        // DELETE FROM category WHERE ctId=?
//        /*boolean result = CrudUtil.execute("DELETE FROM category WHERE ctId=?",
//                id
//        );
//        return result;*/
//        return categoryDAO.delete(id);
//    }
//
//
//
//    // Search category by ID
//    public CategoryDTO searchCategory(String id) throws SQLException {
//
//        //  SELECT * FROM category WHERE ctId = ?
//       /* ResultSet rs = CrudUtil.execute("SELECT * FROM category WHERE ctId = ?",
//                id
//        );
//
//
//
//        if (rs.next()) {
//            int catId = rs.getInt("ctId");
//            String catName = rs.getString("ctname");
//
//            return new CategoryDTO(catId, catName);
//        }
//        return null;*/
//        return categoryDAO.search(id);
//    }
//
//
//
//
//    // get all categories
//
//    public List<CategoryDTO> getAllCategories() throws SQLException {
//
//        //: SELECT * FROM category ORDER BY ctId DESC;
//
//        /*ResultSet rs = CrudUtil.execute("SELECT * FROM category ORDER BY ctId DESC");
//
//        List<CategoryDTO> categoryList = new ArrayList<>();
//
//        while (rs.next()) {
//            CategoryDTO categoryDTO = new CategoryDTO(
//                    rs.getInt("ctId"),
//                    rs.getString("ctname")
//            );
//            categoryList.add(categoryDTO);
//        }
//        return categoryList;*/
//        return categoryDAO.getAll();
//    }
//}
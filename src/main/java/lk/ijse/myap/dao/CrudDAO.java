package lk.ijse.myap.dao;

import lk.ijse.myap.dto.CategoryDTO;
import lk.ijse.myap.entity.Category;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO {

    public boolean save(T category ) throws SQLException;


    public boolean update(T category) throws SQLException;


    public boolean delete(String id) throws SQLException ;


    public T search(String id) throws SQLException ;


    public List<T> getAll() throws SQLException;

    public void printReport(String id) throws SQLException, JRException ;
    
    public void printReport() throws SQLException, JRException ;


}
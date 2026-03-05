package lk.ijse.myap.dao;

import lk.ijse.myap.dao.*;
import lk.ijse.myap.dao.custom.CategoryDAO;
import lk.ijse.myap.dao.impl.*;

import java.sql.SQLException;

public class DAOFactory {

    private static DAOFactory instance;

    private DAOFactory(){}


    public static DAOFactory getInstance() throws SQLException, ClassNotFoundException {
        if(instance == null){
            instance = new DAOFactory();
        }
        return instance;
    }

    public enum daoType {
        CATEGORYDAO,
        CUSTOMERDAO,
        EMPLOYEEDAO,
        ITEMDAO,
        ORDERDAO,
        ORDERDETAILSDAO,
        SUPPLIERDAO

    }

    public SuperDAO getDAO(daoType type)throws SQLException, ClassNotFoundException {

        switch(type){
            case CATEGORYDAO:
                return new CategoryDAOImpl();
            case CUSTOMERDAO:
                return new CustomerDAOImpl();
            case  EMPLOYEEDAO:
                return new EmployeeDAOImpl();
            case ITEMDAO:
                return new ItemDAOImpl();
            case ORDERDAO:
                return  new OrderDAOImpl();
            case ORDERDETAILSDAO:
                return new OrderDAOImpl();
            case SUPPLIERDAO:
                return new SupplierDAOImpl();
            default:
                return null;
        }
    }
}

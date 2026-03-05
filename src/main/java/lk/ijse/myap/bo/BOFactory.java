package lk.ijse.myap.bo;

import java.sql.SQLException;



public class BOFactory {
    private static BOFactory instance;
    private BOFactory() {}

    public static BOFactory getInstance() {
        if (instance == null) {
            instance = new BOFactory();
        }
        return instance;
    }
    public enum BOtype{
        CATEGORYBO,
        CUSTOMERBO,
        EMPLOYEEBO,
        ITEMBO,
        PLACEDORDERBO,
        SUPPLIERBO,
    }
    public SuperBO getBO(BOtype botype) throws SQLException ,ClassNotFoundException{
        switch (botype) {
            case CATEGORYBO:
                return new CategoryBOImpl();
            case CUSTOMERBO:
                 return new CustomerBOImpl();
            case EMPLOYEEBO:
                return new EmployeeBOImpl();
            case ITEMBO:
                return new ItemBOImpl();
           case PLACEDORDERBO:
               return new PlacedOrderBOImpl();
            case  SUPPLIERBO:
                return new SupplierBOImpl();
            default:
                return null;


        }
    }
}

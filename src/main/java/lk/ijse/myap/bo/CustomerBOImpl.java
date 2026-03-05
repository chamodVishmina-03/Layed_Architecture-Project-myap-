package lk.ijse.myap.bo;

import lk.ijse.myap.dao.custom.CustomerDAO;
import lk.ijse.myap.dao.impl.CustomerDAOImpl;
import lk.ijse.myap.dto.CustomerDTO;
import lk.ijse.myap.entity.Customer;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = new CustomerDAOImpl();

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException {
        return customerDAO.save(
                new Customer(
                        customerDTO.getName(),customerDTO.getAddress(),customerDTO.getTele()
                )
        );
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException {
        return customerDAO.update(
                new Customer(
                        customerDTO.getName(),customerDTO.getAddress(),customerDTO.getTele()
                )
        );
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException {
        return customerDAO.delete(id);
    }

    @Override
    public Customer searchCustomer(String id) throws SQLException {

       Customer customer = customerDAO.search(id);
       CustomerDTO dto = new CustomerDTO();
       dto.setId(customer.getId());
     return  customerDAO.search(id);


    }

    @Override
    public List<Customer> getCustomer() throws SQLException {
        return customerDAO.getAll();
    }

    @Override
    public void printCustomerReport() throws SQLException, JRException {

    }


    public void printCustomerReport(String id) throws SQLException, JRException {
        customerDAO.printReport(id);
    }


}

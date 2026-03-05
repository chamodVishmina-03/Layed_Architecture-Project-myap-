package lk.ijse.myap.entity;

import lk.ijse.myap.dto.CustomerDTO;

public class Customer extends CustomerDTO {
    private int id;
    private String name;
    private String address;
    private String tele;
    public Customer(int id, String name, String address, String tele) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.tele = tele;
    }
    public Customer() {}

    public Customer(String name, String address, String tele) {
        this.name = name;
        this.address = address;
        this.tele = tele;
    }

//    public Customer(String name, String address, String tele) {
//        this.name = name;
//        this.address = address;
//        this.tele = tele;
//    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
     this.name = name;
    }
    public String getAddress() {
         return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getTele() {
        return tele;
    }
    public void setTele(String tele) {

        this.tele = tele;
    }


}

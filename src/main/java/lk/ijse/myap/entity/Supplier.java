package lk.ijse.myap.entity;

public class Supplier {
    private int id;
    private String name;
    private String tele;
    private int ctId;

    public Supplier(int id, String name, String tele, int ctId) {

        this.id = id;
        this.name = name;
        this.tele = tele;
        this.ctId = ctId;
    }
    public Supplier() {
    }
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
    public String getTele() {
        return tele;
    }
    public void setTele(String tele) {
        this.tele = tele;
    }
    public int getCtId() {
        return ctId;
    }
    public void setCtId(int ctId) {
        this.ctId = ctId;
    }

}

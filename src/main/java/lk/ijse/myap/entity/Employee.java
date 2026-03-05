package lk.ijse.myap.entity;

public class Employee {
    private String id;
    private String name;
    private String role;
    private double salary;
    private String tele;
    public Employee() {
    }
    public Employee(String id, String name, String role, double salary, String tele) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.salary = salary;
        this.tele = tele;
    }

    public Employee(String name, String role, double salary, String tele, String id) {
        this.name = name;
        this.role = role;
        this.salary = salary;
        this.tele = tele;
        this.id = id;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public String getTele() {
        return tele;
    }
    public void setTele(String tele) {
        this.tele = tele;
    }

}

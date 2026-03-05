package lk.ijse.myap.dto;

public class EmployeeDTO {

    private String id;
    private String name; 
    private String role;
    private double salary; 
    private String tele;

    // No arg Constructor
    public EmployeeDTO() {
    }

    
    
    //  full arg constructor 
    public EmployeeDTO(String id, String name, String role, double salary, String tele) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.salary = salary;
        this.tele = tele;
    }

    
    
    // Getters and Setters methods====

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
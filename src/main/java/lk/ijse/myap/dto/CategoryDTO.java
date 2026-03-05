package lk.ijse.myap.dto;

public class CategoryDTO {

    private int id;
    private String name;

          //No arg constructor
    public CategoryDTO() {
    }

   
    public CategoryDTO(String name) {
        this.name = name;
    }

    // Constructor  id ,name
    public CategoryDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    
    
    // Getters and Setters methods ====

    
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

    @Override
    public String toString() {
        return "CategoryDTO {"+ "id=" + id + ", name=" + name + '}';
    }
}
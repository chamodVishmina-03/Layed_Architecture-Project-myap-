
package lk.ijse.myap.dto;

public class CustomerDTO {
    
    
    private int id;
    private String name;
    private String address;
     private String tele;

     // default constructor
    public CustomerDTO() {
    }
    
    
    
     public CustomerDTO( String name, String address, String tele) {
        this.name = name;
        this.address = address;
        this.tele = tele;
    }

     // full arg constructor
    public CustomerDTO(int id, String name, String address, String tele) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.tele = tele;
    }
     
    
    
    
    // getter setter methods========
    
     public int getId(){
         return id;
     }
     public void setId(int id){
         this.id= this.id;
     }
      public String  getName(){
         return name;
     }
     public void setName(){
         this.name=name;
     }
      public String getAddress(){
         return address;
     }
     public void setAddress(){
         this.address=address;
     }
      public String  getTele(){
         return tele;
     }
     public void setTele(){
         this.tele=tele;
     }
    
     
     
     
     
}

package tk.hausplant.model;

/**
 * User Model
 * 
 * @author mateusht
 */
public class User {
    
    private int id;
    
    private String name;

    public User(final int id, final String name) {
        this.id = id;
        this.name = name;
    }
    
    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

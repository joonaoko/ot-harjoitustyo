package domain;

/**
 * Class for handling tags' info.
 */
public class Tag {
    private int id;
    private String name;
    /*
    public Tag(int id, String name) {
        this.id = id;
        this.name = name;
    }
    */
    public Tag(String name) {
        this.name = name;
    }
    
    public Tag(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    
    public int getId() {
        return this.id;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}

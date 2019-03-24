package gallerywtags;

public class Tag {
    private int id;
    private String name;
    
    public Tag(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String toString() {
        return this.name;
    }
}
package gallerywtags;

import java.util.HashMap;

public class Image {
    private int id;
    private HashMap<Integer, Tag> tags;
    
    public Image(int id) {
        this.id = id;
        this.tags = new HashMap<>();
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void addTag(Tag tag) {
        tags.put(tag.getId(), tag);
    }
    
    public void removeTag(Tag tag) {
        tags.remove(tag.getId());
    }
    
    public String toString() {
        return id+"\n"+tags.values();
    }
}

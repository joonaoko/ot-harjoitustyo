package domain;

import java.util.*;

public class Img {
    private int id;
    private String title;
    private String path;
    private List<Tag> tags;
    /*
    public Image(int id) {
        this.id = id;
        this.tags = new ArrayList<>();
    }
    */
    public Img(String title) {
        this.title = title;
        this.path = "src/main/resources/images/notfound.jpg";
        this.tags = new ArrayList<>();
    }
    
    public Img(String title, String path) {
        this.title = title;
        this.path = path;
        this.tags = new ArrayList<>();
    }
    
    public Img(int id, String title, String path) {
        this.id = id;
        this.title = title;
        this.path = path;
        this.tags = new ArrayList<>();
    }
    
    public Img(int id, String title, String path, List tags) {
        this. id = id;
        this.title = title;
        this.path = path;
        this.tags = tags;
    }
    /*
    public void setId(int id) {
        this.id = id;
    }
    
    public void addTag(Tag tag) {
        tags.add(tag);
    }
    
    public void removeTag(int id) {
        tags.remove(id);
    }
    */
    public int getId() {
        return this.id;
    }
    
    public String getPath() {
        return path;
    }
    
    public String getTagsString() {
        String tagsString = "";
        
        for (Tag t : tags) {
            tagsString += t + ", ";
        }
        
        return tagsString;
    }
    
    public List getTagsList() {
        return tags;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    @Override
    public String toString() {
        return title + "\n" + tags;
    }
}

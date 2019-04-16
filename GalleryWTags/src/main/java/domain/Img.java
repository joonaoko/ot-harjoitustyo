package domain;

import java.util.ArrayList;

public class Img {
    private int id;
    private String title;
    private String path;
    private ArrayList<Tag> tags;
    /*
    public Image(int id) {
        this.id = id;
        this.tags = new ArrayList<>();
    }
    */
    public Img(String title) {
        this.title = title;
        this.path = "images/notfound.jpg";
        this.tags = new ArrayList<>();
    }
    
    public Img(String title, String path) {
        this.title = title;
        this.path = path;
        this.tags = new ArrayList<>();
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void addTag(Tag tag) {
        tags.add(tag);
    }
    
    public void removeTag(int id) {
        tags.remove(id);
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getPath() {
        return path;
    }
    
    public String getTags() {
        String tagsString = "";
        
        for (Tag t : tags) {
            tagsString += t+", ";
        }
        
        return tagsString;
    }
    
    public ArrayList getTagsList() {
        return tags;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    @Override
    public String toString() {
        return title+"\n"+tags;
    }
}

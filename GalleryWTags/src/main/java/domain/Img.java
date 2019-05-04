package domain;

import java.util.*;

/**
 * Class for handling images' info and tags.
 */
public class Img {
    private int id;
    private String title;
    private String path;
    private List<Tag> tags;
    
    public Img(String title, String path) {
        this.title = title;
        this.path = path;
        this.tags = new ArrayList<>();
    }
    
    public Img(int id, String title, String path, List tags) {
        this.id = id;
        this.title = title;
        this.path = path;
        this.tags = tags;
    }
    
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
    
    public String getTitle() {
        return this.title;
    }
}

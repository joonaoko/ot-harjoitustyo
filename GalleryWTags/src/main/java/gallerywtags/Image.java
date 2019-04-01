package gallerywtags;

import java.util.ArrayList;

public class Image {
    private int id;
    private String title;
    private ArrayList<Tag> tags;
    
    public Image(int id) {
        this.id = id;
        this.tags = new ArrayList<>();
    }
    
    public Image(String title) {
        this.title = title;
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
    
    public String getTags() {
        String tagsString = "";
        int i = 0;
        
        for (Tag t : tags) {
            tagsString += i+": "+t+", ";
            i++;
        }
        
        return tagsString;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    @Override
    public String toString() {
        return title+"\n"+tags;
    }
}

package domain;

import java.util.ArrayList;

public class ImgService {
    private ArrayList<Img> images;
    
    public ImgService() {
        images = new ArrayList<>();
        images.add(new Img("Test", "images/testimg.jpg"));
        images.add(new Img("Test2"));
        images.get(0).addTag(new Tag("Test Tag"));
        images.get(0).addTag(new Tag("Test Tag 2"));
    }
    
    public Img getImage(int id) {
        return images.get(id);
    }
    
    public ArrayList getImages() {
        return images;
    }
    
    public void addImage(String title) {
        images.add(new Img(title));
    }
}

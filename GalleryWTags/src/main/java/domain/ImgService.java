package domain;

import dao.Database;
import dao.ImgDao;
import java.sql.SQLException;
import java.util.*;

public class ImgService {
    // private ArrayList<Img> images;
    private ImgDao imgDao;
    
    public ImgService() throws Exception {  
        Database db = new Database("jdbc:sqlite:src/main/resources/database.db");
        db.init();
        imgDao = new ImgDao(db);
        
        /*
        images = new ArrayList<>();
        images.add(new Img("Test", "src/main/resources/images/testimg.jpg"));
        images.add(new Img("Test2"));
        images.get(0).addTag(new Tag("Test Tag"));
        images.get(0).addTag(new Tag("Test Tag 2"));
        */
    }
    
    public Img getImage(int id) throws Exception {
        // return images.get(id);
        return imgDao.findOne(id);
    }
    
    public List<Img> getImages() throws Exception {
        // return images;
        return imgDao.findAll();
    }
    
    public List<Img> getTagImages(int tag_id) throws Exception {
        return imgDao.findTagImages(tag_id);
    }
    /*
    public void addImage(String title) {
        images.add(new Img(title));
    }
    */
    public void addImage(String title, String path) throws Exception {
        imgDao.save(new Img(title, path));
    }
    
    public void removeImage(int id) throws Exception {
        imgDao.delete(id);
    }
    
    public void addImageTag(int id, String tagName) throws Exception {
        imgDao.addImageTag(id, tagName);
    }
    
    public List<Tag> getImageTags(int id) throws Exception {
        return imgDao.findImageTags(id);
    }
    /*
    public String getImageTagsString(int id) throws Exception {
        List<Tag> tags = imgDao.findImageTags(id);
        
        String tagsString = "";
        
        for (Tag t : tags) {
            tagsString += t + ", ";
        }
        
        return tagsString;
    }
    */
    public void removeImageTag(int img_id, int tag_id) throws SQLException {
        imgDao.removeImageTag(img_id, tag_id);
    }
}

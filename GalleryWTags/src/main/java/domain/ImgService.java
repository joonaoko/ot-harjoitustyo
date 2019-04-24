package domain;

import dao.Database;
import dao.ImgDao;
import java.sql.SQLException;
import java.util.*;

/**
 * Class for accessing images and their tags in the database via ImgDao, and 
 * passing them to UI.
 */
public class ImgService {
    // private ArrayList<Img> images;
    private ImgDao imgDao;
    
    public ImgService() throws Exception {  
        Database db = new Database("jdbc:sqlite::resource:database.db");
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
    /**
     * Finds all images that have the specified tag in the database
     * and returns them as a list.
     * @param tag_id ID of the tag
     * @return List of all images with the specified tag
     * @throws Exception Exception
     */
    public List<Img> getTagImages(int tag_id) throws Exception {
        return imgDao.findTagImages(tag_id);
    }
    /*
    public void addImage(String title) {
        images.add(new Img(title));
    }
    */
    /**
     * Adds a new image to the database.
     * @param title Title of the image being added
     * @param path File path of the image being added
     * @throws Exception Exception
     */
    public void addImage(String title, String path) throws Exception {
        imgDao.save(new Img(title, path));
    }
    
    /**
     * Removes specified image from the database.
     * @param id ID of the image being removed
     * @throws Exception Exception
     */
    public void removeImage(int id) throws Exception {
        imgDao.delete(id);
    }
    
    /**
     * Adds a new tag for an image in the database.
     * @param id ID of the image being modified
     * @param tagName Name of the tag being added
     * @throws Exception Exception
     */
    public void addImageTag(int id, String tagName) throws Exception {
        imgDao.addImageTag(id, tagName);
    }
    
    /**
     * Finds all tags of a specified image from the database and returns them as 
     * a list.
     * @param id ID of the image
     * @return List of the image's tags
     * @throws Exception Exception
     */
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
    /**
     * Removes an image's tag from the database.
     * @param img_id ID of the image being modified
     * @param tag_id ID of the tag being removed
     * @throws SQLException SQLException
     */
    public void removeImageTag(int img_id, int tag_id) throws SQLException {
        imgDao.removeImageTag(img_id, tag_id);
    }
}

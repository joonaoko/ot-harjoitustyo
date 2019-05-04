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
    private ImgDao imgDao;
    
    public ImgService(Database db) throws Exception {
        imgDao = new ImgDao(db);
    }
    
    public Img getImage(int id) throws Exception {
        return imgDao.findOne(id);
    }
    
    public List<Img> getImages() throws Exception {
        return imgDao.findAll();
    }
    /**
     * Finds all images that have the specified tag in the database
     * and returns them as a list.
     * @param tagId ID of the tag
     * @return List of all images with the specified tag
     * @throws Exception Exception
     */
    public List<Img> getTagImages(int tagId) throws Exception {
        return imgDao.findTagImages(tagId);
    }
    
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
    
    /**
     * Removes an image's tag from the database.
     * @param imgId ID of the image being modified
     * @param tagId ID of the tag being removed
     * @throws SQLException SQLException
     */
    public void removeImageTag(int imgId, int tagId) throws SQLException {
        imgDao.removeImageTag(imgId, tagId);
    }
}

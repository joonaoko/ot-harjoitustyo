package dao;

import dao.Database;
import domain.Img;
import domain.Tag;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for finding, updating and deleting images and their tags in the database.
 */
public class ImgDao implements Dao<Img, Integer> {
    private Database db;
    private TagDao tagDao;
    
    public ImgDao(Database db) {
        this.db = db;
        tagDao = new TagDao(db);
    }
    
    /**
     * Adds named tag to Tag table if it doesn't exist yet, and connects it to 
     * specified image via ImageTag.
     * @param key ID of the image
     * @param tagName Name of the tag being added
     * @throws SQLException SQLException
     */
    public void addImageTag(Integer key, String tagName) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT id FROM Tag WHERE name = ?");
        stmt.setObject(1, tagName);
        
        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        // Lisää tagin Tag-tauluun, jos sitä ei löydy
        if (!hasOne) {
            tagDao.save(new Tag(tagName));
            addImageTag(key, tagName);
        } else {
            Integer tagId = rs.getInt("id");

            stmt = conn.prepareStatement("INSERT INTO ImageTag (image_id, tag_id) VALUES (?, ?)");
            stmt.setInt(1, key);
            stmt.setInt(2, tagId);
            stmt.executeUpdate();
        }
        rs.close();
        stmt.close();
        conn.close();
    }
    
    /**
     * Finds all tags that are connected to the specified image via ImageTag and 
     * returns them as a list.
     * @param key ID of the image
     * @return List of the image's tags
     * @throws SQLException SQLException
     */
    public List<Tag> findImageTags(Integer key) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ImageTag WHERE image_id = ?");
        stmt.setObject(1, key);
        
        ResultSet rs = stmt.executeQuery();
        List<Tag> tags = new ArrayList<>();
        while (rs.next()) {
            Integer tagId = rs.getInt("tag_id");
            if (tagDao.findOne(tagId) != null) {
                tags.add(tagDao.findOne(tagId));
            }
        }
        
        rs.close();
        stmt.close();
        conn.close();
        
        return tags;
    }
    
    /**
     * Removes connection between specified image and tag from ImageTag.
     * @param imgId ID of the image
     * @param tagId ID of the tag
     * @throws SQLException SQLException
     */
    public void removeImageTag(Integer imgId, Integer tagId) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "DELETE FROM ImageTag "
                + "WHERE image_id =  ? AND tag_id = ?");
        stmt.setInt(1, imgId);
        stmt.setInt(2, tagId);
        
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }
    
    /**
     * Finds all images connected to specified tag via ImageTag and returns them 
     * as a list.
     * @param tagId ID of the tag
     * @return List of images with the specified tag
     * @throws SQLException SQLException
     */
    public List<Img> findTagImages(Integer tagId) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Image "
                + "INNER JOIN ImageTag ON Image.id = ImageTag.image_id "
                + "INNER JOIN Tag ON ImageTag.tag_id = Tag.id WHERE Tag.id = ?");
        stmt.setInt(1, tagId);
        
        ResultSet rs = stmt.executeQuery();
        List<Img> images = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String title = rs.getString("title");
            String path = rs.getString("path");
            List tags = findImageTags(id);
            
            images.add(new Img(id, title, path, tags));
        }
        
        rs.close();
        stmt.close();
        conn.close();
        
        return images;
    }
    
    /**
     * Finds specified image from the Image table and returns it.
     * @param key ID of the image
     * @return The image
     * @throws SQLException SQLException
     */
    @Override
    public Img findOne(Integer key) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Image WHERE id = ?");
        stmt.setInt(1, key);
        
        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }
        
        Integer id = rs.getInt("id");
        String title = rs.getString("title");
        String path = rs.getString("path");
        List tags = findImageTags(id);
        
        Img img = new Img(id, title, path, tags);
        
        rs.close();
        stmt.close();
        conn.close();
        
        return img;
    }
    
    /**
     * Finds all images from the Image table and returns them as a list.
     * @return List of all images in the database
     * @throws SQLException SQLException
     */
    @Override
    public List<Img> findAll() throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Image");
        
        ResultSet rs = stmt.executeQuery();
        List<Img> images = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String title = rs.getString("title");
            String path = rs.getString("path");
            List tags = findImageTags(id);
            
            images.add(new Img(id, title, path, tags));
        }
        
        rs.close();
        stmt.close();
        conn.close();
        
        return images;
    }
    
    /**
     * Saves a new image to the Image table.
     * @param img The image being saved
     * @throws SQLException SQLException
     */
    @Override
    public void save(Img img) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO Image"
                + "(title, path)"
                + "VALUES (?, ?)");
        
        stmt.setString(1, img.getTitle());
        stmt.setString(2, img.getPath());
        stmt.executeUpdate();
        
        stmt.close();
        conn.close();
    }
    
    /**
     * Removes specified image from the Image table.
     * @param key ID of the image being removed
     * @throws SQLException SQLException
     */
    @Override
    public void delete(Integer key) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Image WHERE id = ?");
        stmt.setInt(1, key);
        stmt.executeUpdate();
        
        stmt.close();
        conn.close();
    }
}

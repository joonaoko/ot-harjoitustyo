package dao;

import dao.Database;
import domain.Img;
import domain.Tag;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ImgDao implements Dao<Img, Integer> {
    private Database db;
    private TagDao tagDao;
    
    public ImgDao(Database db) {
        this.db = db;
        tagDao = new TagDao(db);
    }
    
    public void addImageTag(Integer key, String tagName) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT id FROM Tag WHERE name = ?");
        stmt.setObject(1, tagName);
        
        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        /* Lisää tagin Tag-tauluun, jos sitä ei löydy */
        if (!hasOne) {
            tagDao.save(new Tag(tagName));
            
            rs.close();
            stmt.close();
            conn.close();
            
            addImageTag(key, tagName);
        } else {
            Integer tag_id = rs.getInt("id");

            stmt = conn.prepareStatement("INSERT INTO ImageTag"
                    + "(image_id, tag_id)"
                    + "VALUES (?, ?)");
            stmt.setInt(1, key);
            stmt.setInt(2, tag_id);
            stmt.executeUpdate();
            
            rs.close();
            stmt.close();
            conn.close();
        }
    }
    
    public List<Tag> findImageTags(Integer key) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ImageTag WHERE image_id = ?");
        stmt.setObject(1, key);
        
        ResultSet rs = stmt.executeQuery();
        List<Tag> tags = new ArrayList<>();
        while(rs.next()) {
            Integer tag_id = rs.getInt("tag_id");
            if (tagDao.findOne(tag_id) != null) {
                tags.add(tagDao.findOne(tag_id));
            }
        }
        
        rs.close();
        stmt.close();
        conn.close();
        
        return tags;
    }
    
    public void removeImageTag(Integer img_id, Integer tag_id) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "DELETE FROM ImageTag "
                + "WHERE image_id =  ? AND tag_id = ?");
        stmt.setInt(1, img_id);
        stmt.setInt(2, tag_id);
        
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }
    
    public List<Img> findTagImages(Integer tag_id) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM Image "
                + "INNER JOIN ImageTag ON Image.id = ImageTag.image_id "
                + "INNER JOIN Tag ON ImageTag.tag_id = Tag.id "
                + "WHERE Tag.id = ?");
        stmt.setInt(1, tag_id);
        
        ResultSet rs = stmt.executeQuery();
        List<Img> images = new ArrayList<>();
        while(rs.next()) {
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

    @Override
    public List<Img> findAll() throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Image");
        
        ResultSet rs = stmt.executeQuery();
        List<Img> images = new ArrayList<>();
        while(rs.next()) {
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

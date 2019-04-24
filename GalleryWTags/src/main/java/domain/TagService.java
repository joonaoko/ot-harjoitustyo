package domain;

import dao.Database;
import dao.TagDao;
import java.sql.SQLException;
import java.util.*;

/**
 * Class for accessing tags in the database via TagDao, and passing them to UI.
 */
public class TagService {
    private TagDao tagDao;
    
    public TagService() throws Exception {
        Database db = new Database("jdbc:sqlite::resource:database.db");
        db.init();
        tagDao = new TagDao(db);
    }
    
    public List<Tag> getAllTags() throws Exception {
        return tagDao.findAll();
    }
}

package domain;

import dao.Database;
import dao.TagDao;
import java.sql.SQLException;
import java.util.*;

public class TagService {
    private TagDao tagDao;
    
    public TagService() throws Exception {
        Database db = new Database("jdbc:sqlite:src/main/resources/database.db");
        db.init();
        tagDao = new TagDao(db);
    }
    
    public List getAllTags() throws Exception {
        return tagDao.findAll();
    }
}

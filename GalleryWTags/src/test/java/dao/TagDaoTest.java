package dao;

import domain.Tag;
import java.io.File;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

public class TagDaoTest {
    private TagDao tagDao;
    
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();
    
    @Before
    public void setUp() throws Exception {
        File testdbfile = tempFolder.newFile("testdatabase.db");
        
        Database db = new Database("jdbc:sqlite:"+testdbfile.getPath());
        db.init();
        tagDao = new TagDao(db);
        
        tagDao.save(new Tag("Testtag1"));
        tagDao.save(new Tag("Testtag2"));
    }
    
    @After
    public void tearDown() throws Exception {
        for (Tag t: tagDao.findAll()) {
            tagDao.delete(t.getId());
        }
    }
    
    @Test
    public void findOneReturnsTag() throws Exception {
        assertEquals(1, tagDao.findOne(1).getId());
    }
    
    @Test
    public void findAllReturnsTags() throws Exception {
        List<Tag> tags = tagDao.findAll();
        assertEquals(1, tags.get(0).getId());
        assertEquals(2, tags.get(1).getId());
    }
    
    @Test
    public void saveAddsTag() throws Exception {
        tagDao.save(new Tag("Testtag3"));
        assertEquals(3, tagDao.findOne(3).getId());
    }
    
    @Test
    public void removeDeletesTag() throws Exception {
        tagDao.delete(2);
        assertEquals(null, tagDao.findOne(2));
    }
}

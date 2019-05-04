package domain;

import dao.Database;
import dao.TagDao;
import domain.Tag;
import domain.TagService;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TagServiceTest {
    
    TagService tagService;
    
    @Before
    public void setUp() throws Exception {
        Database db = new Database("jdbc:sqlite::resource:testdatabase.db");
        tagService = new TagService(db);
        
        TagDao tagDao = new TagDao(db);
        tagDao.save(new Tag("Testtag1"));
        tagDao.save(new Tag("Testtag2"));
    }
    
    @After
    public void tearDown() throws Exception {
        for (Tag t : tagService.getAllTags()) {
            tagService.removeTag(t.getId());
        }
    }
    
    @Test
    public void getAllTagsReturnsTags() throws Exception {
        List<Tag> tags = tagService.getAllTags();
        assertEquals(1, tags.get(0).getId());
        assertEquals(2, tags.get(1).getId());
    }
    
    @Test
    public void removeTagRemovesTag() throws Exception {
        tagService.removeTag(2);
        assertEquals(1, tagService.getAllTags().size());
    }
}

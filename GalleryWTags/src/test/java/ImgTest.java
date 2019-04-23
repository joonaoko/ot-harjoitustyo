
import domain.Img;
import domain.Tag;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ImgTest {
    
    Img img;
    Img img2;
    List<Tag> tags;
    
    @Before
    public void setUp() {
        tags = new ArrayList<>();
        tags.add(new Tag("Testtag"));
        img = new Img(1, "Test", "Testpath", tags);
        img2 = new Img("Test2", "Testpath");
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void constructorSetsCorrectTitle() {
        assertEquals("Test", img.getTitle());
    }
    
    @Test
    public void constructorSetsCorrectPath() {
        assertEquals("Testpath", img2.getPath());
    }
    
    @Test
    public void getIdReturnsId() {
        assertEquals(1, img.getId());
    }
    
    @Test
    public void getTitleReturnsTitle() {
        assertEquals("Test", img.getTitle());
    }
    
    @Test
    public void getTagsStringReturnsTagsString() {
        assertEquals("Testtag, ", img.getTagsString());
    }
    /*
    @Test
    public void setIdSetsCorrectId() {
        img.setId(1);
        assertEquals(1, img.getId());
    }
    
    @Test
    public void addTagAddsTag() {
        img.addTag(new Tag("test"));
        assertEquals("test, ", img.getTags());
    }
    
    @Test
    public void removeTagRemovesTag() {
        img.addTag(new Tag("test"));
        img.removeTag(0);
        assertEquals("", img.getTags());
    }
    
    @Test
    public void toStringReturnsTitleAndTags() {
        img.addTag(new Tag("test"));
        assertEquals("Test\n[test]", img.toString());
    }
    @Test
    public void getTagsListReturnsTags() {
        Tag tag1 = new Tag("test1");
        Tag tag2 = new Tag("test2");
        
        img.addTag(tag1);
        img.addTag(tag2);
        
        ArrayList<Tag> testtags = new ArrayList<>();
        testtags.add(tag1);
        testtags.add(tag2);
        
        assertEquals(testtags, img.getTagsList());
    }
    */
}


import domain.Img;
import domain.Tag;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ImgTest {
    
    Img img;
    
    @Before
    public void setUp() {
        img = new Img("Test");
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void constructorSetsCorrectTitle() {
        assertEquals("Test", img.getTitle());
    }
    
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
    public void getTitleReturnsTitle() {
        assertEquals("Test", img.getTitle());
    }
    
    @Test
    public void toStringReturnsTitleAndTags() {
        img.addTag(new Tag("test"));
        assertEquals("Test\n[test]", img.toString());
    }
}

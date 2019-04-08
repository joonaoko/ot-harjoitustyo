
import gallerywtags.Image;
import gallerywtags.Tag;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class TagTest {
    Tag tag;
    
    @Before
    public void setUp() {
        tag = new Tag("Test");
    }
    
    @Test
    public void constructorSetsCorrectTitle() {
        assertEquals("Test", tag.toString());
    }
}

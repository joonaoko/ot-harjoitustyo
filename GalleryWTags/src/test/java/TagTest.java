
import domain.Img;
import domain.Tag;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class TagTest {
    Tag tag;
    Tag tag2;
    
    @Before
    public void setUp() {
        tag = new Tag("Test");
        tag2 = new Tag(2, "Test2");
    }
    
    @Test
    public void constructorSetsCorrectTitle() {
        assertEquals("Test", tag.toString());
    }
    
    @Test
    public void getIdReturnsId() {
        assertEquals(2, tag2.getId());
    }
}

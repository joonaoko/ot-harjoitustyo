
import gallerywtags.Image;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ImageTest {
    
    Image img;
    
    @Before
    public void setUp() {
        img = new Image("Test");
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void constructorSetsCorrectTitle() {
        assertEquals("Test", img.getTitle());
    }
}

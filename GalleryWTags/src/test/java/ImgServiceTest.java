
import domain.Img;
import domain.ImgService;
import domain.Tag;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ImgServiceTest {
    
    ImgService imgService;
    
    @Before
    public void setUp() throws Exception {
        imgService = new ImgService();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void getImageWithIdReturnsCorrectImage() throws Exception {
        assertEquals("Test", imgService.getImage(1).getTitle());
    }
}

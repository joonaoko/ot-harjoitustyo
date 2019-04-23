
import domain.Img;
import domain.ImgService;
import domain.Tag;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ImgServiceTest {
    
    ImgService imgService;
    Img img;
    List<Tag> tags;
    
    @Before
    public void setUp() throws Exception {
        imgService = new ImgService();
        tags = new ArrayList<>();
        // tags.add(new Tag(3, ))
        img = new Img("Testimage", "Testpath");
    }
    
    @After
    public void tearDown() throws Exception {
        for (Img i : imgService.getImages()) {
            if (i.getId() > 2) {
                imgService.removeImage(i.getId());
            }
        }
    }
    
    @Test
    public void getImageWithIdReturnsCorrectImage() throws Exception {
        assertEquals("Test", imgService.getImage(1).getTitle());
    }
    
    @Test
    public void addImageAddsImage() throws Exception {
        imgService.addImage(img.getTitle(), img.getPath());
        Img img2 = imgService.getImage(3);
        assertEquals("Testimage", img2.getTitle());
        assertEquals("Testpath", img2.getPath());
    }
    
    @Test
    public void addImageTagsAddsTag() throws Exception {
        imgService.addImageTag(3, "Testtag");
        System.out.println("After Add:");
        System.out.println(imgService.getImageTags(3));
        String testString = imgService.getImageTags(3).get(0).toString();
        assertEquals("Testtag", testString);
    }
    
    @Test
    public void removeImageTagRemovesTag() throws Exception {
        imgService.removeImageTag(3, 4);
        int sizeAfterRemove = imgService.getImageTags(3).size();
        assertEquals(0, sizeAfterRemove);
    }
    
    @Test
    public void removeImageRemovesImage() throws Exception {
        imgService.removeImage(3);
        assertEquals(null, imgService.getImage(3));
    }
}

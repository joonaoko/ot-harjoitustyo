package domain;


import dao.Database;
import domain.Img;
import domain.ImgService;
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

public class ImgServiceTest {
    
    ImgService imgService;
    TagService tagService;
    Img img;
    
    @Before
    public void setUp() throws Exception {
        Database db = new Database("jdbc:sqlite::resource:testdatabase.db");
        imgService = new ImgService(db);
        tagService = new TagService(db);
        img = new Img("Testimage", "Testpath");
        
        imgService.addImage("Test Image 1", "Test Path 1");
        imgService.addImageTag(1, "Test Tag 1");
    }
    
    @After
    public void tearDown() throws Exception {
        for (Img i : imgService.getImages()) {
            imgService.removeImage(i.getId());
        }
        
        for (int i = 1; i <= 3; i++) {
            List<Tag> imgTags = imgService.getImageTags(i);
            for (Tag t : imgTags) {
                imgService.removeImageTag(i, t.getId());
            }
        }
        
        for (Tag t : tagService.getAllTags()) {
            tagService.removeTag(t.getId());
        }
    }
    
    @Test
    public void getImageWithIdReturnsCorrectImage() throws Exception {
        assertEquals("Test Image 1", imgService.getImage(1).getTitle());
    }
    
    @Test
    public void addImageAddsImage() throws Exception {
        imgService.addImage(img.getTitle(), img.getPath());
        Img img2 = imgService.getImage(2);
        assertEquals("Testimage", img2.getTitle());
        assertEquals("Testpath", img2.getPath());
    }
    
    @Test
    public void addImageTagsAddsTag() throws Exception {
        imgService.addImageTag(1, "Testtag");
        String testString = imgService.getImageTags(1).get(1).toString();
        assertEquals("Testtag", testString);
    }
    
    @Test
    public void removeImageTagRemovesTag() throws Exception {
        imgService.removeImageTag(1, 1);
        int sizeAfterRemove = imgService.getImageTags(1).size();
        assertEquals(0, sizeAfterRemove);
    }
    
    @Test
    public void removeImageRemovesImage() throws Exception {
        imgService.removeImage(1);
        assertEquals(null, imgService.getImage(1));
    }
    
    @Test
    public void findTagImagesReturnsImages() throws Exception {
        List<Img> imgs = imgService.getTagImages(1);
        assertEquals(1, imgs.size());
        assertEquals(1, imgs.get(0).getId());
    }
}

package dao;

import domain.Img;
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

public class ImgDaoTest {
    private ImgDao imgDao;
    
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();
    
    @Before
    public void setUp() throws Exception {
        File testdbfile = tempFolder.newFile("testdatabase.db");
        
        Database db = new Database("jdbc:sqlite:"+testdbfile.getPath());
        db.init();
        imgDao = new ImgDao(db);
        
        imgDao.save(new Img("Testimg1", "Testpath1"));
        imgDao.save(new Img("Testimg2", "Testpath2"));
    }
    
    @After
    public void tearDown() throws Exception {
        for (Img i : imgDao.findAll()) {
            imgDao.delete(i.getId());
        }
    }

    @Test
    public void findOneReturnsImg() throws Exception {
        assertEquals(1, imgDao.findOne(1).getId());
    }
    
    @Test
    public void findOneReturnsNullIfNotFound() throws Exception {
        assertEquals(null, imgDao.findOne(3));
    }
    
    @Test
    public void findAllReturnsAllImgs() throws Exception {
        List<Img> imgs = imgDao.findAll();
        assertEquals(1, imgs.get(0).getId());
        assertEquals(2, imgs.get(1).getId());
    }
    
    @Test
    public void saveAddsImage() throws Exception {
        imgDao.save(new Img("Testimg3", "Testpath3"));
        assertEquals(3, imgDao.findOne(3).getId());
    }
    
    @Test
    public void deleteRemovesImage() throws Exception {
        imgDao.delete(2);
        assertEquals(null, imgDao.findOne(2));
    }
}

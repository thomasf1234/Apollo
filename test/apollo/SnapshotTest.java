/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apollo;

import java.awt.image.BufferedImage;
import java.io.File;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ad
 */
public class SnapshotTest {
    
    public SnapshotTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getSnapshot method, of class Snapshot.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetSnapshot() throws Exception {
        System.out.println("getSnapshot");
        Pianist pianist = new Pianist();
        float scale = 1.0F;
        Snapshot instance = new Snapshot();
        Transcription transcription = new Transcriber().transcribe("test\\samples\\test.mid");
        pianist.setTranscription(transcription);
        pianist.elapsedTime = 1.0;
        
        Image actualSnapshot = instance.getSnapshot(pianist, scale);
        ImageIO.write(SwingFXUtils.fromFXImage(actualSnapshot, null), "png", new File("tmp\\actual.png"));
        
        assertEquals(true, Utilities.compareFiles("tmp\\actual.png", "test\\samples\\testGetSnapshot.png"));     
    }
}

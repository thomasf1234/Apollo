/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apollo;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

/**
 *
 * @author ad
 */
public class Snapshot {

    public BufferedImage background;
    private final BufferedImage[] DOWN_KEYS;
    private final BufferedImage[] FUTURE;
    private static final int OCTAVE_WIDTH = 140;
    private final Map<String, Point> KEY_POSITION_MAP;
    public static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
    
    public Snapshot() throws IOException {
        this.background = ImageIO.read(new File("images\\basic_background.png"));
        this.DOWN_KEYS = initializeDOWN_KEYS();
        this.KEY_POSITION_MAP = initializeKEY_POSITION_MAP();
        this.FUTURE = initializeFUTURE();
    }

    public Image getSnapshot(Pianist pianist, float scale) throws IOException {
        BufferedImage image = new BufferedImage(this.background.getWidth(), this.background.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.drawImage(this.background, 0, 0, image.getWidth(), image.getHeight(), null);

        renderInterval(g, pianist.transcription, new Interval(pianist.elapsedTime, pianist.elapsedTime + 6.0f));
        renderKeybed(pianist.piano, g);    
        
        BufferedImage scaledImage = scale(image, scale);

        return SwingFXUtils.toFXImage(scaledImage, null);
    }
  
    public void renderInterval(Graphics2D g, Transcription transcription, Interval interval) {
      for (Note note : transcription.notes) {
        if (note.interval.intersects(interval)) { 
            BufferedImage base;
            if (note.isBlackKey()) 
              base = this.FUTURE[1];
            else
              base = this.FUTURE[0];
           
           int x = this.KEY_POSITION_MAP.get(note.getNoteName()).getX();
           int y;
           int width = base.getWidth();
           int height;
           
           if (note.interval.getEndTime() > interval.getEndTime()) 
               y = 47;
           else 
               y = (int) (47 + ((interval.getEndTime() - note.interval.getEndTime()) / interval.getDuration()) * 543);
           
           if (note.interval.getStartTime() < interval.getStartTime())
               height = 590 - y;
           else
               height = 590 - y - (int)(((note.interval.getStartTime() - interval.getStartTime()) / interval.getDuration()) * 543);
               
           g.drawImage(base, x, y, width, height, null);   
        }
      }   
    }
    
    public void renderKeybed(Piano piano, Graphics2D g) {
        for (int midiNoteNumber=9; midiNoteNumber<97; midiNoteNumber++) {
            int velocity = piano.getNoteState(midiNoteNumber);   
            if (velocity > 0) {
              String noteName = Note.noteNameFor(midiNoteNumber);
              try {
                  g.drawImage(this.DOWN_KEYS[midiNoteNumber % 12], this.KEY_POSITION_MAP.get(noteName).getX(), this.KEY_POSITION_MAP.get(noteName).getY(), null);        
              } catch(Exception e) {
                  
              }
            }
        }
    }
    
    public BufferedImage scale(BufferedImage bufferedImage, float scale) {
        BufferedImage scaledBufferedImage = new BufferedImage(Math.round(scale * bufferedImage.getWidth()),
                Math.round(scale * bufferedImage.getHeight()),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) scaledBufferedImage.getGraphics();
        g.scale(scale, scale);
        g.drawImage(bufferedImage, 0, 0, null);
        g.dispose();

        return scaledBufferedImage;
    }
    
    private Map<String, Point> initializeKEY_POSITION_MAP() {
        Map<String, Point> result = new HashMap<String, Point>();
        result.put("A0", new Point(141 - (1 * OCTAVE_WIDTH), 599));
        result.put("A#1", new Point(157 - (1 * OCTAVE_WIDTH), 599));
        result.put("B0", new Point(161 - (1 * OCTAVE_WIDTH), 599));

        for (int i = 0; i < 7; i++) {
            result.put("C" + (i + 1), new Point(41 + (i * OCTAVE_WIDTH), 599));
            result.put("C#" + (i + 1), new Point(53 + (i * OCTAVE_WIDTH), 599));
            result.put("D" + (i + 1), new Point(61 + (i * OCTAVE_WIDTH), 599));
            result.put("D#" + (i + 1), new Point(76 + (i * OCTAVE_WIDTH), 599));
            result.put("E" + (i + 1), new Point(81 + (i * OCTAVE_WIDTH), 599));
            result.put("F" + (i + 1), new Point(101 + (i * OCTAVE_WIDTH), 599));
            result.put("F#" + (i + 1), new Point(111 + (i * OCTAVE_WIDTH), 599));
            result.put("G" + (i + 1), new Point(121 + (i * OCTAVE_WIDTH), 599));
            result.put("G#" + (i + 1), new Point(134 + (i * OCTAVE_WIDTH), 599));
            result.put("A" + (i + 1), new Point(141 + (i * OCTAVE_WIDTH), 599));
            result.put("A#" + (i + 1), new Point(157 + (i * OCTAVE_WIDTH), 599));
            result.put("B" + (i + 1), new Point(161 + (i * OCTAVE_WIDTH), 599));
        }

        result.put("C8", new Point(41 + (7 * OCTAVE_WIDTH), 599));

        return Collections.unmodifiableMap(result);
    }
    
    private static BufferedImage[] initializeDOWN_KEYS() throws IOException {
        BufferedImage[] array = new BufferedImage[12];
        array[0] = ImageIO.read(new File("images\\c_down.png")); 
        array[1] = ImageIO.read(new File("images\\c#_down.png")); 
        array[2] = ImageIO.read(new File("images\\d_down.png")); 
        array[3] = ImageIO.read(new File("images\\d#_down.png")); 
        array[4] = ImageIO.read(new File("images\\e_down.png")); 
        array[5] = ImageIO.read(new File("images\\f_down.png")); 
        array[6] = ImageIO.read(new File("images\\f#_down.png")); 
        array[7] = ImageIO.read(new File("images\\g_down.png")); 
        array[8] = ImageIO.read(new File("images\\g#_down.png")); 
        array[9] = ImageIO.read(new File("images\\a_down.png")); 
        array[10] = ImageIO.read(new File("images\\a#_down.png")); 
        array[11] = ImageIO.read(new File("images\\b_down.png")); 
        
        return array;
    }
    
    private static BufferedImage[] initializeFUTURE() throws IOException {
        BufferedImage[] array = new BufferedImage[2];
        array[0] = ImageIO.read(new File("images\\white_key_future.png")); 
        array[1] = ImageIO.read(new File("images\\black_key_future.png")); 
        
        return array;
    }
}

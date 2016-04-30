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
    public BufferedImage cDown;
    public BufferedImage cSharpDown;
    public BufferedImage gSharpDown;
    private static final int OCTAVE_WIDTH = 140;
    private static final Map<String, Point> KEY_POSITION_MAP = initializeKEY_POSITION_MAP();

    private static Map<String, Point> initializeKEY_POSITION_MAP() {
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
            result.put("F#" + (i + 1), new Point(112 + (i * OCTAVE_WIDTH), 599));
            result.put("G" + (i + 1), new Point(121 + (i * OCTAVE_WIDTH), 599));
            result.put("G#" + (i + 1), new Point(134 + (i * OCTAVE_WIDTH), 599));
            result.put("A" + (i + 1), new Point(141 + (i * OCTAVE_WIDTH), 599));
            result.put("A#" + (i + 1), new Point(157 + (i * OCTAVE_WIDTH), 599));
            result.put("B" + (i + 1), new Point(161 + (i * OCTAVE_WIDTH), 599));
        }

        result.put("C8", new Point(41 + (7 * OCTAVE_WIDTH), 599));

        return Collections.unmodifiableMap(result);
    }

    public Snapshot() throws IOException {
        this.background = ImageIO.read(new File("images\\basic_background.png"));
        this.cDown = ImageIO.read(new File("images\\c_down.png"));
        this.cSharpDown = ImageIO.read(new File("images\\c#_down.png"));
        this.gSharpDown = ImageIO.read(new File("images\\g#_down.png"));
    }

    public Image getSnapshot(float scale) throws IOException {
        //public Image getSnapshot(Interval interval, KeyPress allKeyPresses) throws IOException {

        BufferedImage image = new BufferedImage(this.background.getWidth(), this.background.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.drawImage(this.background, 0, 0, image.getWidth(), image.getHeight(), null);

        //g.drawImage(this.cDown, 40, 599, null);
        for(int i=1 ; i<8; i++) {
          g.drawImage(this.cSharpDown, KEY_POSITION_MAP.get("C#"+i).getX(), KEY_POSITION_MAP.get("C#"+i).getY(), null);   
          g.drawImage(this.gSharpDown, KEY_POSITION_MAP.get("G#"+i).getX(), KEY_POSITION_MAP.get("G#"+i).getY(), null);    
        }
        
        BufferedImage scaledImage = scale(image, scale);
        ImageIO.write(scaledImage, "png", new File("tmp\\saved.png"));

        return SwingFXUtils.toFXImage(image, null);
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
}

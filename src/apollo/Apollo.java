/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apollo;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.animation.AnimationTimer;

/**
 *
 * @author ad
 */
public class Apollo extends Application 
{
    public RunnablePianist runnablePianist;
    
    public static void main(String[] args) throws MidiUnavailableException, InvalidMidiDataException, IOException {
        launch(args);
    }

    @Override
    public void start(Stage theStage) throws MidiUnavailableException, InvalidMidiDataException, IOException 
    {
        theStage.setTitle( "Apollo" );

        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );

        Canvas canvas = new Canvas( 1041, 724 );
        root.getChildren().add( canvas );

        GraphicsContext gc = canvas.getGraphicsContext2D();

        final long startNanoTime = System.nanoTime();

        //this.runnablePianist = new RunnablePianist("Pianist", new Transcriber().transcribe("test\\samples\\test2.mid"));
        this.runnablePianist = new RunnablePianist("Pianist", new Transcriber().transcribe("C:\\Users\\ad\\Documents\\my_songs\\tom_odell_-_.mid"));

        runnablePianist.start();
        Snapshot renderer = new Snapshot();
        
        new AnimationTimer()
        {
            @Override
            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - startNanoTime) * Math.pow(10, -9); 

                try {
                    gc.drawImage( renderer.getSnapshot(runnablePianist.pianist, 1.0f, t), 0, 0 );
                } catch (IOException ex) {
                    Logger.getLogger(Apollo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();
        
        theStage.show();
    }
    
    @Override
    public void stop() {
        System.out.println("Stage is closing");
        
        if(this.runnablePianist != null) {
            this.runnablePianist.pianist.finish();
        }
        // Save file
    }
}
//http://stackoverflow.com/questions/26619566/javafx-stage-close-handler
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apollo;

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
//class RunnableDemo implements Runnable {
//   private Thread thread;
//   private final String threadName;
//   
//   RunnableDemo( String name){
//       threadName = name;
//   }
//   @Override
//   public void run() {
//      try {
//         for(int i = 4; i > 0; i--) {
//            System.out.println("Thread: " + threadName + ", " + i);
//            // Let the thread sleep for a while.
//            Thread.sleep(50);
//         }
//     } catch (InterruptedException e) {
//     }
//   }
//   
//   public void start ()
//   {
//      if (this.thread == null)
//      {
//         this.thread = new Thread (this, this.threadName);
//         this.thread.start ();
//      }
//   }
//
//}

public class ThreadSafeArrayTest {
    
    public ThreadSafeArrayTest() {
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

    @Test
    public void testSize() {
        ThreadSafeArray array = new ThreadSafeArray(3);
        
        assertEquals(3, array.size());
    }
    
    @Test
    public void testSet_testGet() {
        ThreadSafeArray array = new ThreadSafeArray(3);
        array.set(0, 12);
        array.set(1, 13);
        array.set(2, 15);
        
        assertEquals(12, array.get(0));
        assertEquals(13, array.get(1));
        assertEquals(15, array.get(2));
    }
    
    //testing that the array cannot be read from while updating. 
    //find a way to test this
//    @Test
//    public void testMultiThreadReadWrite() {
//        ThreadSafeArray array = new ThreadSafeArray(3);
//
//        RunnableDemo R1 = new RunnableDemo("Thread-1");
//        R1.start();
//
//        RunnableDemo R2 = new RunnableDemo("Thread-2");
//        R2.start();
//    }
}

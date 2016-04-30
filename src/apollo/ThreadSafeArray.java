/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apollo;

/**
 *
 * @author ad
 */
public class ThreadSafeArray {
  private final int[] array;
  
  public ThreadSafeArray(int length) {
      this.array = new int[length];
  }
  
  public void set(int index, int value) {
    synchronized (array) {
      array[index] = value;
    }
  }

  public int get(int index) { 
    int returnValue;
       
    synchronized (array) {
      returnValue = array[index];  
    }
    
    return returnValue;
  }
  
  public int size() {
      return this.array.length;
  }
}
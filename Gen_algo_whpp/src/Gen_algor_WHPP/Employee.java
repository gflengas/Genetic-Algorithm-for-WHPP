package ai2;

import java.util.Random;

public class Employee {
	
	public static final int SIZE = 14;
    private int[] shift = new int[SIZE];

    public Employee() {}
    
    //getters and setters
    public int getshift(int index) {
        return shift[index];
    }

    public void setshift(int index, int shift) {
        this.shift[index] = shift;
    }
    
    //generate random shifts for this employee
    public void randshift() {
        Random rand = new Random();
        for(int i=0; i<SIZE; ++i) {
            this.setshift(i, rand.nextInt(4));
        }
    }  
    
    
}

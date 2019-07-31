package ai2;

import java.util.Arrays;
import java.util.Comparator;

public class Population {

	 private Schedule[] Sched_Pop;
	 private double totalFitness;
	 private int POP_SIZE;
	 
	 public Population(int POP_SIZE) {
		 Sched_Pop = new Schedule[POP_SIZE];
		 this.POP_SIZE=POP_SIZE;
	     // init population
	     for (int i = 0; i < POP_SIZE; i++) {
	    	 Sched_Pop[i] = new Schedule();
	     }
	 }
	
	 public Schedule getChromosome(int idx) {
		 return this.Sched_Pop[idx];
	 }
	
	public int getPOPSIZE() {
		return this.POP_SIZE;
	}
	
    public void setPopulation(Schedule[] newPop) {
        // this.Sched_Pop = newPop;
        System.arraycopy(newPop, 0, this.Sched_Pop, 0, POP_SIZE);
    }

    public Schedule[] getPopulation() {
        return this.Sched_Pop;
    }

    //We call this so we can calculate the score of each Chromosome-Schedule
    public double PopulationTotalFitness() {
        this.totalFitness = 0.0;
        for (int i = 0; i < POP_SIZE; i++) {
        	Sched_Pop[i].SchedScore();
            this.totalFitness += Sched_Pop[i].getFitness();
        }
        return this.totalFitness;
    }
    
    public double getTotalFitness() {
    	return this.totalFitness;
    }
   
    //sets the Schedules from best to worst and picks the 1 based on offset
	public Schedule getFittest(int offset) {
		// Order population by fitness
		Arrays.sort(this.Sched_Pop, new Comparator<Schedule>() {
			@Override
			public int compare(Schedule s1, Schedule s2) {
				if (s1.getFitness() < s2.getFitness()) {
					return -1;
				} else if (s1.getFitness() > s2.getFitness()) {
					return 1;
				}
				return 0;
			}
		});
		// Return the fittest individual
		return this.Sched_Pop[offset];
	}
     
    public Schedule setSchedule(int index, Schedule schedule) {
		return Sched_Pop[index] = schedule;
	}
}

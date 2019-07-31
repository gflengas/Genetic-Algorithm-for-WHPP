package ai2;

import java.util.Random;

public class GeneticAlgorithm {
	private int populationSize;
	private double mutationRate;
	private double crossoverRate;
	private int MaxIter;
	private int elitismCount;
	private static Random m_rand = new Random();  // random-number generator
	
	public GeneticAlgorithm(int populationSize, double mutationRate, double crossoverRate, int MaxIter, int elitismCount) {
		this.populationSize = populationSize;
		this.mutationRate = mutationRate;
		this.crossoverRate = crossoverRate;
		this.MaxIter = MaxIter;
		this.elitismCount=elitismCount;
	}
	
	
	
	public int getMaxIter() {
		return MaxIter;
	}
	
	
	//perfect case for a perfect world never gonna happen on this case
	public boolean perfectTermination(Population pop) {
		for (Schedule sched : pop.getPopulation()) {
			if (sched.getFitness() <= 10) {
				return true;
			}
		}

		return false;
	}
	
	 //Roulette selection 
    public Schedule rouletteWheelSelection(Population pop) {
    	Schedule Sched[] = pop.getPopulation();
    	//Wheel spin
        double randNum = m_rand.nextDouble() * pop.getTotalFitness();
        int idx,wheel=0;
        for (idx=0; idx<pop.getPOPSIZE() && randNum>0; ++idx) {
        	wheel+= Sched[idx].getFitness();
        	
            if(wheel>=randNum) 
            	return Sched[idx];
        }
        return Sched[pop.getPOPSIZE()-1];
    }
    
    public Population crossoverBest(Population population) {
    	Population newPopulation = new Population(population.getPOPSIZE());
    	Schedule parent1 = population.getFittest(0);
    	Schedule parent2 = population.getFittest(1);
    	Schedule offspring = new Schedule();
    	for(int i=0;i<population.getPOPSIZE()-1;i++) {
    		newPopulation.setSchedule(i, population.getChromosome(i));
    	}
    	for (int geneindex = 0; geneindex < Schedule.EMP_NUM; geneindex++) {
			// Use half of parent1's genes and half of parent2's genes
					if(geneindex<(Schedule.EMP_NUM/2)){
						//offspring.getEmployee(geneindex).setshift(i, parent1.getEmployee(geneindex).getshift(i));
						offspring.setEmployee(geneindex, parent1.getEmployee(geneindex));
					}else{
						//offspring.getEmployee(geneindex).setshift(i, parent2.getEmployee(geneindex).getshift(i));
						offspring.setEmployee(geneindex, parent2.getEmployee(geneindex));
					}
				
    	}
    	newPopulation.setSchedule(population.getPOPSIZE()-1, offspring);
    	if(offspring.evaluate()==14) {
    		return newPopulation;
    	}
    	else return population;
    	
    }
    
    public Population crossoverPopulation(Population population) {
		// Create new population
		Population newPopulation = new Population(population.getPOPSIZE());

		// Loop over current population by fitness
		for (int index = 0; index < population.getPOPSIZE(); index++) {
			Schedule parent1 = population.getFittest(index);

			// Apply crossover to this individual?
			if (this.crossoverRate > Math.random() && index >= this.elitismCount) {
				// Initialize offspring
				Schedule offspring = new Schedule();
				// Find second parent
				Schedule parent2 = rouletteWheelSelection(population);

				// Loop over genome
				for (int geneindex = 0; geneindex < Schedule.EMP_NUM; geneindex++) {
					// Use half of parent1's genes and half of parent2's genes
					//for(int i=0;i<Employee.SIZE;i++) {
						if (0.5 > Math.random()) {
							//offspring.getEmployee(geneindex).setshift(i, parent1.getEmployee(geneindex).getshift(i));
							offspring.setEmployee(geneindex, parent1.getEmployee(geneindex));
						} else {
							//offspring.getEmployee(geneindex).setshift(i, parent2.getEmployee(geneindex).getshift(i));
							offspring.setEmployee(geneindex, parent2.getEmployee(geneindex));
						}
						
					//}
					
				}

				// Add offspring to new population after evaluating it 
				if(offspring.evaluate()==14) {
					offspring.SchedScore();
					newPopulation.setSchedule(index, offspring);
				}else {
					//newPopulation.setSchedule(index, parent1);
				}
			} else {
				// Add individual to new population without applying crossover
				newPopulation.setSchedule(index, parent1);
			}
		}

		return newPopulation;
	}


    public Population mutatePopulation(Population pop) {
		// Initialize new population
		Population newPopulation = new Population(this.populationSize);

		// Loop over current population by fitness
		for (int index = 0; index < pop.getPOPSIZE(); index++) {
			Schedule sched = pop.getFittest(index);

			// Loop over individual's genes
			for (int geneIndex = 0; geneIndex < Schedule.EMP_NUM; geneIndex++) {
				// Skip mutation if this is an elite individual
				if (index > this.elitismCount) {
					// Does this gene need mutation?
					if (this.mutationRate > Math.random()) {
						// Get new gene
						Employee newEmployee =new Employee();
						newEmployee.randshift();
						// Mutate gene
						sched.setEmployee(geneIndex, newEmployee);
						sched.SchedScore();
					}
				}
			}
			newPopulation.setSchedule(index, sched);
		}

		// Return mutated population
		return newPopulation;
	}
    
    //flip mutation 
    public Population flipMutate(Population pop) {
		// Initialize new population
		Population newPopulation = new Population(this.populationSize);

		// Loop over current population by fitness
		for (int index = 0; index < pop.getPOPSIZE(); index++) {
			Schedule sched = pop.getFittest(index);

			// Loop over individual's genes
			for (int geneIndex = 0; geneIndex < Schedule.EMP_NUM; geneIndex++) {
				// Skip mutation if this is an elite individual
				if (index > this.elitismCount) {
					// Does this gene need mutation?
					if (this.mutationRate > Math.random()) {
						sched.mutate();
					}
				}
			}
			newPopulation.setSchedule(index, sched);

		}

		// Return mutated population
		return newPopulation;
	}


	

}

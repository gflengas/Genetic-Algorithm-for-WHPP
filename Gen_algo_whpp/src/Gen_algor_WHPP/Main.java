package ai2;

public class Main {

	public static void main(String[] args) {
		GeneticAlgorithm ga = new GeneticAlgorithm(20, 0.001, 0.95, 100, 2);

		// Initialize
		Population population = new Population(20);
		// Evaluate
		population.PopulationTotalFitness();
		// Keep track of current generation
		int gen = 1;
		
		//perfectTermination when totalFitness <10 , normalTermination based on iteretion
		//while (!ga.perfectTermination(population)) {
		while (gen<=ga.getMaxIter()) {
			//Fittest Schedule of the population of this Generation
			System.out.println("Generation: "+gen+" Best solution: " + population.getFittest(0).getFitness()+" Averege Fitness: "+(population.getTotalFitness()/population.getPOPSIZE()));
			// Apply crossover
			population = ga.crossoverPopulation(population);
			//population = ga.crossoverBest(population);
			
			// Apply mutation
			population = ga.mutatePopulation(population);
			//population = ga.flipMutate(population);
			// Evaluate population
			population.PopulationTotalFitness();
			
			// Increment the current generation
			gen++;
		}
		//	for(int i=0;i<population.getPOPSIZE();i++) population.getChromosome(i).printSched();
		/**
		 * We're out of the loop now, which means we have a perfect solution on
		 * our hands. Let's print it out to confirm that it is actually all
		 * ones, as promised.
		 */
		if(population.getFittest(0).evaluate()==14) {
			System.out.println("Found solution in " + (gen-1) + " generations"+" Averege Fitness: "+(population.getTotalFitness()/population.getPOPSIZE()));
			population.getFittest(0).printSched();
		}
		else {
			System.out.print("Error");
		}
	}
		  
		 
		 

}



# Genetic-Algorithm-for-WHPP
Project developed for Artificial Intelligence course in collaboration with [Emmanouela Theodoraki](https://github.com/etheodoraki)<br/>
Using the theory of genetic algorithm, the code of this repository came to life. It includes 2 Selection methods, 2 crossover methods and 2 mutation methods.

## Correlation between Genetic Algorithm and our implementation 
According to the genetic algorithm, the chromosomes of each population that have the most suitable genes are more likely to be selected for reproduction than those that do not have them. A population is made up of chromosomes, which in turn consist of genes, which describe the characteristics of the chromosome.

Starting with an initial population, receiving random values from the search space, the chromosomes are passed through the fitness function and the characteristics of the genes, giving it a representative value. Then based on this score, the best are selected for crossbreeding and production of offspring, synthesizing their genes and at the same time, thanks to the mutation take care to avoid the creating homogeneity in the population.

General pseudocode illustrating the above is as follows:
```
Initialize population p
evaluate p
while not end_condition{
          select parents
          Crossover
          Mutation
          evaluate new population
}
```
In our implementation, the role of the chromosome is assigned to its schedule, whose genes are the work schedule of each employee. The schedule of each employee is characterized by the shift in which he/she works for each of his day's time horizons. The evaluation function will mainly assess the light restrictions giving a score to each possible schedule, while strict restrictions should be controlled when creating a new schedule. During crossover, 2 schedules are selected and a new one will be created, combining schedules of employees who will come from both parents, while for the mutation they will change places with a andom worker's schedule.

## Implementation
<ins>Initialization:</ins>

When initializing the population, we first set all shifts to '0', and then we start giving values to the shift table based on the days. Randomly we select one of the 30 employees and if he has not been assigned a shift, we assign him in order of priority: morning '1', afternoon '2', evening '3'. Once the number of people needed for the day in the morning shift is filled, we proceed to the evening and in the same way, followed up by the evening. When all shifts are full, the process starts from the beginning for the next day, until filling in the number of days provided for in the time horizon.

<ins>Consistency check - absolute restrictions:</ins>

Thanks to the way we created our original population, we ensure it
fullfills the absolute restrictions. We also created another function, **public int evaluate ()**, which we call whenever there is a crossover and mutation, in order the new chromosomes meet these limitations

<ins>Grading - elastic constraints:</ins>

To implement the fitness function, we relied on the soft constraints table given to us, we created the **public double ScoreCalculation (Employee Empl)** function, which checks an employee's work schedule and adds them to his fitness score, the necessary units in case one of its restrictions is not observed. In the end, it returns all of these units and sets them as the fitness of this schedule. This function is repeatedly called through the **public void SchedScore ()** to calculate the overall fitness of the chromosome-schedule.

<ins>Selection method:</ins>

We have 2 selection methods which we utilize in our algorithm:
--The first, **public Population crossoverBest(Population population)**, is based on the choice of the 2 best chromosomes through getFittest and then use for the 1st half the genes of the best, one for the 2nd the genes of the 2nd best.
--The second, **public Population crossoverPopulation(Population population)**, initially chooses the best chromosome as a parent and then considering elitism and crossover ratio, utilizes the rouletteWheelSelection, for the choice of the 2nd parent and finally chooses random half-half of the parents the genes and creates the new chromosome, checks its consistency and if it is acceptable adds it to the new population. Otherwise adds parent1.

<ins>Crossover methods</ins>

<ins>Mutation methods:</ins>

<ins>Termination criteria:</ins>

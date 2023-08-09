# Genetic-Algorithm-for-WHPP
Project developed for Artificial Intelligence course in collaboration with [George Flengas](https://github.com/gflengas)<br/>
Using the theory of genetic algorithm, the code of this repository came to life. It includes 2 Selection methods, 2 crossover methods and 2 mutation methods.

## Correlation between Genetic Algorithm and our implementation 
According to the genetic algorithm, the chromosomes of each population that have the most suitable genes are more likely to be selected for reproduction than those that do not have them. A population is made up of chromosomes, which in turn consist of genes, which describe the characteristics of the chromosome.

![explanation image](https://github.com/gflengas/Genetic-Algorithm-for-WHPP/blob/master/pictures/1.png)

Starting with an initial population, receiving random values from the search space, the chromosomes are passed through the fitness function and the characteristics of the genes, giving it a representative value. Then based on this score, the best are selected for crossbreeding and production of offspring, synthesizing their genes and at the same time, thanks to the mutation take care to avoid the creating homogeneity in the population.

![explanation image2](https://github.com/gflengas/Genetic-Algorithm-for-WHPP/blob/master/pictures/2.png)

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
- The first is to classify in terms of fitness all members of the population and to receive the best medium of the **public Schedule getFittest(int offset)**, in which we put the offset to 0 to get the best chromosome.
- The second, is implemented through **public Schedule rouletteWheelSelection(Population pop)**, which performs a selection using roulette as described in the description given to us. We select a random point of the sum of the fitness of the population and then repeatedly add the fitness until we exceed this point. Once it happens, we return the chromosome we added last.

<ins>Crossover methods</ins>

We were assigned to create 2 different crossover methods:
- The first, **public Population crossoverBest(Population population)**, is based on the choice of the 2 best chromosomes through getFittest and then use for the 1st half the genes of the best, one for the 2nd the genes of the 2nd best.
- The second, **public Population crossoverPopulation(Population population)**, initially chooses the best chromosome as a parent and then considering elitism and crossover ratio, utilizes the rouletteWheelSelection, for the choice of the 2nd parent and finally chooses random half-half of the parents the genes and creates the new chromosome, checks its consistency and if it is acceptable adds it to the new population. Otherwise adds parent1.

<ins>Mutation methods:</ins>

For the mutation, we were asked to create 2 different methods. Both are based on both Elitism and mutation rate:
- **public Population mutatePopulation(Population pop)**, when the mutation rate is met, a random employee schedule is created and replaces its existing program.
- **Population flipMutate(Population pop)**, when the mutation rate is satisfied, exchanges shifts of 2 random employees for a specific day.

<ins>Termination criteria:</ins>

Regarding the criterion with which the algorithm will end the evolution, we tried 2 different versions:
- The first, which would be the most attractive, is to get to the point where the score would be too small (<100), in order to have an almost perfect schedule. This never happened and we ended up getting to a point where the program stopped evolving.
- The second, which we eventually used to derive our results, was based on the number of maximum repetitions. When our algorithm exceeds the number, it stops and gives us back the best program it managed to create.

## Results
After experimenting with different values in the initial population (pop), the maximum number of repeats (MaxIter), the chances of selection - crossover - mutation, we decided to present the results of 2 typical cases, shown below:

![explanation image3](https://github.com/gflengas/Genetic-Algorithm-for-WHPP/blob/master/pictures/3.png)

The following are indicative of the changes in the values of the best score and the mean for the combination crossoverPopulation and mutatePopulation which is the best combination result from the algorithms we implemented:

**Best score per Generation**

![explanation image4](https://github.com/gflengas/Genetic-Algorithm-for-WHPP/blob/master/pictures/4%20best%20score%20per%20generation.png)

**Average score per Generation**

![explanation image5](https://github.com/gflengas/Genetic-Algorithm-for-WHPP/blob/master/pictures/5%20avg%20score%20per%20generation.png)

The format of the respective schedule will be:

![explanation image6](https://github.com/gflengas/Genetic-Algorithm-for-WHPP/blob/master/pictures/6.png)

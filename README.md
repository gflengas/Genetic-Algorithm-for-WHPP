# Genetic-Algorithm-for-WHPP
Project developed for Artificial Intelligence course with [Emmanouela Theodoraki](https://github.com/etheodoraki)<br/>
Using the theory of genetic algorithm, the code of this repository came to life. It icludes 2 Selection methods, 2 crossover methods and 2 mutation methods.

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

<ins>Consistency check - absolute restrictions:</ins>

<ins>Grading - elastic constraints:</ins>

<ins>Selection method:</ins>

<ins>Crossover methods</ins>

<ins>Mutation methods:</ins>

<ins>Termination criteria:</ins>

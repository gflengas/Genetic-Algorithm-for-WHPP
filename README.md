# Genetic-Algorithm-for-WHPP
Project developed for Artificial Intelligence course with [Emmanouela Theodoraki](https://github.com/etheodoraki)<br/>
Using the theory of genetic algorithm, the code of this repository came to life. It icludes 2 Selection methods, 2 crossover methods and 2 mutation methods.

## Correlation between Genetic Algorithm and our implementation 
According to the genetic algorithm, the chromosomes of each population that have the most suitable genes are more likely to be selected for reproduction than those that do not have them. A population is made up of chromosomes, which in turn consist of genes, which describe the characteristics of the chromosome.

Starting with an initial population, receiving random values from the search space, the chromosomes are passed through the fitness function and the characteristics of the genes, giving it a representative value. Then based on this score, the best are selected for crossbreeding and production of offspring, synthesizing their genes and at the same time, thanks to the mutation take care to avoid the creating homogeneity in the population.

General pseudocode illustrating the above is as follows

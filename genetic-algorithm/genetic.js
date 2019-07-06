const _populationSize = 100
const _genes = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ 1234567890, .-;:_!\"#%&/()=?@${[]}"
const _target = "I love GeeksForGeeks"

/**
 * Generate random number
 * @param {Number} start
 * @param {Number} end
 * @returns {Number} Random number
 */
function _genRanNum(start, end) {
    return Math.floor((Math.random() * end) + start)
}

/**
 * Create random genes for mutation
 * @param {String} genes original form
 * @return {Char} a single gene
 */
function _mutatedGenes(genes) {
    let ranNum = _genRanNum(0, genes.length - 1)
    return genes.charAt(ranNum)
}

/**
 * Create chromosome or string of genes
 * @param {String} genes to be altered
 * @param {String} target target genes
 * @return {String} gnome
 */
function _createGnome(genes, target) {
    let gnome = ''
    for (let i = 0; i < target.length; i++) gnome += _mutatedGenes(genes)
    // console.log('gnome = ' + gnome)
    return gnome
}

/**
 * Class to represent individual population
 */
class Individual {
    /**
     * Initialize individual
     * @param {String} chromosome
     * @param {String} genes
     * @param {String} target 
     */
    constructor(chromosome, target) {
        this.target = target
        this.chromosome = chromosome
        this.fitness = this.calculateFitness()
        // console.log('Individual(): target = ' + this.target)
        // console.log('Individual(): chromosome = ' + this.chromosome)
        // console.log('Individual(): fitness = ' + this.fitness)
    }

    /**
     * Calculate fitness score, it is the number of
     * characters in string which differ from target
     * string.
     * @return {Int} fitness
     */
    calculateFitness() {
        let fitness = 0
        // console.log('cal fitness = ' + this.target)
        for (let i = 0; i < this.target.length; i++) {
            // console.log('a = ' + this.chromosome.charAt(i))
            // console.log('b = ' + this.target.charAt(i))
            if (this.chromosome.charAt(i) !== this.target.charAt(i)) {
                fitness++
                // console.log('increment fitness: ' + fitness)
            }
        }
        // console.log('returning fitness: ' + fitness)
        return fitness
    }


    /**
     * Perform mating and produce new offspring
     * @param {Individual} offspring create new child
     * @param {String} genes original
     */
    mate(offspring, genes) {
        let childChromosome = ''
        for (let i = 0; i < this.chromosome.length; i++) {
            // Random probability 
            let probability = Number(_genRanNum(0, 100) / 100)
            // If probability is less than 0.45,
            // from parent 1
            if (probability < 0.45) childChromosome += this.chromosome.charAt(i)
            // If probability is between 0.45 and 0.9,
            // insert gene from parent 2
            else if (probability < 0.9)
                childChromosome += offspring.chromosome.charAt(i)
            // Otherwise insert random gene (mutate),
            // for maintaining diversity
            else childChromosome += _mutatedGenes(genes)
        }
        // Create new individual(offspring, genes) using
        // generated chromosome for offspring
        console.log('childChromosome = ' + childChromosome)
        return new Individual(childChromosome, this.target)
    }
}

/**
 * Function to run genetic algorithm
 * @param {String} genes 
 * @param {String} target 
 * @param {Number} populationSize 
 */
function run(genes, target, populationSize) {
    let generation = 0
    let population = []
    let isFound = false

    // Create initial population
    for (let i = 0; i < populationSize; i++)
        population.push(new Individual(_createGnome(genes, target), target))

    while (!isFound) {
        // Create generation
        population = population.sort((ind1, ind2) => {
            if (ind1.fitness < ind2.fitness) return -1
            if (ind1.fitness > ind2.fitness) return 1
            return 0;
        })
        // If the individual having lowest fitness score ie.
        // 0 then we know that we have reached to the target
        // and break the loop
        console.log()
        // console.log(population)
        if (population[0].fitness <= 0) {
            isFound = true
            break
        }

        // Otherwise generate new offsprings for new generation
        let newGen = []
        // Perform Elitism, that mean 10% of fittest population
        // goes to the next generation
        let s = (10 * populationSize) / 100
        for (let i = 0; i < s; i++) newGen.push(population[i])

        // From 50% of fittest population, Individuals
        // will mate to produce offspring
        s = (90 * populationSize) / 100
        for (let i = 0; i < s; i++) {
            let ranNum = _genRanNum(0, 50)
            let parent1 = population[ranNum]
            ranNum = _genRanNum(0, 50)
            let parent2 = population[ranNum]
            let offspring = parent1.mate(parent2, genes)
            newGen.push(offspring)
        }
        population = newGen
        console.log('Generation: ' + generation)
        console.log('String: ' + population[0].chromosome)
        console.log('Fitness: ' + population[0].fitness)
        generation++
    }
    console.log('Generation: ' + generation)
    console.log('String: ' + population[0].chromosome)
    console.log('Fitness: ' + population[0].fitness)
} run(_genes, _target, _populationSize)
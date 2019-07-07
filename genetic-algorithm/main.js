const GeneticAlgorithm = require('./genetic.js')


function main() {
    const _populationSize = 100
    const _genes = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ 1234567890, .-;:_!\"#%&/()=?@${[]}"
    const _target = "Hello World!"
    // Run genetic algorithm
    let gen = new GeneticAlgorithm(_genes, _target, _populationSize)
    gen.run()
} main()

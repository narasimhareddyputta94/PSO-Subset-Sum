package org.example;

import java.util.Random;

/**
 * Represents a particle in the Particle Swarm Optimization (PSO) algorithm.
 * Each particle has a position and velocity in a binary search space,
 * and it maintains its own personal best position found so far.
 */
class Particle {
    private int[] position; // Current position of the particle (binary representation for subset selection)
    private int[] velocity; // Velocity of the particle (determines probability of position change)
    private int[] pBestPosition; // Best position found by this particle
    private double fitness; // Current fitness of the particle
    private double pBestFitness; // Best fitness achieved by this particle

    /**
     * Constructor to initialize a particle with a specified dimensionality.
     *
     * @param dimension The number of elements in the subset (same as the size of the problem).
     */
    public Particle(int dimension) {
        position = new int[dimension];
        velocity = new int[dimension];
        pBestPosition = new int[dimension];
        initializePosition();
        updatePBest();
    }

    /**
     * Initializes the particle's position randomly (binary values 0 or 1).
     * The initial personal best is set to the current position.
     */
    private void initializePosition() {
        for (int i = 0; i < position.length; i++) {
            position[i] = Math.random() < 0.5 ? 0 : 1; // Random binary position (0 or 1)
        }
        System.arraycopy(position, 0, pBestPosition, 0, position.length);
    }

    /**
     * Updates the velocity of the particle based on inertia, cognitive, and social components.
     * The velocity influences the probability of each bit flipping in the position array.
     *
     * @param gBestPosition The global best position found by the entire swarm.
     * @param inertiaWeight The inertia weight to control the impact of previous velocity.
     * @param cognitiveComponent The cognitive component weight (personal influence).
     * @param socialComponent The social component weight (swarm influence).
     */
    public void updateVelocity(int[] gBestPosition, double inertiaWeight, double cognitiveComponent, double socialComponent) {
        for (int i = 0; i < velocity.length; i++) {
            double cognitiveTerm = cognitiveComponent * Math.random() * (pBestPosition[i] - position[i]);
            double socialTerm = socialComponent * Math.random() * (gBestPosition[i] - position[i]);
            velocity[i] = (int) (inertiaWeight * velocity[i] + cognitiveTerm + socialTerm);

            // Bound the velocity to [-1, 1] for binary PSO
            velocity[i] = Math.min(1, Math.max(-1, velocity[i]));
        }
    }

    /**
     * Updates the position of the particle based on its velocity.
     * Uses a sigmoid function to determine the probability of each bit in the position array.
     */
    public void updatePosition() {
        for (int i = 0; i < position.length; i++) {
            if (Math.random() < sigmoid(velocity[i])) {
                position[i] = 1;
            } else {
                position[i] = 0;
            }
        }
    }

    /**
     * Evaluates the fitness of the particle based on the difference between
     * the sum of the subset represented by the position and the target sum.
     *
     * @param set The set of numbers to be used for subset selection.
     * @param targetSum The target sum for the subset.
     */
    public void evaluateFitness(int[] set, int targetSum) {
        int sum = 0;
        for (int i = 0; i < position.length; i++) {
            if (position[i] == 1) {
                sum += set[i];
            }
        }
        fitness = 1.0 / (1 + Math.abs(targetSum - sum)); // Fitness inversely proportional to the distance from the target
    }

    /**
     * Returns the current fitness of the particle.
     *
     * @return The fitness value.
     */
    public double getFitness() {
        return fitness;
    }

    /**
     * Returns the best fitness achieved by the particle (personal best).
     *
     * @return The personal best fitness value.
     */
    public double getPBestFitness() {
        return pBestFitness;
    }

    /**
     * Updates the personal best position and fitness if the current fitness is better.
     */
    public void updatePBest() {
        pBestFitness = fitness;
        pBestPosition = position.clone();
    }

    /**
     * Returns the current position of the particle.
     *
     * @return The current position as a binary array.
     */
    public int[] getPosition() {
        return position;
    }

    /**
     * Sigmoid function to scale velocity values to probabilities for binary PSO.
     *
     * @param x The input velocity value.
     * @return The probability of the position bit being 1.
     */
    private double sigmoid(int x) {
        return 1.0 / (1 + Math.exp(-x));
    }
}

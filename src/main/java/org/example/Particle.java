package org.example;

// Particle.java
import java.util.Random;

public class Particle {
    private int[] position; // Binary representation of subset inclusion
    private double[] velocity;
    private double fitness;
    private int[] pBestPosition;
    private double pBestFitness;

    public Particle(int numIntegers) {
        Random random = new Random();
        position = new int[numIntegers];
        velocity = new double[numIntegers];
        pBestPosition = new int[numIntegers];

        // Initialize position and velocity randomly
        for (int i = 0; i < numIntegers; i++) {
            position[i] = random.nextBoolean() ? 1 : 0;
            velocity[i] = random.nextDouble() * 2 - 1; // Velocity in range [-1, 1]
        }

        // Set initial fitness and personal best
        fitness = 0.0;
        pBestPosition = position.clone();
        pBestFitness = fitness;
    }

    // Evaluate fitness based on how close the subset sum is to the target sum
    public void evaluateFitness(int[] set, int targetSum) {
        int subsetSum = 0;
        for (int i = 0; i < position.length; i++) {
            if (position[i] == 1) subsetSum += set[i];
        }

        // Calculate fitness
        if (subsetSum <= targetSum) {
            fitness = (subsetSum / (double) targetSum) * 100;
        } else {
            fitness = (targetSum / (double) subsetSum) * 100;
        }
    }

    // Update particle velocity based on personal best, global best, and inertia
    public void updateVelocity(int[] gBestPosition, double inertiaWeight, double cognitiveComponent, double socialComponent) {
        Random random = new Random();
        for (int i = 0; i < velocity.length; i++) {
            double r1 = random.nextDouble();
            double r2 = random.nextDouble();
            velocity[i] = inertiaWeight * velocity[i]
                    + cognitiveComponent * r1 * (pBestPosition[i] - position[i])
                    + socialComponent * r2 * (gBestPosition[i] - position[i]);

            // Clamp velocity
            velocity[i] = Math.max(-1, Math.min(1, velocity[i]));
        }
    }

    // Update position based on velocity
    public void updatePosition() {
        Random random = new Random();
        for (int i = 0; i < position.length; i++) {
            double sigmoid = 1 / (1 + Math.exp(-velocity[i]));
            if (random.nextDouble() < sigmoid) {
                position[i] = 1 - position[i]; // Flip bit
            }
        }
    }

    // Update personal best position and fitness
    public void updatePBest() {
        pBestPosition = position.clone();
        pBestFitness = fitness;
    }

    // Getters
    public int[] getPosition() { return position; }
    public double getFitness() { return fitness; }
    public double getPBestFitness() { return pBestFitness; }
}

package org.example;

// Particle.java
import java.util.Random;

class Particle {
    private int[] position;
    private int[] velocity;
    private int[] pBestPosition;
    private double fitness;
    private double pBestFitness;

    public Particle(int dimension) {
        position = new int[dimension];
        velocity = new int[dimension];
        pBestPosition = new int[dimension];
        initializePosition();
        updatePBest();
    }

    private void initializePosition() {
        for (int i = 0; i < position.length; i++) {
            position[i] = Math.random() < 0.5 ? 0 : 1; // Randomly initialize position
        }
        System.arraycopy(position, 0, pBestPosition, 0, position.length);
    }

    public void updateVelocity(int[] gBestPosition, double inertiaWeight, double cognitiveComponent, double socialComponent) {
        for (int i = 0; i < velocity.length; i++) {
            double cognitiveTerm = cognitiveComponent * Math.random() * (pBestPosition[i] - position[i]);
            double socialTerm = socialComponent * Math.random() * (gBestPosition[i] - position[i]);
            velocity[i] = (int) (inertiaWeight * velocity[i] + cognitiveTerm + socialTerm);

            // Bound the velocity to [0, 1] for binary position updates
            velocity[i] = Math.min(1, Math.max(-1, velocity[i]));
        }
    }

    public void updatePosition() {
        for (int i = 0; i < position.length; i++) {
            if (Math.random() < sigmoid(velocity[i])) {
                position[i] = 1;
            } else {
                position[i] = 0;
            }
        }
    }

    public void evaluateFitness(int[] set, int targetSum) {
        int sum = 0;
        for (int i = 0; i < position.length; i++) {
            if (position[i] == 1) {
                sum += set[i];
            }
        }
        fitness = 1.0 / (1 + Math.abs(targetSum - sum)); // Fitness: closer sum to target, higher the fitness
    }

    public double getFitness() {
        return fitness;
    }

    public double getPBestFitness() {
        return pBestFitness;
    }

    public void updatePBest() {
        pBestFitness = fitness;
        pBestPosition = position.clone();
    }

    public int[] getPosition() {
        return position;
    }

    private double sigmoid(int x) {
        return 1.0 / (1 + Math.exp(-x));
    }
}
package org.example;

import java.util.*;

public class SubsetSumPSO {

    // PSO parameters
    private static final int SWARM_SIZE = 10;
    private static final int MAX_ITERATIONS = 50;
    private static final double INERTIA_WEIGHT = 0.729;
    private static final double COGNITIVE_COMPONENT = 1.49445;
    private static final double SOCIAL_COMPONENT = 1.49445;

    // Global best solution
    private static int[] gBestPosition;
    private static double gBestFitness = Double.NEGATIVE_INFINITY;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for input set and target sum
        System.out.println("Enter the integers in the set, separated by commas:");
        String[] setInput = scanner.nextLine().split(",\\s*");  // Split by comma and optional whitespace
        int[] set = Arrays.stream(setInput).mapToInt(Integer::parseInt).toArray();

        System.out.println("Enter the target sum:");
        int targetSum = scanner.nextInt();

        // Initialize swarm
        List<Particle> swarm = new ArrayList<>();
        for (int i = 0; i < SWARM_SIZE; i++) {
            Particle particle = new Particle(set.length);
            swarm.add(particle);
            updateGlobalBest(particle, set, targetSum);
        }

        // PSO iterations
        for (int iteration = 0; iteration < MAX_ITERATIONS; iteration++) {
            for (Particle particle : swarm) {
                particle.updateVelocity(gBestPosition, INERTIA_WEIGHT, COGNITIVE_COMPONENT, SOCIAL_COMPONENT);
                particle.updatePosition();
                particle.evaluateFitness(set, targetSum);

                // Update personal best
                if (particle.getFitness() > particle.getPBestFitness()) {
                    particle.updatePBest();
                }

                // Update global best
                updateGlobalBest(particle, set, targetSum);
            }

            // Early exit if an exact solution is found
            if (gBestFitness == 1.0) {
                System.out.println("Exact solution found!");
                break;
            }
        }

        // Output best solution
        System.out.println("Best solution: " + Arrays.toString(gBestPosition));
        System.out.println("Best fitness: " + gBestFitness);
    }

    // Update global best if a particle's fitness is better than the current global best
    private static void updateGlobalBest(Particle particle, int[] set, int targetSum) {
        if (particle.getFitness() > gBestFitness) {
            gBestFitness = particle.getFitness();
            gBestPosition = particle.getPosition().clone();
        }
    }
}

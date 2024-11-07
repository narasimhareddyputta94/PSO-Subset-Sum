package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A Particle Swarm Optimization (PSO) algorithm to solve the Subset Sum problem.
 * This algorithm aims to find a subset of numbers that sum to a target value.
 *
 * Dynamic parameters for the PSO algorithm, including inertia weight, cognitive, and social components,
 * are adjusted based on the input size for better performance.
 */
public class SubsetSumPSO {

    // Dynamic PSO parameters
    private static int SWARM_SIZE;
    private static double INERTIA_WEIGHT;
    private static double COGNITIVE_COMPONENT;
    private static double SOCIAL_COMPONENT;
    private static int MAX_ITERATIONS;

    // Global best solution across all particles in the swarm
    private static int[] gBestPosition;
    private static double gBestFitness = Double.NEGATIVE_INFINITY;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt for the list of numbers
        System.out.println("Enter the list of numbers (comma-separated), and press Enter on a blank line to finish:");
        List<Integer> numberList = new ArrayList<>();

        // Read and parse input until a blank line is entered
        while (true) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                break;
            }
            try {
                String[] input = line.split(",");
                for (String s : input) {
                    if (!s.trim().isEmpty()) {
                        numberList.add(Integer.parseInt(s.trim()));
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input detected. Please enter a valid list of integers.");
                return;
            }
        }

        if (numberList.isEmpty()) {
            System.out.println("The input list is empty. Please provide a valid list of numbers.");
            return;
        }

        // Prompt for the target sum value
        System.out.print("Enter the target sum: ");
        String targetSumInput = scanner.nextLine().trim();
        int targetSum;

        try {
            targetSum = Integer.parseInt(targetSumInput);
        } catch (NumberFormatException e) {
            System.out.println("Invalid target sum. Please enter an integer value.");
            return;
        }

        int[] set = numberList.stream().mapToInt(i -> i).toArray();
        int inputSize = numberList.size();

        // Adjust PSO parameters based on the size of the input list
        if (inputSize < 10) {
            SWARM_SIZE = 20;
            MAX_ITERATIONS = 100;
            INERTIA_WEIGHT = 0.9;
            COGNITIVE_COMPONENT = 2.0;
            SOCIAL_COMPONENT = 2.0;
        } else if (inputSize < 20) {
            SWARM_SIZE = 50;
            MAX_ITERATIONS = 200;
            INERTIA_WEIGHT = 0.8;
            COGNITIVE_COMPONENT = 1.5;
            SOCIAL_COMPONENT = 1.8;
        } else {
            SWARM_SIZE = 100;
            MAX_ITERATIONS = 500;
            INERTIA_WEIGHT = 0.7;
            COGNITIVE_COMPONENT = 1.2;
            SOCIAL_COMPONENT = 1.5;
        }

        // Print the chosen PSO parameters
        System.out.printf("Swarm size: %d, Max iterations: %d, Inertia weight: %.2f, Cognitive component: %.2f, Social component: %.2f%n",
                SWARM_SIZE, MAX_ITERATIONS, INERTIA_WEIGHT, COGNITIVE_COMPONENT, SOCIAL_COMPONENT);

        // Initialize the swarm with particles
        List<Particle> swarm = new ArrayList<>();
        for (int i = 0; i < SWARM_SIZE; i++) {
            Particle particle = new Particle(set.length);
            swarm.add(particle);
            updateGlobalBest(particle, set, targetSum);
        }

        // PSO main loop: update velocities, positions, and fitness values
        for (int iteration = 0; iteration < MAX_ITERATIONS; iteration++) {
            INERTIA_WEIGHT = 0.9 - (iteration / (double) MAX_ITERATIONS) * 0.5; // Gradually decrease inertia weight

            for (Particle particle : swarm) {
                particle.updateVelocity(gBestPosition, INERTIA_WEIGHT, COGNITIVE_COMPONENT, SOCIAL_COMPONENT);
                particle.updatePosition();
                particle.evaluateFitness(set, targetSum);

                // Update personal best for the particle if the current fitness is better
                if (particle.getFitness() > particle.getPBestFitness()) {
                    particle.updatePBest();
                }

                // Update the global best if this particle's fitness is the highest seen so far
                updateGlobalBest(particle, set, targetSum);
            }

            // Display the best solution for the current generation
            System.out.printf("Generation %d: Best solution so far: %s, Sum = %d, Fitness = %.2f%n",
                    iteration, formatSubset(set, gBestPosition), calculateSum(set, gBestPosition), gBestFitness);

            // Early exit if an exact solution is found
            if (gBestFitness == 1.0) {
                System.out.println("Exact solution found!");
                break;
            }
        }

        // Display the best solution found after all iterations
        System.out.printf("Best solution found: Subset: %s, Sum = %d, Fitness = %.2f%n",
                formatSubset(set, gBestPosition), calculateSum(set, gBestPosition), gBestFitness);
    }

    /**
     * Updates the global best solution if the current particle has a better fitness.
     *
     * @param particle  The particle being checked.
     * @param set       The set of numbers.
     * @param targetSum The target sum for the subset.
     */
    private static void updateGlobalBest(Particle particle, int[] set, int targetSum) {
        if (particle.getFitness() > gBestFitness) {
            gBestFitness = particle.getFitness();
            gBestPosition = particle.getPosition().clone();
        }
    }

    /**
     * Calculates the sum of the subset represented by the binary position array.
     *
     * @param set      The set of numbers.
     * @param position Binary array indicating the selected subset.
     * @return The sum of the subset.
     */
    private static int calculateSum(int[] set, int[] position) {
        int sum = 0;
        for (int i = 0; i < position.length; i++) {
            if (position[i] == 1) {
                sum += set[i];
            }
        }
        return sum;
    }

    /**
     * Formats the subset for display based on the binary position array.
     *
     * @param set      The set of numbers.
     * @param position Binary array indicating the selected subset.
     * @return String representation of the subset.
     */
    private static String formatSubset(int[] set, int[] position) {
        List<Integer> subset = new ArrayList<>();
        for (int i = 0; i < position.length; i++) {
            if (position[i] == 1) {
                subset.add(set[i]);
            }
        }
        return subset.toString();
    }
}

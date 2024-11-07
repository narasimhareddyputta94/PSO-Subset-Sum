package org.example;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class SubsetSumPSO {

    // Dynamic PSO parameters
    private static int SWARM_SIZE;
    private static double INERTIA_WEIGHT;
    private static double COGNITIVE_COMPONENT;
    private static double SOCIAL_COMPONENT;
    private static int MAX_ITERATIONS;

    // Global best solution
    private static int[] gBestPosition;
    private static double gBestFitness = Double.NEGATIVE_INFINITY;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: List of numbers
        System.out.println("Enter the list of numbers (comma-separated), and press Enter on a blank line to finish:");
        List<Integer> numberList = new ArrayList<>();
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

        // Input: Target sum
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

        // Adjust PSO parameters dynamically based on input size and target sum
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

        System.out.printf("Swarm size: %d, Max iterations: %d, Inertia weight: %.2f, Cognitive component: %.2f, Social component: %.2f%n",
                SWARM_SIZE, MAX_ITERATIONS, INERTIA_WEIGHT, COGNITIVE_COMPONENT, SOCIAL_COMPONENT);

        // Initialize swarm
        List<Particle> swarm = new ArrayList<>();
        for (int i = 0; i < SWARM_SIZE; i++) {
            Particle particle = new Particle(set.length);
            swarm.add(particle);
            updateGlobalBest(particle, set, targetSum);
        }

        // PSO iterations with dynamic inertia weight decay
        for (int iteration = 0; iteration < MAX_ITERATIONS; iteration++) {
            INERTIA_WEIGHT = 0.9 - (iteration / (double) MAX_ITERATIONS) * 0.5; // Decrease inertia over time

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

            // Display current generation's best solution
            System.out.printf("Generation %d: Best solution so far: %s, Sum = %d, Fitness = %.2f%n",
                    iteration, formatSubset(set, gBestPosition), calculateSum(set, gBestPosition), gBestFitness);

            // Early exit if an exact solution is found
            if (gBestFitness == 1.0) {
                System.out.println("Exact solution found!");
                break;
            }
        }

        // Display the best solution found
        System.out.printf("Best solution found: Subset: %s, Sum = %d, Fitness = %.2f%n",
                formatSubset(set, gBestPosition), calculateSum(set, gBestPosition), gBestFitness);
    }

    // Update global best if a particle's fitness is better than the current global best
    private static void updateGlobalBest(Particle particle, int[] set, int targetSum) {
        if (particle.getFitness() > gBestFitness) {
            gBestFitness = particle.getFitness();
            gBestPosition = particle.getPosition().clone();
        }
    }

    // Calculate the sum of the subset represented by the binary position array
    private static int calculateSum(int[] set, int[] position) {
        int sum = 0;
        for (int i = 0; i < position.length; i++) {
            if (position[i] == 1) {
                sum += set[i];
            }
        }
        return sum;
    }

    // Format the subset for display
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

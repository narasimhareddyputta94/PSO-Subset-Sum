
---

# Subset Sum Problem using Particle Swarm Optimization (PSO)

## Overview

This project provides a solution to the subset sum problem using Particle Swarm Optimization (PSO). The subset sum problem is a classic computational problem that involves finding a subset of numbers from a given list that sums up to a target value. The PSO algorithm, commonly used for optimization tasks, is employed here to approach this problem effectively.

### Problem Statement

Given a list of integers and a target sum, determine if there exists a subset of the integers that adds up to the target. If such a subset exists, the program outputs the subset and its sum; otherwise, it attempts to find the subset with the sum closest to the target.

### What is Particle Swarm Optimization (PSO)?

PSO is a population-based optimization technique inspired by the social behavior of birds flocking or fish schooling. Each potential solution, or "particle," adjusts its position based on its own experience and the experience of neighboring particles, converging over iterations towards an optimal solution.

---

## Features

- **Dynamic Parameter Tuning**: The PSO parameters (swarm size, inertia weight, cognitive and social components, and max iterations) adjust automatically based on the problem's complexity.
- **Real-Time Solution Updates**: Displays the current best subset and its sum in each generation.
- **Early Stopping**: If an exact solution matching the target sum is found, the program exits early.
- **Binary Representation**: Each particle represents a binary solution, indicating inclusion or exclusion of each element in the subset.

---

## Project Structure

- **Main Class**: `SubsetSumPSO.java` – Handles user input, initializes the PSO, and manages the optimization process.
- **Particle Class**: `Particle.java` – Represents an individual particle in the swarm, handling position, velocity updates, and fitness evaluation.

---

## Getting Started

### Prerequisites

Ensure you have Java installed. You can check your Java version with:

```bash
java -version
```

### Cloning the Repository

Clone this repository using Git:

```bash
git clone <repository-url>
cd SubsetSumPSO
```

### Running the Program

Compile and run the program using:

```bash
javac org/example/*.java
java org.example.SubsetSumPSO
```

### User Inputs

1. **List of Numbers**: Provide a comma-separated list of integers. Press Enter on a blank line to finish.
2. **Target Sum**: Specify the target sum that the algorithm will try to achieve with a subset of the list.

---

## Algorithm Details

### Particle Swarm Optimization Workflow

1. **Initialization**: Each particle in the swarm is randomly assigned a binary position vector, indicating inclusion/exclusion of elements in the subset.
2. **Velocity and Position Update**: Each particle’s velocity and position are updated in each generation based on the inertia weight, cognitive component, and social component.
    - **Inertia Weight**: Controls the influence of previous velocity.
    - **Cognitive Component**: Encourages particles to move towards their own best position.
    - **Social Component**: Encourages particles to move towards the global best position.
3. **Fitness Evaluation**: The fitness of each particle is calculated based on how close its subset sum is to the target sum. The closer to the target sum, the higher the fitness.
4. **Global Best Update**: The algorithm maintains a global best solution and updates it if a particle finds a closer subset to the target sum.

### Dynamic Parameter Tuning

The PSO parameters adjust based on the size of the input list to improve the efficiency and performance of the algorithm:
- **Small Input (<10)**: Small swarm size, fewer iterations.
- **Medium Input (10-20)**: Moderate swarm size, more iterations.
- **Large Input (>20)**: Large swarm size, maximum iterations.

---

## Example Usage

Input:
- List of Numbers: `3, 34, 4, 12, 5, 2`
- Target Sum: `9`

Output:
```
Swarm size: 20, Max iterations: 100, Inertia weight: 0.90, Cognitive component: 2.00, Social component: 2.00
Generation 0: Best solution so far: [4, 5], Sum = 9, Fitness = 1.00
Exact solution found!
Best solution found: Subset: [4, 5], Sum = 9, Fitness = 1.00
```

---

## Class Descriptions

- **`SubsetSumPSO`**: Main class for executing the PSO algorithm. It initializes the swarm, iterates over generations, and updates the global best solution.
- **`Particle`**: Represents a particle in the swarm, managing its position, velocity, and fitness. Contains methods for updating velocity, updating position, and evaluating fitness.

### Key Methods

- **`updateVelocity`**: Updates each particle's velocity based on the global best position.
- **`updatePosition`**: Updates each particle's position in the solution space based on its velocity.
- **`evaluateFitness`**: Calculates how close a particle's subset sum is to the target, impacting the particle's fitness score.
- **`updateGlobalBest`**: Updates the global best solution if a better subset is found.

---

## Customization

The PSO parameters (swarm size, inertia weight, cognitive component, and social component) can be manually adjusted for experimentation.

---

## Contributing

Contributions are welcome! For any improvements or suggestions, feel free to fork the repository and submit a pull request.

---

## License

This project is licensed under the MIT License.

Here's an informative README for your `SubsetSumPSO` project. It outlines the purpose, features, setup, usage, and provides a clear understanding of Particle Swarm Optimization (PSO) as applied to the subset sum problem.

---

# Subset Sum Problem using Particle Swarm Optimization (PSO)

## Overview

This project provides a solution to the subset sum problem using Particle Swarm Optimization (PSO). The subset sum problem is a classic computational problem that involves finding a subset of numbers from a given list that sums up to a target value. The PSO algorithm, commonly used for optimization tasks, is employed here to approach this problem effectively.

### Problem Statement

Given a list of integers and a target sum, determine if there exists a subset of the integers that adds up to the target. If such a subset exists, the program outputs the subset and its sum; otherwise, it attempts to find the subset with the sum closest to the target.

### What is Particle Swarm Optimization (PSO)?

PSO is a population-based optimization technique inspired by the social behavior of birds flocking or fish schooling. Each potential solution, or "particle," adjusts its position based on its own experience and the experience of neighboring particles, converging over iterations towards an optimal solution.

---

## Features

- **Dynamic Parameter Tuning**: The PSO parameters (swarm size, inertia weight, cognitive and social components, and max iterations) adjust automatically based on the problem's complexity.
- **Real-Time Solution Updates**: Displays the current best subset and its sum in each generation.
- **Early Stopping**: If an exact solution matching the target sum is found, the program exits early.
- **Binary Representation**: Each particle represents a binary solution, indicating inclusion or exclusion of each element in the subset.

---

## Project Structure

- **Main Class**: `SubsetSumPSO.java` – Handles user input, initializes the PSO, and manages the optimization process.
- **Particle Class**: `Particle.java` – Represents an individual particle in the swarm, handling position, velocity updates, and fitness evaluation.

---

## Getting Started

### Prerequisites

Ensure you have Java installed. You can check your Java version with:

```bash
java -version
```

### Cloning the Repository

Clone this repository using Git:

```bash
git clone <repository-url>
cd SubsetSumPSO
```

### Running the Program

Compile and run the program using:

```bash
javac org/example/*.java
java org.example.SubsetSumPSO
```

### User Inputs

1. **List of Numbers**: Provide a comma-separated list of integers. Press Enter on a blank line to finish.
2. **Target Sum**: Specify the target sum that the algorithm will try to achieve with a subset of the list.

---

## Algorithm Details

### Particle Swarm Optimization Workflow

1. **Initialization**: Each particle in the swarm is randomly assigned a binary position vector, indicating inclusion/exclusion of elements in the subset.
2. **Velocity and Position Update**: Each particle’s velocity and position are updated in each generation based on the inertia weight, cognitive component, and social component.
    - **Inertia Weight**: Controls the influence of previous velocity.
    - **Cognitive Component**: Encourages particles to move towards their own best position.
    - **Social Component**: Encourages particles to move towards the global best position.
3. **Fitness Evaluation**: The fitness of each particle is calculated based on how close its subset sum is to the target sum. The closer to the target sum, the higher the fitness.
4. **Global Best Update**: The algorithm maintains a global best solution and updates it if a particle finds a closer subset to the target sum.

### Dynamic Parameter Tuning

The PSO parameters adjust based on the size of the input list to improve the efficiency and performance of the algorithm:
- **Small Input (<10)**: Small swarm size, fewer iterations.
- **Medium Input (10-20)**: Moderate swarm size, more iterations.
- **Large Input (>20)**: Large swarm size, maximum iterations.

---

## Example Usage

Input:
- List of Numbers: `3, 34, 4, 12, 5, 2`
- Target Sum: `9`

Output:
```
Swarm size: 20, Max iterations: 100, Inertia weight: 0.90, Cognitive component: 2.00, Social component: 2.00
Generation 0: Best solution so far: [4, 5], Sum = 9, Fitness = 1.00
Exact solution found!
Best solution found: Subset: [4, 5], Sum = 9, Fitness = 1.00
```

---

## Class Descriptions

- **`SubsetSumPSO`**: Main class for executing the PSO algorithm. It initializes the swarm, iterates over generations, and updates the global best solution.
- **`Particle`**: Represents a particle in the swarm, managing its position, velocity, and fitness. Contains methods for updating velocity, updating position, and evaluating fitness.

### Key Methods

- **`updateVelocity`**: Updates each particle's velocity based on the global best position.
- **`updatePosition`**: Updates each particle's position in the solution space based on its velocity.
- **`evaluateFitness`**: Calculates how close a particle's subset sum is to the target, impacting the particle's fitness score.
- **`updateGlobalBest`**: Updates the global best solution if a better subset is found.

---

## Customization

The PSO parameters (swarm size, inertia weight, cognitive component, and social component) can be manually adjusted for experimentation.

---

## Contributing

Contributions are welcome! For any improvements or suggestions, feel free to fork the repository and submit a pull request.

---

## License

This project is licensed under the MIT License.


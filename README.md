# Student Cafeteria Simulation

## Description
This project is a multithreaded simulation of a student cafeteria, where clients queue for food, pay at the cashier, and find seats at shared tables. The simulation models real-life interactions using Java threads and synchronization mechanisms.

## System Components

### 1. Client (Thread)
- Represents a student visiting the cafeteria.
- Joins the shortest queue at a food-serving point.
- After receiving food, queues at the shortest available cashier.
- Proceeds to a table, finds an available seat, eats, and then leaves.
- Repeats the process periodically.

### 2. Food Serving Point (Worker Thread)
- Two points available for serving food.
- Each serving point has a queue.
- Workers serve clients in a random time interval.

### 3. Cashier (Worker Thread)
- Four cashier counters available for payment.
- Clients join the shortest available queue.
- Some cashiers may be disabled, preventing new clients from joining their queue.
- Cashiers process payments at a random time interval.

### 4. Tables (Shared Resource)
- Two long tables with `2*n` seats each.
- Clients attempt to find a free seat sequentially.
- After eating, clients leave the table.

## Technologies Used
- **Programming Language**: Java
- **Concurrency Mechanisms**: Java Threads, Synchronization (Locks, Semaphores, etc.)

## How to Run
1. Compile and start the simulation.
2. Observe client behavior as they navigate queues, payments, and seating.
3. Adjust parameters to test different cafeteria load scenarios.

## Future Improvements
- Implement friend grouping for seating arrangements.
- Enhance visualization of queues and table occupancy.
- Introduce more dynamic cashier availability logic.

## License
This project is open-source and available under the MIT License.


# Student Canteen Simulation

![openart-image_np8L0FjF_1743518830231_raw](https://github.com/user-attachments/assets/c0c91e17-6fbe-4c7c-92f8-6a151cab3c46)

## Description
This project is a multithreaded simulation of a student cafeteria, where clients queue for food, pay at the cashier, and find seats at shared tables. The simulation models real-life interactions using Java threads and synchronization mechanisms.

## Interface

![Screenshot from 2025-04-01 16-24-19](https://github.com/user-attachments/assets/e9856422-c40a-4a6b-9a8b-068a7f2ef64c)


![Screenshot from 2025-04-01 16-24-28](https://github.com/user-attachments/assets/d15d53d3-f2b7-4347-98fc-2ac4d1517d7e)



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
- One long table with `2*n` seats.
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



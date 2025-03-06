package pl.edu.pwr.student.utrybukhouski.lab5;


import javafx.scene.layout.Pane;

import static pl.edu.pwr.student.utrybukhouski.lab5.Objects.*;

class Student implements Runnable {
    private final int id;
    private Pane window;
    private static int counter = 1;

    public Student() {
        this.id = counter++;
        window = createStudentCup(id);
    }

    public int getId() {
        return id;
    }

    @Override
    public void run() {
        System.out.println("Student with ID: " + id + " is created and added to the queue.");
    }


    public Pane getWindow() {
        return window;
    }
}

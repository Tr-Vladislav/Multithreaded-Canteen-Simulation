package pl.edu.pwr.student.utrybukhouski.lab5;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import static pl.edu.pwr.student.utrybukhouski.lab5.Objects.*;

import java.util.concurrent.BlockingQueue;

class Distribution implements Runnable {
    private final BlockingQueue<Student> queue;
    private final BlockingQueue<Student> paymentQueue;
    private final Pane window;
    private final Pane queueSection;
    private final Pane distributionSection;
    private int id;

    public Distribution(int id, BlockingQueue<Student> queue, BlockingQueue<Student> paymentQueue, Pane queueSection, Pane distributionSection) {
        this.queue = queue;
        this.paymentQueue = paymentQueue;
        this.id = id;
        window = createFoodDistributionWindow();
        this.queueSection = queueSection;
        this.distributionSection = distributionSection;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Student student = queue.take();

                Platform.runLater(() -> {
                    moveStudentFrom(queueSection);
                });

                System.out.println("Student with ID: " + student.getId() + " is being served at checkout " + id );
                Thread.sleep((long) (1000+3000*Math.random()));
                System.out.println("Student with ID: " + student.getId() + " is ready to pay.");
                Platform.runLater(() -> {
                    moveStudentTo(distributionSection);
                });
                paymentQueue.put(student);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    public Pane getWindow() {
        return window;
    }
    public void moveStudentFrom(Pane queueSection){
        for(Node node : queueSection.getChildren()) {
            if (node instanceof StackPane) {
                StackPane student = (StackPane) node;
                queueSection.getChildren().remove(student);
                window.getChildren().add(student);
                break;
            }
        }
    }
    public void moveStudentTo(Pane distributionSection){
        for(Node node : window.getChildren()) {
            if (node instanceof StackPane) {
                StackPane student = (StackPane) node;
                window.getChildren().remove(student);
                for (Node innerNode : distributionSection.getChildren()) {
                    if (innerNode instanceof VBox) {
                        ((VBox) innerNode).getChildren().add(student);
                        break;
                    }
                }
                break;
            }
        }
    }
}

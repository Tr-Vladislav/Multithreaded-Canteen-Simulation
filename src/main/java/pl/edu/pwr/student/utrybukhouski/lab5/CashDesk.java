package pl.edu.pwr.student.utrybukhouski.lab5;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.concurrent.BlockingQueue;
import static pl.edu.pwr.student.utrybukhouski.lab5.Objects.*;

public class CashDesk implements Runnable{
    private final BlockingQueue<Student> queue;
    private final BlockingQueue<Student> placeQueue;
    private final Pane window;
    private final VBox boxPaymentQueue;
    private final Pane cashierSection;
    private int id;

    public CashDesk(int id, BlockingQueue<Student> queue, BlockingQueue<Student> placeQueue, VBox boxPaymentQueue, Pane cashierSection) {
        this.queue = queue;
        this.placeQueue = placeQueue;
        this.id = id;
        window = createCashRegister();
        this.boxPaymentQueue = boxPaymentQueue;
        this.cashierSection = cashierSection;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Student student = queue.take();
                Platform.runLater(() -> {
                    moveStudentFrom(boxPaymentQueue);
                });
                System.out.println("Student with ID: " + student.getId() + " is being served at checkout " + id );
                Thread.sleep((long) (1000+3000*Math.random()));
                System.out.println("Student with ID: " + student.getId() + " is going to the table");
                Platform.runLater(() -> {
                    moveStudentTo(cashierSection);
                });
                placeQueue.put(student);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    public int getId() {
        return id;
    }
    public Pane getWindow() {
        return window;
    }
    public void moveStudentFrom(VBox boxPaymentQueue){
        for(Node node : boxPaymentQueue.getChildren()) {
            if (node instanceof StackPane) {
                StackPane student = (StackPane) node;
                boxPaymentQueue.getChildren().remove(student);
                window.getChildren().add(student);
                break;
            }
        }
    }
    public void moveStudentTo(Pane hallSection){
        for(Node node : window.getChildren()) {
            if (node instanceof StackPane) {
                StackPane student = (StackPane) node;
                window.getChildren().remove(student);
                for (Node innerNode : hallSection.getChildren()) {
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

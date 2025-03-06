package pl.edu.pwr.student.utrybukhouski.lab5;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.concurrent.BlockingQueue;
import static pl.edu.pwr.student.utrybukhouski.lab5.Objects.*;

public class Place implements Runnable{
    private final int id;
    private final BlockingQueue<Student> queue;
    private final VBox boxPlaceQueue;
    private Pane window;

    public Place(int id, BlockingQueue<Student> queue, VBox boxPlaceQueue) {
        this.id = id;
        this.queue = queue;
        this.window = createSeat();
        this.boxPlaceQueue = boxPlaceQueue;
    }
    public int getId() {
        return id;
    }
    @Override
    public void run() {
        try {
            while (true) {
                Student student = queue.take();

                Platform.runLater(() -> {

                    moveStudentFrom(boxPlaceQueue);
                });
                System.out.println("Student with ID: " + student.getId() + " at table " + id );
                Thread.sleep((long) (1000+4000*Math.random()));
                System.out.println("Student with ID: " + student.getId() + " has left.");
                Platform.runLater(() -> {
                    moveStudentTo();
                });
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Работа Distribution завершена.");
        }
    }
    public Pane getWindow() {
        return window;
    }
    public void moveStudentFrom(VBox boxPlaceQueue){
        for(Node node : boxPlaceQueue.getChildren()) {
            if (node instanceof StackPane) {
                StackPane student = (StackPane) node;
                boxPlaceQueue.getChildren().remove(student);
                window.getChildren().add(student);
                break;
            }
        }
    }
    public void moveStudentTo(){
        for(Node node : window.getChildren()) {
            if (node instanceof StackPane) {
                StackPane student = (StackPane) node;
                window.getChildren().remove(student);
                break;
            }
        }
    }
}

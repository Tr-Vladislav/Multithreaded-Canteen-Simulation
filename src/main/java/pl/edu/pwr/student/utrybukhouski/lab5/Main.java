package pl.edu.pwr.student.utrybukhouski.lab5;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import static pl.edu.pwr.student.utrybukhouski.lab5.Objects.*;

public class Main  extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){

        HBox root = new HBox();
        root.setSpacing(13);
        root.setPadding(new Insets(13));

        Pane queueSection = createSection("Queue");
        Pane distributionSection = createSection("Distribution");
        Pane cashierSection = createSection("Cashier");
        Pane hallSection = createDiningHallSection("Hall");

        HBox.setHgrow(queueSection, Priority.ALWAYS);
        HBox.setHgrow(distributionSection, Priority.ALWAYS);
        HBox.setHgrow(cashierSection, Priority.ALWAYS);
        HBox.setHgrow(hallSection, Priority.ALWAYS);


        BlockingQueue<Student> distributionQueue = new LinkedBlockingQueue<>();
        BlockingQueue<Student> paymentQueue = new LinkedBlockingQueue<>();
        BlockingQueue<Student> placeQueue = new LinkedBlockingQueue<>();

        Button newStudentButton = new Button("New Student");
        newStudentButton.setOnAction(e -> {
            createStudent( distributionQueue, queueSection);
        });
        int numberOfDistributions = 3;
        ExecutorService service = Executors.newFixedThreadPool(numberOfDistributions);

        int numberOfCashDesks = 1;
        ExecutorService serviceCash = Executors.newFixedThreadPool(numberOfCashDesks);

        int numberOfPlaces = 10;
        ExecutorService servicePlaces = Executors.newFixedThreadPool(numberOfPlaces);

        int numberOfStudents = 20;



        for (int i = 0; i < numberOfDistributions; i++) {
            Distribution distribution = new Distribution(i, distributionQueue, paymentQueue, queueSection, distributionSection);
            service.submit(distribution);
            distributionSection.getChildren().add(distribution.getWindow());
        }
        VBox boxPaymentQueue = new VBox();
        boxPaymentQueue.setAlignment(Pos.TOP_CENTER);
        boxPaymentQueue.setSpacing(10);
        distributionSection.getChildren().add(boxPaymentQueue);

        for (int i = 0; i < numberOfCashDesks; i++) {
            CashDesk cashDesk = new CashDesk(i, paymentQueue, placeQueue, boxPaymentQueue, cashierSection);
            serviceCash.submit(cashDesk);
            cashierSection.getChildren().add(cashDesk.getWindow());
        }
        VBox boxPlaceQueue = new VBox();
        boxPlaceQueue.setAlignment(Pos.TOP_CENTER);
        boxPaymentQueue.setSpacing(10);
        cashierSection.getChildren().add(boxPlaceQueue);

        for (int i = 0; i < numberOfPlaces/2; i++) {
            for(int j = 0; j < 2; j++ ) {
                Place place = new Place(i, placeQueue, boxPlaceQueue);
                servicePlaces.submit(place);
                for(Node node: hallSection.getChildren()) {
                    if(node instanceof HBox) {
                        for(Node innerNode: ((HBox) node).getChildren()) {
                            if(innerNode instanceof GridPane) {
                                ((GridPane) innerNode).add(place.getWindow(),j,i);
                                break;
                            }
                        }
                    }
                }
            }
        }
        hallSection.getChildren().add(newStudentButton);

        for (int i = 1; i <= numberOfStudents; i++) {
            createStudent(distributionQueue,queueSection);
        }

        root.getChildren().addAll(queueSection, distributionSection, cashierSection, hallSection);

        Scene scene = new Scene(root, 1040, 780);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cafeteria Layout");
        primaryStage.show();
        service.shutdown();

    }
    public void createStudent(BlockingQueue<Student> distributionQueue,Pane queueSection){
        Student student = new Student();
        distributionQueue.add(student);
        queueSection.getChildren().add(student.getWindow());
        student.run();
    }
}
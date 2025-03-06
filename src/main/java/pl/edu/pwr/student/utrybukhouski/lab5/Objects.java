package pl.edu.pwr.student.utrybukhouski.lab5;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Objects {
    public static StackPane createStudentCup(int studentNumber) {
        Circle circle = new Circle(40, generateRandomColor());
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(2);

        Text text = new Text("Student " + studentNumber);
        text.setFill(Color.WHITE);
        text.setFont(Font.font("Arial", 14));

        StackPane cup = new StackPane(circle, text);
        cup.setAlignment(Pos.CENTER);

        return cup;
    }

    public static StackPane createFoodDistributionWindow() {
        Rectangle window = new Rectangle(200, 120, Color.LIGHTGRAY);
        window.setArcWidth(15);
        window.setArcHeight(15);
        window.setStroke(Color.DARKGRAY);
        window.setStrokeWidth(2);

        Label label = new Label("Food Distribution");
        label.setFont(Font.font("Arial", 16));
        label.setStyle("-fx-font-weight: bold;");

        Rectangle clientArea = new Rectangle(80, 80, Color.BEIGE);
        clientArea.setStroke(Color.BLACK);
        clientArea.setStrokeWidth(2);

        Circle light = new Circle(10, Color.RED);
        light.setStroke(Color.BLACK);

        VBox layout = new VBox(10, label, clientArea, light);
        layout.setAlignment(Pos.CENTER);

        StackPane distributionWindow = new StackPane(window, layout);
        return distributionWindow;
    }
    public static StackPane createCashRegister() {
        Rectangle register = new Rectangle(200, 120, Color.BEIGE);
        register.setArcWidth(15);
        register.setArcHeight(15);
        register.setStroke(Color.DARKGRAY);
        register.setStrokeWidth(2);

        Label label = new Label("Cashier");
        label.setFont(Font.font("Arial", 16));
        label.setStyle("-fx-font-weight: bold;");

        Rectangle clientArea = new Rectangle(80, 80, Color.LIGHTGRAY);
        clientArea.setStroke(Color.BLACK);
        clientArea.setStrokeWidth(2);

        Circle light = new Circle(10, Color.GREEN);
        light.setStroke(Color.BLACK);

        VBox layout = new VBox(10, label, clientArea, light);
        layout.setAlignment(Pos.CENTER);

        StackPane cashier = new StackPane(register, layout);
        return cashier;
    }

    public static Pane createSection(String title) {
        VBox section = new VBox();
        section.setAlignment(Pos.TOP_CENTER);
        section.setSpacing(10);
        section.setStyle("-fx-border-color: black; -fx-border-width: 1.3; -fx-padding: 13;");

        Label label = new Label(title);
        label.setStyle("-fx-font-size: 23px; -fx-font-weight: bold;");
        label.setAlignment(Pos.CENTER);
        label.setMaxWidth(Double.MAX_VALUE);

        section.getChildren().add(label);

        return section;
    }

    public static Pane createDiningHallSection(String title) {
        VBox section = new VBox();

        Label label = new Label(title);
        label.setStyle("-fx-font-size: 23px; -fx-font-weight: bold;");
        label.setLayoutX(10);
        label.layoutBoundsProperty().addListener((obs, oldVal, newVal) -> {
            label.setLayoutX((section.getWidth() - newVal.getWidth()) / 2);
        });
        section.widthProperty().addListener((obs, oldVal, newVal) -> {
            label.setLayoutX((newVal.doubleValue() - label.getWidth()) / 2);
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(13));
        grid.setHgap(13);
        grid.setVgap(13);


        grid.setMaxWidth(Double.MAX_VALUE);
        grid.setMaxHeight(Double.MAX_VALUE);
        HBox gridContainer = new HBox(grid);
        gridContainer.setStyle("-fx-alignment: center;");

        section.getChildren().addAll(label, gridContainer);
        section.setStyle("-fx-border-color: black; -fx-border-width: 1.3; -fx-padding: 13;");

        return section;
    }
    public static Pane createSeat(){
        Pane seat = new Pane();
        seat.setPrefSize(80, 80);
        seat.setStyle("-fx-border-color: black; -fx-border-width: 1.3;");
        return seat;
    }
    public static Color generateRandomColor() {
        int red = (int) (Math.random() * 256);
        int green = (int) (Math.random() * 256);
        int blue = (int) (Math.random() * 256);

        return Color.rgb(red, green, blue);
    }
}

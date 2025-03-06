module pl.edu.pwr.student.utrybukhouski.lab5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens pl.edu.pwr.student.utrybukhouski.lab5 to javafx.fxml;
    exports pl.edu.pwr.student.utrybukhouski.lab5;
}
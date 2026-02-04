module org.example.pixel_tra {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens org.example.pixel_tra to javafx.fxml;
    exports org.example.pixel_tra;
}
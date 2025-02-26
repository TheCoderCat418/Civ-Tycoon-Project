module civtycoon {
    exports com.thecodercat418.civtycoon;

    requires javafx.base;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens com.thecodercat418.civtycoon to javafx.fxml;
}

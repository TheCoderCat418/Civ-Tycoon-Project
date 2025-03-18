module civtycoon {
    exports com.thecodercat418.civtycoon;

    requires transitive javafx.base;
    requires transitive javafx.fxml;
    requires transitive javafx.graphics;
    requires transitive javafx.controls;
    requires transitive java.desktop;

    opens com.thecodercat418.civtycoon to javafx.fxml;
}

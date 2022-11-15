module com.slprojects.chessgame {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.slprojects.chessgame to javafx.fxml;
    exports com.slprojects.chessgame;
}
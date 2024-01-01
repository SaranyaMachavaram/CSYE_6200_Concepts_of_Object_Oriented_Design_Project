module org.openjfx.Insurance {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    
    
    opens org.openjfx.Insurance to javafx.fxml;
    exports org.openjfx.Insurance;
}

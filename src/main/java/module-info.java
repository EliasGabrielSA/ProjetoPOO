module start.projetopadrao2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    

    opens start.projetopadrao2 to javafx.fxml;
    exports start.projetopadrao2;
    
    opens controller to javafx.fxml;
    exports controller;
    
    opens model to javafx.fxml;
    exports model;
}

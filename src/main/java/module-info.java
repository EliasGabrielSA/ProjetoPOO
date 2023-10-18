module start.colecaojavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens start.colecaojavafx to javafx.fxml;
    exports start.colecaojavafx;
    
    opens controller to javafx.fxml;
    exports controller;
    
    opens model to javafx.fxml;
    exports model;
}

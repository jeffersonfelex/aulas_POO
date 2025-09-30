module com.jefferson.org {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.jefferson.org.principal to javafx.fxml;
    exports com.jefferson.org.principal;
}
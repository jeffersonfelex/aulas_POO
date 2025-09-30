package com.jefferson.org.principal;

import com.jefferson.org.utils.PathFXML;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App - Controle de Estoque
 */
public class Main extends Application 
{

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException 
    {
        Parent root = FXMLLoader.load(PathFXML.getPath("TelaPrincipal.fxml"));

        scene = new Scene(root, 600, 400);

        stage.setTitle("Controle de Estoque");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) 
    {
        launch();
    }

    public static void setRoot(String string) 
    {
        throw new UnsupportedOperationException("Unimplemented method 'setRoot'");
    }
}
package com.jefferson.org;

import java.io.IOException;

import com.jefferson.org.principal.Main;

import javafx.fxml.FXML;

public class PrimaryController 
{

    @FXML
    private void switchToSecondary() throws IOException 
    {
        Main.setRoot("secondary");
    }
}

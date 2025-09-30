package com.jefferson.org;

import java.io.IOException;

import com.jefferson.org.principal.Main;

import javafx.fxml.FXML;

public class SecondaryController 
{

    @FXML
    private void switchToPrimary() throws IOException 
    {
        Main.setRoot("primary");
    }
}
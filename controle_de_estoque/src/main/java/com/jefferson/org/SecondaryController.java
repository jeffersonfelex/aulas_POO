package com.jefferson.org;

import javafx.fxml.FXML;

import java.io.IOException;

import com.jefferson.org.principal.Main;

public class SecondaryController 
{

    @FXML
    private void switchToPrimary() throws IOException 
    {
        Main.setRoot("primary");
    }
}
package com.locacao.view;

import com.locacao.dao.ModeloDAO;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CadastroModeloTela {

    private final ModeloDAO modeloDAO = new ModeloDAO();
    private final Runnable aoSalvarCallback;

    public CadastroModeloTela(Runnable aoSalvarCallback) {
        this.aoSalvarCallback = aoSalvarCallback;
    }

    public void mostrar() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL); // Bloqueia a janela de trás
        stage.setTitle("Novo Modelo / Marca");

        TextField txtMarca = new TextField();
        txtMarca.setPromptText("Ex: Chevrolet");

        TextField txtModelo = new TextField();
        txtModelo.setPromptText("Ex: Onix 1.0");

        TextField txtAno = new TextField();
        txtAno.setPromptText("Ex: 2024");

        Button btnSalvar = new Button("Cadastrar");
        btnSalvar.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        btnSalvar.setOnAction(e -> {
            try {
                String marca = txtMarca.getText();
                String modelo = txtModelo.getText();
                int ano = Integer.parseInt(txtAno.getText());

                if (marca.isEmpty() || modelo.isEmpty()) {
                    mostrarAlerta("Preencha Marca e Modelo!");
                    return;
                }

                modeloDAO.salvarModelo(marca, modelo, ano);

                mostrarSucesso("Modelo cadastrado com sucesso!");
                stage.close();

                if (aoSalvarCallback != null) aoSalvarCallback.run();

            } catch (NumberFormatException ex) {
                mostrarAlerta("O ano deve ser um número!");
            } catch (Exception ex) {
                mostrarAlerta("Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.add(new Label("Marca:"), 0, 0);
        form.add(txtMarca, 1, 0);
        form.add(new Label("Modelo:"), 0, 1);
        form.add(txtModelo, 1, 1);
        form.add(new Label("Ano Fab.:"), 0, 2);
        form.add(txtAno, 1, 2);

        VBox root = new VBox(15, new Label("Cadastrar Novo Carro"), form, btnSalvar);
        root.setPadding(new Insets(20));

        stage.setScene(new Scene(root, 300, 250));
        stage.showAndWait();
    }

    private void mostrarAlerta(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void mostrarSucesso(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
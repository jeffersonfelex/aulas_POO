package com.jefferson.org.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import com.jefferson.org.model.Produto;

import java.net.URL;
import java.util.ResourceBundle;

public class TelaPrincipalController implements Initializable 
{

    @FXML
    private TableView<Produto> tableViewProdutos;

    @FXML
    private TableColumn<Produto, String> tableColumnNome;

    @FXML
    private TableColumn<Produto, Double> tableColumnPreco;

    @FXML
    private TableColumn<Produto, Integer> tableColumnQuantidade;

    @FXML
    private Label labelValorTotal;

    private ObservableList<Produto> listaProdutos;

    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        tableColumnQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

        carregarProdutos();
        calcularValorTotalEstoque();
    }


    private void carregarProdutos() 
    {
        Produto[] produtosArray =
        {
                new Produto("Notebook", 3500.00, 5),
                new Produto("Mouse", 80.00, 20),
                new Produto("Teclado", 150.00, 10),
                new Produto("Monitor 24\"", 1200.00, 7),
                new Produto("Monitor 27\"", 1600.00, 4),
                new Produto("Impressora", 900.00, 3),
                new Produto("Headset", 250.00, 15),
                new Produto("Cadeira Gamer", 1100.00, 2),
                new Produto("Webcam", 200.00, 8),
                new Produto("HD Externo 1TB", 400.00, 6)

        };

        listaProdutos = FXCollections.observableArrayList(produtosArray);
        tableViewProdutos.setItems(listaProdutos);
    }


    private void calcularValorTotalEstoque() 
    {
        double total = 0;
        for (Produto p : listaProdutos) {
            total += p.getPreco() * p.getQuantidade();
        }
        labelValorTotal.setText(String.format("R$ %.2f", total));
    }
}
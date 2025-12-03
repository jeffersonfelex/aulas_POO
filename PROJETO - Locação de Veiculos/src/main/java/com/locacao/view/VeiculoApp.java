package com.locacao.view;

import com.locacao.dao.ModeloDAO;
import com.locacao.dao.VeiculoDAO;
import com.locacao.model.Modelo;
import com.locacao.model.StatusVeiculo;
import com.locacao.model.Veiculo;
import com.locacao.util.HibernateUtil;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class VeiculoApp extends Application {

    private TableView<Veiculo> tabela = new TableView<>();
    private TextField txtPlaca = new TextField();
    private TextField txtCor = new TextField();
    private TextField txtAno = new TextField();
    private TextField txtKm = new TextField();
    private ComboBox<StatusVeiculo> cmbStatus = new ComboBox<>();
    private ComboBox<Modelo> cmbModelo = new ComboBox<>();

    private Veiculo veiculoSelecionado = null;

    private VeiculoDAO veiculoDAO = new VeiculoDAO();
    private ModeloDAO modeloDAO = new ModeloDAO();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // --- 1. CONFIGURAÇÃO DA TABELA ---
        configurarTabela();

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(10));

        cmbStatus.getItems().setAll(StatusVeiculo.values());

        carregarModelos();

        form.add(new Label("Placa:"), 0, 0);
        form.add(txtPlaca, 1, 0);

        form.add(new Label("Cor:"), 0, 1);
        form.add(txtCor, 1, 1);

        form.add(new Label("Ano:"), 2, 0);
        form.add(txtAno, 3, 0);

        form.add(new Label("Km:"), 2, 1);
        form.add(txtKm, 3, 1);

        form.add(new Label("Modelo:"), 0, 2);

        HBox boxModelo = new HBox(5);
        Button btnNovoModelo = new Button("+");
        btnNovoModelo.setStyle("-fx-font-weight: bold; -fx-background-color: #2196F3; -fx-text-fill: white;");

        btnNovoModelo.setOnAction(e -> {
            new CadastroModeloTela(() -> carregarModelos()).mostrar();
        });

        boxModelo.getChildren().addAll(cmbModelo, btnNovoModelo);
        form.add(boxModelo, 1, 2);

        form.add(new Label("Status:"), 2, 2);
        form.add(cmbStatus, 3, 2);

        Button btnSalvar = new Button("Salvar");
        Button btnExcluir = new Button("Excluir");
        Button btnLimpar = new Button("Limpar / Novo");

        btnSalvar.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        btnExcluir.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");

        HBox botoes = new HBox(10, btnSalvar, btnExcluir, btnLimpar);
        botoes.setPadding(new Insets(10));

        btnSalvar.setOnAction(e -> salvarVeiculo());
        btnExcluir.setOnAction(e -> excluirVeiculo());
        btnLimpar.setOnAction(e -> limparFormulario());

        tabela.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                preencherFormulario(newVal);
            }
        });

        VBox layoutSuperior = new VBox(form, botoes);
        BorderPane root = new BorderPane();
        root.setTop(layoutSuperior);
        root.setCenter(tabela);

        atualizarTabela();

        Scene scene = new Scene(root, 900, 600);
        stage.setTitle("Gestão de Veículos");
        stage.setScene(scene);
        stage.show();
    }

    private void configurarTabela() {
        TableColumn<Veiculo, Long> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Veiculo, String> colModelo = new TableColumn<>("Modelo");
        colModelo.setCellValueFactory(cell -> new SimpleStringProperty(
                cell.getValue().getModelo().getMarca().getNome() + " " + cell.getValue().getModelo().getNome()
        ));
        colModelo.setMinWidth(150);

        TableColumn<Veiculo, String> colPlaca = new TableColumn<>("Placa");
        colPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));

        TableColumn<Veiculo, String> colCor = new TableColumn<>("Cor");
        colCor.setCellValueFactory(new PropertyValueFactory<>("cor"));

        TableColumn<Veiculo, Integer> colAno = new TableColumn<>("Ano");
        colAno.setCellValueFactory(new PropertyValueFactory<>("anoModelo"));

        TableColumn<Veiculo, String> colStatus = new TableColumn<>("Status");
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tabela.getColumns().addAll(colId, colModelo, colPlaca, colCor, colAno, colStatus);
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void carregarModelos() {
        try {
            var modelos = modeloDAO.listarTodos();
            cmbModelo.getItems().setAll(modelos);

            cmbModelo.setConverter(new StringConverter<Modelo>() {
                @Override
                public String toString(Modelo m) {
                    return (m == null) ? "" : m.getMarca().getNome() + " - " + m.getNome();
                }
                @Override
                public Modelo fromString(String s) { return null; }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void salvarVeiculo() {
        try {
            if (txtPlaca.getText().isEmpty() || cmbModelo.getValue() == null) {
                mostrarAlerta("Erro", "Placa e Modelo são obrigatórios!");
                return;
            }

            Veiculo veiculo = (veiculoSelecionado == null) ? new Veiculo() : veiculoSelecionado;

            veiculo.setPlaca(txtPlaca.getText());
            veiculo.setCor(txtCor.getText());
            veiculo.setAnoModelo(Integer.parseInt(txtAno.getText()));
            veiculo.setQuilometragem(Integer.parseInt(txtKm.getText()));
            veiculo.setStatus(cmbStatus.getValue());
            veiculo.setModelo(cmbModelo.getValue());

            veiculoDAO.salvar(veiculo);

            mostrarAlerta("Sucesso", "Veículo salvo com sucesso!");
            limparFormulario();
            atualizarTabela();

        } catch (NumberFormatException e) {
            mostrarAlerta("Erro", "Ano e Km devem ser números!");
        } catch (Exception e) {
            mostrarAlerta("Erro", "Erro ao salvar: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void excluirVeiculo() {
        if (veiculoSelecionado == null) {
            mostrarAlerta("Aviso", "Selecione um veículo na tabela para excluir.");
            return;
        }

        try {
            veiculoDAO.deletar(veiculoSelecionado);
            mostrarAlerta("Sucesso", "Veículo excluído!");
            limparFormulario();
            atualizarTabela();
        } catch (Exception e) {
            mostrarAlerta("Erro", "Não foi possível excluir (pode estar alugado).");
        }
    }

    private void preencherFormulario(Veiculo v) {
        this.veiculoSelecionado = v;
        txtPlaca.setText(v.getPlaca());
        txtCor.setText(v.getCor());
        txtAno.setText(String.valueOf(v.getAnoModelo()));
        txtKm.setText(String.valueOf(v.getQuilometragem()));
        cmbStatus.setValue(v.getStatus());

        for(Modelo m : cmbModelo.getItems()) {
            if(m.getId().equals(v.getModelo().getId())) {
                cmbModelo.setValue(m);
                break;
            }
        }
    }

    private void limparFormulario() {
        veiculoSelecionado = null;
        txtPlaca.clear();
        txtCor.clear();
        txtAno.clear();
        txtKm.clear();
        cmbStatus.setValue(StatusVeiculo.DISPONIVEL);
        cmbModelo.getSelectionModel().clearSelection();
        tabela.getSelectionModel().clearSelection();
    }

    private void atualizarTabela() {
        var lista = veiculoDAO.listarTodos();
        tabela.setItems(FXCollections.observableArrayList(lista));
    }

    private void mostrarAlerta(String titulo, String msg) {
        Alert alert = new Alert(titulo.equals("Erro") ? Alert.AlertType.ERROR : Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    @Override
    public void stop() {
        HibernateUtil.shutdown();
    }
}
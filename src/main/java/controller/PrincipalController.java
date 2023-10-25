/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import start.colecaojavafx.App;

/**
 *
 * @author biels
 */
public class PrincipalController {

    @FXML
    private Button btnAdicionar;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnEstatisticas;
    @FXML
    private Button btnFiltrar;
    @FXML
    private Button btnExcluir;
    @FXML
    private GridPane gridPane;

    @FXML
    private void btnAdicionarOnAction(ActionEvent event) throws IOException {
        App.setRoot("Cadastro");
    }

    @FXML
    private void btnEditarOnAction(ActionEvent event) {
    }

    @FXML
    private void btnExcluirOnAction(ActionEvent event) {
    }

    @FXML
    private void btnEstatisticasOnAction(ActionEvent event) {
    }

    @FXML
    private void btnFiltrarOnAction(ActionEvent event) {
    }
    
}

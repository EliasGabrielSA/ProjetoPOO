/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import start.colecaojavafx.App;

/**
 * FXML Controller class
 *
 * @author lab1
 */
public class PrincipalController implements Initializable {

    @FXML
    private Button btnAdicionar;
    @FXML
    private Button btnRemover;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnEstatisticas;
    @FXML
    private TextField txtFiltro;
    @FXML
    private Button btnFiltrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnAdicionarOnAction(ActionEvent event) throws IOException {
        App.setRoot("Cadastro");
    }

    @FXML
    private void btnRemoverOnAction(ActionEvent event) {
    }

    @FXML
    private void btnEditarOnAction(ActionEvent event) {
    }

    @FXML
    private void btnEstatisticasOnAction(ActionEvent event) {
    }

    @FXML
    private void btnFiltrarOnAction(ActionEvent event) {
    }
    
}

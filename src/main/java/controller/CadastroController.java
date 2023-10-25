/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import start.colecaojavafx.App;

/**
 * FXML Controller class
 *
 * @author biels
 */
public class CadastroController implements Initializable {

    @FXML
    private Button btnAdicionar;
    @FXML
    private Button btnVoltar;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtTipo;
    @FXML
    private TextField txtQtd;
    @FXML
    private TextField txtAno;
    @FXML
    private TextField txtDuracao;
    @FXML
    private TextField txtUrl;
    @FXML
    private ImageView imgCapa;
    @FXML
    private Button btnProcurar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void btnAdicionarOnAction(ActionEvent event) {
    }

    @FXML
    private void btnVoltarOnAction(ActionEvent event) throws IOException {
        App.setRoot("Principal");
    }

    @FXML
    private void btnProcurarOnAction(ActionEvent event) {
        System.out.println("printou");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecione uma imagem");
        fileChooser.setInitialDirectory(new File("C:\\"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPEG (*.jpeg)", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG (*.png)", "*png"), new FileChooser.ExtensionFilter("All images", "*jpg","*.png"));
        File selectedFile = fileChooser.showOpenDialog(btnProcurar.getScene().getWindow());
        if(selectedFile != null){
            txtUrl.setText(selectedFile.getAbsolutePath());
            Image image = new Image(selectedFile.toURI().toString());
            imgCapa.setImage(image);
        }else{
            System.out.println("Nenhum arquivo foi selecionado");
        }
    }   
}

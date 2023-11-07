/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.File;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import model.Disco;
import model.dao.DaoFactory;
import model.dao.DiscoDaoJdbc;
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
    @FXML
    private CheckBox checkBoxOuviu;

    private static Disco discoSelecionado;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (discoSelecionado != null){
            txtNome.setText(discoSelecionado.getNome());
            txtTipo.setText(discoSelecionado.getTipo());
            txtQtd.setText(Integer.toString(discoSelecionado.getFaixas()));
            txtAno.setText(Integer.toString(discoSelecionado.getAno()));
            txtDuracao.setText(Integer.toString(discoSelecionado.getDuracao()));
            txtUrl.setText(discoSelecionado.getImagem());
            
            if (discoSelecionado.getVisualizou().equals("Já ouviu")) {
                checkBoxOuviu.setSelected(true);
            } else {
                checkBoxOuviu.setSelected(false);
            }
            
            if (txtUrl != null) {
                Image image = new Image("file:///" + txtUrl.getText().replace("\\", "/"));
                imgCapa.setImage(image);
            }
        }
    }    

    @FXML
    private void btnAdicionarOnAction(ActionEvent event) throws Exception {
        Disco disco = new Disco();
        disco.setNome(txtNome.getText());
        disco.setTipo(txtTipo.getText());
        disco.setFaixas(parseInt(txtQtd.getText()));
        disco.setAno(parseInt(txtAno.getText()));
        disco.setDuracao(parseInt(txtDuracao.getText()));
        disco.setImagem(txtUrl.getText());
        if(checkBoxOuviu.isSelected()){
            disco.setVisualizou("Já ouviu");
        } else{
            disco.setVisualizou("Não ouviu");
        }
        
        DiscoDaoJdbc dao = DaoFactory.novoDiscoDao();
        
        if (discoSelecionado == null){
            dao.incluir(disco);
        } else{
            disco.setId(discoSelecionado.getId());
            dao.editar(disco);
            discoSelecionado = null;
        }
        
        App.setRoot("Principal");
    }

    @FXML
    private void btnVoltarOnAction(ActionEvent event) throws IOException {
        App.setRoot("Principal");
    }

    @FXML
    private void btnProcurarOnAction(ActionEvent event) {
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
    
    public static Disco getDiscoSelecionado() {
        return discoSelecionado;
    }

    public static void setDiscoSelecionado(Disco discoSelecionado) {
        CadastroController.discoSelecionado = discoSelecionado;
    }
}

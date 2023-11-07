/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Disco;
import model.dao.DaoFactory;
import model.dao.DiscoDaoJdbc;
import start.colecaojavafx.App;

/**
 *
 * @author biels
 */
public class PrincipalController implements Initializable {

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
    private TableView<Disco> tblDiscos;
    @FXML
    private TableColumn<Disco, String> tblColNome;
    @FXML
    private TableColumn<Disco, String> tblColVisualizado;
    @FXML
    private TableColumn<Disco, String> tblColTipo;
    
    private List<Disco> listaDisco;
    private ObservableList<Disco> observableListDisco;
    @FXML
    private TextField txtFiltro;
    
    @FXML
    private EstatisticasController estatisticasController;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        carregarDisco("");
    }    

    @FXML
    private void btnAdicionarOnAction(ActionEvent event) throws IOException {
        App.setRoot("Cadastro");
    }

    //VERIFICAR
    
    @FXML
    private void btnEditarOnAction(ActionEvent event) throws IOException {
        Disco discoSelecionado = tblDiscos.selectionModelProperty().getValue().getSelectedItem();
        
        if(discoSelecionado != null){
            CadastroController.setDiscoSelecionado(discoSelecionado);
            App.setRoot("Cadastro");
        }
        
        carregarDisco("");
    }

    @FXML
    private void btnExcluirOnAction(ActionEvent event) throws Exception {
        Disco discoSelecionado = tblDiscos.selectionModelProperty().getValue().getSelectedItem();
        
        if(discoSelecionado != null){
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Exclusão");
            alert.setContentText("Tem certeza que deseja excluir o disco selecionado ?");
            alert.setHeaderText("");
            Optional<ButtonType> result = alert.showAndWait();
            
            DiscoDaoJdbc dao = DaoFactory.novoDiscoDao();
            
            if(result.get() == ButtonType.OK){
                dao.excluir(discoSelecionado);
                carregarDisco("");
            }else{
                alert.close();
            }
        }
    }
    
    //FAZER
    @FXML
    private void btnEstatisticasOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Estatisticas.fxml"));
        Parent telaEstatisticas = loader.load();

        estatisticasController = loader.getController();
        if (observableListDisco != null && !observableListDisco.isEmpty()) {
            estatisticasController.setLista(observableListDisco);
            App.setRoot("Estatisticas");
        } else {
            System.out.println("A lista de discos está vazia ou nula.");
        }
    }

    @FXML
    private void btnFiltrarOnAction(ActionEvent event) {
        carregarDisco(txtFiltro.getText());
    }
    
    private void carregarDisco(String param){
        tblColNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tblColVisualizado.setCellValueFactory(new PropertyValueFactory<>("visualizou"));
        tblColTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        try{
            DiscoDaoJdbc dao = DaoFactory.novoDiscoDao();
            listaDisco = dao.listar(param);
        } catch (Exception e){
            e.printStackTrace();
        }

        observableListDisco = FXCollections.observableArrayList(listaDisco);
        tblDiscos.setItems(observableListDisco);
    }

    public List<Disco> getListaDisco() {
        return listaDisco;
    }

    public ObservableList<Disco> getObservableListDisco() {
        return observableListDisco;
    }
}

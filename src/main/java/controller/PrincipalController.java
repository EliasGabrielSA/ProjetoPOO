package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Disco;
import model.dao.DaoFactory;
import model.dao.DiscoDaoJdbc;
import start.colecaojavafx.App;

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
    private ImageView ImageViewModal;
    
    @FXML
    private Label labelNome;
    
    @FXML
    private Label labelAno;
    
    @FXML
    private Label labelTipo;
    
    @FXML
    private Label labelVisualizado;
    
    @FXML
    private Label labelDuracao;
    
    @FXML
    private Label labelQuantidadeFaixas;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblDiscos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            modalOnAction(null, newSelection);
        });

        carregarDisco("");
    }   

    @FXML
    private void btnAdicionarOnAction(ActionEvent event) throws IOException {
        App.setRoot("Cadastro");
    }

    @FXML
    private void btnEditarOnAction(ActionEvent event) throws IOException {
        Disco discoSelecionado = tblDiscos.selectionModelProperty().getValue().getSelectedItem();
        
        if(discoSelecionado != null){
            CadastroController.setDiscoSelecionado(discoSelecionado);
            App.setRoot("Cadastro");
        } else{
            Alert alert = new Alert(AlertType.INFORMATION); 
            alert.setTitle("Erro");
            alert.setHeaderText("");
            alert.setContentText("Selecione um item para editar.");
            alert.show();
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
        } else{
            Alert alert = new Alert(AlertType.INFORMATION); 
            alert.setTitle("Erro");
            alert.setHeaderText("");
            alert.setContentText("Selecione um item para excluir.");
            alert.show();
        }
    }
    
    @FXML
    private void btnEstatisticasOnAction(ActionEvent event) throws IOException {
        if(listaDisco != null && !listaDisco.isEmpty()){
            App.setRoot("Estatisticas");
        } else{
            Alert alert = new Alert(AlertType.INFORMATION); 
            alert.setTitle("Erro");
            alert.setHeaderText("");
            alert.setContentText("A lista de discos está vazia.");
            alert.show();
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

    @FXML
    private void modalOnAction(ActionEvent event, Disco discoSelecionado) {
        if (discoSelecionado != null) {
            String caminhoImagem = discoSelecionado.getImagem().replace("\\", File.separator);

            if (caminhoImagem.startsWith("." + File.separator)) {
                caminhoImagem = System.getProperty("user.dir") + caminhoImagem.substring(1);
            }

            Image image = new Image("file:" + caminhoImagem);
            ImageViewModal.setImage(image);

            labelNome.setText(discoSelecionado.getNome());
            labelAno.setText("Ano: " + discoSelecionado.getAno());
            labelTipo.setText("Tipo: " + discoSelecionado.getTipo());
            labelVisualizado.setText("Status: " + discoSelecionado.getVisualizou());
            labelDuracao.setText("Duração: " + discoSelecionado.getDuracao());
            labelQuantidadeFaixas.setText("Faixas: " + discoSelecionado.getFaixas());
        }
    }
}

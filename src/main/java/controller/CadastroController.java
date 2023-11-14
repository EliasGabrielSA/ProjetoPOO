package controller;

import java.io.File;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (discoSelecionado != null) {
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
                String caminhoImagem = txtUrl.getText().replace("\\", File.separator);

                if (caminhoImagem.startsWith("." + File.separator)) {
                    caminhoImagem = System.getProperty("user.dir") + caminhoImagem.substring(1);
                }

                Image image = new Image("file:" + caminhoImagem);
                imgCapa.setImage(image);
            }

            btnAdicionar.setText("Editar");
        } else {
            limparDados();
        }
    }

    
    @FXML
    private void btnAdicionarOnAction(ActionEvent event) throws Exception {
        try {
            Disco disco = new Disco();
            disco.setNome(txtNome.getText());
            disco.setTipo(txtTipo.getText().toUpperCase());
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
            limparDados();
        } catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro na entrada de dados");
            alert.setContentText("Certifique-se de digitar valores inteiros para a quantidade, ano e duração.");
            alert.showAndWait();
        }
    }

    @FXML
    private void btnVoltarOnAction(ActionEvent event) throws Exception {
        App.setRoot("Principal");
        limparDados();
    }

    @FXML
    private void btnProcurarOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecione uma imagem");
        fileChooser.setInitialDirectory(new File("./assets"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Todas imagens", "*jpg", "*png", "*jpeg"),
                new FileChooser.ExtensionFilter("PNG (*.png)", "*png"),
                new FileChooser.ExtensionFilter("JPEG (*.jpeg)", "*jpeg"),
                new FileChooser.ExtensionFilter("JPG (*.jpg)", "*.jpg")
        );
        File selectedFile = fileChooser.showOpenDialog(btnProcurar.getScene().getWindow());

        if (selectedFile != null) {
            String separator = File.separator;
            String caminhoImagem = selectedFile.getAbsolutePath();
            if (caminhoImagem.startsWith(System.getProperty("user.dir"))) {
                caminhoImagem = "." + separator + caminhoImagem.substring(System.getProperty("user.dir").length() + 1);
            }

            txtUrl.setText(caminhoImagem);
            Image image = new Image(new File(caminhoImagem).toURI().toString());
            imgCapa.setImage(image);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Aviso");
            alert.setHeaderText("");
            alert.setContentText("Nenhuma imagem foi selecionada.");
            alert.show();
            System.out.println("Nenhum arquivo foi selecionado");
    }
}

    private String getRelativePath(File file) {
        Path currentPath = Paths.get("").toAbsolutePath();
        Path absolutePath = file.toPath().toAbsolutePath();
        Path relativePath = currentPath.relativize(absolutePath);
        return relativePath.toString();
    }   

    private void limparDados(){
        discoSelecionado = null;
        txtNome.setText("");
        txtTipo.setText("");
        txtQtd.setText("");
        txtAno.setText("");
        txtDuracao.setText("");
        txtUrl.setText("");
        checkBoxOuviu.setSelected(false);
        btnAdicionar.setText("Adicionar");
    }
    
    public static Disco getDiscoSelecionado() {
        return discoSelecionado;
    }

    public static void setDiscoSelecionado(Disco discoSelecionado) {
        CadastroController.discoSelecionado = discoSelecionado;
    }
}

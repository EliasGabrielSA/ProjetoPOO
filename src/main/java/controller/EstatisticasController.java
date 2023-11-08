package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import model.Disco;
import model.dao.DaoFactory;
import model.dao.DiscoDaoJdbc;
import start.colecaojavafx.App;

public class EstatisticasController implements Initializable {
    
    private int contsim;
    private int contnao;
    private List<Disco> listaDisco;

    @FXML
    private PieChart estOuviu;
    @FXML
    private Button btnVoltar;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        estOuviu.getData().clear(); // Limpe os dados antigos
        
        carregarLista("");
    }

    @FXML
    private void onActionVoltar(ActionEvent event) throws IOException {
        App.setRoot("Principal");
    }
    
    private void carregarLista(String param){
        try{
            DiscoDaoJdbc dao = DaoFactory.novoDiscoDao();
            listaDisco = dao.listar(param);
        } catch (Exception e){
            e.printStackTrace();
        }
        
        atualizarGrafico();
    }

    private void atualizarGrafico() {
        
        for (int i = 0; i < listaDisco.size(); i++) {
            if ("Já ouviu".equals(listaDisco.get(i).getVisualizou())) {
                contsim++;
            } else{
                contnao++;
            }
        }
        
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
            new PieChart.Data("Já ouviu", contsim),
            new PieChart.Data("Não ouviu", contnao)
        );
        
        pieChartData.forEach(data ->
            data.nameProperty().bind(
                Bindings.concat(data.getName(), ": ", data.pieValueProperty())
            )
        );

        estOuviu.setData(pieChartData);
    }
}
package controller;

import java.io.IOException;
import java.net.URL;
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
import start.colecaojavafx.App;

public class EstatisticasController implements Initializable {
    
    private int contsim;
    private int contnao;
    private ObservableList<Disco> lista;

    @FXML
    private PieChart estOuviu;
    @FXML
    private Button btnVoltar;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicialização do gráfico
        contaEstatistica();
    }

    @FXML
    private void onActionVoltar(ActionEvent event) throws IOException {
        App.setRoot("Principal");
    }
    
    public void setLista(ObservableList<Disco> lista){
        this.lista = lista;
    }

    public void contaEstatistica() {
        contsim = 0;
        contnao = 0;
        
        for (int i = 0; i < lista.size(); i++) {
            if ("Já ouviu".equals(lista.get(i).getVisualizou())) {
                contsim++;
            } else{
                contnao++;
            }
        }
        
        atualizarGrafico();
    }

    private void atualizarGrafico() {
        estOuviu.getData().clear(); // Limpe os dados antigos
        
        int teste1 = contsim;
        int teste2 = contnao;
        
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
            new PieChart.Data("Já ouviu", teste1),
            new PieChart.Data("Não ouviu", teste2)
        );
        
        pieChartData.forEach(data ->
            data.nameProperty().bind(
                Bindings.concat(data.getName(), ": ", data.pieValueProperty())
            )
        );

        estOuviu.setData(pieChartData);
    }       
}
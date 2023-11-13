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
    private int contcd;
    private int contdvd;
    private int contvinil;
    private int contoutros;
    private List<Disco> listaDisco;

    @FXML
    private PieChart estOuviu;
    @FXML
    private Button btnVoltar;
    @FXML
    private PieChart estTipo;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        estOuviu.getData().clear(); // Limpe os dados antigos
        estTipo.getData().clear();
        
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
        
        for (int i = 0; i < listaDisco.size(); i++) {
            if ("DVD".equals(listaDisco.get(i).getTipo())) {
                contdvd++;
            } 
            else if ("CD".equals(listaDisco.get(i).getTipo())){
                contcd++;
            }
            else if("VINIL".equals(listaDisco.get(i).getTipo())){
                contvinil++;
            }
            else{
                contoutros++;
            }
        }
        
        
        //Grafico de ouviu
        ObservableList<PieChart.Data> dataOuviu = FXCollections.observableArrayList(
            new PieChart.Data("Já ouviu", contsim),
            new PieChart.Data("Não ouviu", contnao)
        );
        
        dataOuviu.forEach(data ->
            data.nameProperty().bind(
                Bindings.concat(data.getName(), ": ", data.pieValueProperty())
            )
        );

        estOuviu.setData(dataOuviu);
        
        //Grafico de Tipo
        ObservableList<PieChart.Data> dataTipo = FXCollections.observableArrayList(
            new PieChart.Data("CD", contcd),
            new PieChart.Data("DVD", contdvd),
            new PieChart.Data("VINIL", contvinil),
            new PieChart.Data("Outros", contoutros)
        );
        
        dataTipo.forEach(data ->
            data.nameProperty().bind(
                Bindings.concat(data.getName(), ": ", data.pieValueProperty())
            )
        );

        estTipo.setData(dataTipo);
    }
}
package com.moscowsubway.subwaypathclient;

import com.moscowsubway.restcl.Station;
import com.moscowsubway.restcl.SubwayClient;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLController implements Initializable {
    
    @FXML
    private ComboBox srcCombo;
    
    @FXML
    private ComboBox dstCombo;
    
    @FXML
    private TableView tablePath;
    
    @FXML
    private TableColumn nameColumn;
    
    @FXML
    private TableColumn lineNumColumn;
    
    @FXML
    private Label statusLabel;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        ArrayList<Station> stations = new ArrayList<>();
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        lineNumColumn.setCellValueFactory(new PropertyValueFactory<>("lineNum"));
        tablePath.getItems().clear();
        if (srcCombo.getValue() != null && dstCombo.getValue() != null) {
            Station srcSt = (Station) srcCombo.getValue();
            Station dstSt = (Station) dstCombo.getValue();
            try {
                stations = new SubwayClient().getPath(srcSt.getId(), dstSt.getId());
                statusLabel.setText("");
            } catch (Exception e) {
                statusLabel.setText("Connection error!");
            }
            for(int i = 0; i < stations.size(); i++) 
                tablePath.getItems().add(stations.get(i));
            
        }
    }
    
    @FXML
    private void handleUpdateAction(ActionEvent event) {
        getStartData();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getStartData();
    }    
    
    public void getStartData() {
        ArrayList<Station> stations = new ArrayList<>();
        try {
            stations = new SubwayClient().getAllStations();
            statusLabel.setText("");
        } catch (Exception e) {
            statusLabel.setText("Connection error!");
        }
        srcCombo.getItems().addAll(stations);
        dstCombo.getItems().addAll(stations);
    }
}

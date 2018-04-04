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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Callback;

public class FXMLController implements Initializable {
    
    @FXML
    private ComboBox<Station> srcCombo;
    
    @FXML
    private ComboBox<Station> dstCombo;
    
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
        if (FxUtil.getComboBoxValue(srcCombo) != null && FxUtil.getComboBoxValue(srcCombo) != null) {
            Station srcSt = FxUtil.getComboBoxValue(srcCombo);
            Station dstSt = FxUtil.getComboBoxValue(dstCombo);
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
        this.srcCombo.getItems().addAll(stations);
        this.dstCombo.getItems().addAll(stations);
        
        this.srcCombo.setCellFactory(
        new Callback<ListView<Station>, ListCell<Station>>() {
            @Override public ListCell<Station> call(ListView<Station> param) {
                final ListCell<Station> cell = new ListCell<Station>() {
                    {
                        super.setPrefWidth(100);
                    }    
                    @Override public void updateItem(Station item, 
                        boolean empty) {
                            super.updateItem(item, empty);
                            if (item != null) {
                                setText(item.toString());    
                                setTextFill(getLineColor(item.getLineNum()));
                            } else {
                                setText(null);
                            }
                        }
            };
            return cell;
            }
        });
        
        this.dstCombo.setCellFactory(
        new Callback<ListView<Station>, ListCell<Station>>() {
            @Override public ListCell<Station> call(ListView<Station> param) {
                final ListCell<Station> cell = new ListCell<Station>() {
                    {
                        super.setPrefWidth(100);
                    }    
                    @Override public void updateItem(Station item, 
                        boolean empty) {
                            super.updateItem(item, empty);
                            if (item != null) {
                                setText(item.toString());
                                setTextFill(getLineColor(item.getLineNum()));
                            } else {
                                setText(null);
                            }
                        }
            };
            return cell;
            }
        });
        
        FxUtil.autoCompleteComboBoxPlus(this.srcCombo, (typedText, itemToCompare) -> itemToCompare.getName().toLowerCase().contains(typedText.toLowerCase()) || itemToCompare.getName().toString().equals(typedText));
        FxUtil.autoCompleteComboBoxPlus(this.dstCombo, (typedText, itemToCompare) -> itemToCompare.getName().toLowerCase().contains(typedText.toLowerCase()) || itemToCompare.getName().toString().equals(typedText));
    }
    
    private Color getLineColor(int lineNum) {
        if (lineNum == 1)
            return Color.RED;
        else if (lineNum == 2)
            return Color.GREEN;
        else if (lineNum == 3)
            return Color.BLUE;
        else if (lineNum == 4)
            return Color.CORNFLOWERBLUE;
        else if (lineNum == 5)
            return Color.BROWN;
        else if (lineNum == 6)
            return Color.ORANGE;
        else if (lineNum == 7)
            return Color.BLUEVIOLET;
        else if (lineNum == 8)
            return Color.GOLD;
        else if (lineNum == 9)
            return Color.GOLD;
        else if (lineNum == 10)
            return Color.GRAY;
        else if (lineNum == 11)
            return Color.CHARTREUSE;
        else if (lineNum == 12)
            return Color.AQUAMARINE;
        else if (lineNum == 13)
            return Color.AQUAMARINE;
        else if (lineNum == 14)
            return Color.AQUA;
        else if (lineNum == 16)
            return Color.PINK;
        else
            return Color.BLACK;
    }
}

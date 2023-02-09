package graphic;

import enums.TypyProcesu;
import enums.enumPozice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;


public class ControlPanelHBox extends HBox {


    public ControlPanelHBox() {
        setSpacing(12);
        alignmentProperty();
        setAlignment(Pos.CENTER);
        setPrefHeight(70);
        setMaxSize(500,100);
    }

    public void newButton(String nazev, EventHandler<ActionEvent> handler) {
        Button btn = new Button(nazev);
        btn.setPrefSize(80,25);
        btn.setOnAction(handler);
        getChildren().add(btn);

    }
//    public void newComboBox(String nazev, EventHandler<ActionEvent> handler){
//        ObservableList<TypyProcesu> list= FXCollections.observableArrayList(TypyProcesu.values());
//        ComboBox<TypyProcesu> comboBox = new ComboBox<>(list);
//        comboBox.setPrefSize(100,25);
//        comboBox.setPromptText(nazev);
//        comboBox.setOnAction(handler);
//        getChildren().add(comboBox);
//    }
//    public void newComboBoxPozice(String nazev, EventHandler<ActionEvent> handler){
//        ObservableList<enumPozice> list= FXCollections.observableArrayList(enumPozice.values());
//        ComboBox<enumPozice> comboBox = new ComboBox<>(list);
//        comboBox.setPrefSize(100,25);
//        comboBox.setPromptText(nazev);
//        comboBox.setOnAction(handler);
//        getChildren().add(comboBox);
//    }

    public void newLabel(String nazev){
        Label label = new Label(nazev);
        getChildren().add(label);
    }
    public TextField newField(String nazev){
        TextField field = new TextField();
        field.setText(nazev);
        field.setPrefSize(80,25);
        field.setEditable(false);
        getChildren().add(field);
        return field;
    }
}

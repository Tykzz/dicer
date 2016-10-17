package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class Controller {

    private Model model;

    public Controller() {
        model = new Model();
    }

    @FXML private TextField passwordField;
    @FXML private ChoiceBox wordNumber;

    @FXML private void initialize() {
        wordNumber.getSelectionModel().select(2);
    }

    @FXML private void handleGenerateButtonAction(ActionEvent event) {
        model.setWordNumber((Integer) wordNumber.getValue());
        passwordField.setText(model.generatePassword());
    }
}

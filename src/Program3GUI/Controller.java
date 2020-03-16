package Program3GUI;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;

public class Controller {
    @FXML
    private HBox triStateSelectionArea;

    @FXML
    private HBox exchangeSelectionArea;

    @FXML
    private HBox fundsArea;

    @FXML
    private TextArea outputArea;

    @FXML
    private ToggleGroup exchangeRadioGroup;


    @FXML
    public void typeSelectionDisabler(ActionEvent event){
        String eventSourceId = ((RadioButton)event.getSource()).getId();
        switch (eventSourceId){
            case "instateRadioButton":
                triStateSelectionArea.setDisable(true);
                exchangeSelectionArea.setDisable(true);
                fundsArea.setDisable(false);
                break;

            case "outstateRadioButton":
                fundsArea.setDisable(true);
                exchangeSelectionArea.setDisable(true);
                triStateSelectionArea.setDisable(false);
                break;

            case "internationalRadioButton":
                fundsArea.setDisable(true);
                triStateSelectionArea.setDisable(true);
                exchangeSelectionArea.setDisable(false);
                break;
        }
    }

}

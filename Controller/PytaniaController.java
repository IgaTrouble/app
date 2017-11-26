package app.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

public class PytaniaController {

    @FXML
    private TableView<?> tvPytania;

    @FXML
    private TableColumn<?, ?> colid;

    @FXML
    private TableColumn<?, ?> colpytanie;

    @FXML
    private TableColumn<?, ?> colodp1;

    @FXML
    private TableColumn<?, ?> colodp2;

    @FXML
    private TableColumn<?, ?> colodp3;

    @FXML
    private TableColumn<?, ?> colodp4;

    @FXML
    private TableColumn<?, ?> colodp5;

    @FXML
    private TableColumn<?, ?> colnrodp;

    @FXML
    private Button btnZamknij;

    @FXML
    private TextField tfPytanie; // coś

    @FXML
    private TextField tfOdp1;

    @FXML
    private TextField tfOdp2;

    @FXML
    private TextField tfOdp3;

    @FXML
    private TextField tfOdp4;

    @FXML
    private Button btnAdd;

    @FXML
    private RadioButton rbOdp1;

    @FXML
    private ToggleGroup G1;

    @FXML
    private RadioButton rbOdp2;

    @FXML
    private RadioButton rbOdp3;

    @FXML
    private RadioButton rbOdp4;

    @FXML
    void actionDodaj(MouseEvent event) {

    }

    @FXML
    void actionZamknij(MouseEvent event) {

    }

}

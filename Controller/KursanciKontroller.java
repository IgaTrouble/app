package app.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class KursanciKontroller {

    @FXML
    private TableView<?> tvKursanci;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colImie;

    @FXML
    private TableColumn<?, ?> colNazwisko;

    @FXML
    private TableColumn<?, ?> colGrupa;

    @FXML
    private Button btnZamknij;

    @FXML
    private Button btnPokazHaslo;

    @FXML
    private Button btnUsun;

    @FXML
    private TextField txEmail;

    @FXML
    private TextField txImie;

    @FXML
    private TextField txNazwisko;

    @FXML
    private TextField txGrupa;

    @FXML
    private PasswordField pwdPass1;

    @FXML
    private PasswordField pwdPass2;

    @FXML
    private Button btnDodaj;

    @FXML
    private TextField txPassword;

    @FXML
    void actionAdd(MouseEvent event) {

    }

    @FXML
    void actionPokazHaslo(MouseEvent event) {

    }

    @FXML
    void actionUsun(MouseEvent event) {

    }

    @FXML
    void actionZamknij(MouseEvent event) {

    }

}

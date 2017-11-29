package app.Controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class StatystykiGrupyController {

    @FXML
    private Button btnZamknij;

    @FXML
    private Label lblKursant;

    @FXML
    private Label lblGrupa;

    @FXML
    private Label lblTestyAll;

    @FXML
    private Label lblTestyZakonczone;

    @FXML
    private Label lblOdpowiedziAll;

    @FXML
    private Label lblOdpowiedziPopr;

    @FXML
    private Label lblOdpowiedziNiep;

    @FXML
    private Label lblOdpowiedziWynik;

    @FXML
    private PieChart pic;

    @FXML
    private ProgressBar pbWynik;

    @FXML
    void ActionZamknij(MouseEvent event) throws IOException {
    	System.out.println("Moje wyniki");
    	Stage stage = new Stage();
    	Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/StatystykiView.fxml"));
    	Scene scene = new Scene(parent);
    	stage.setScene(scene);
    	stage.setTitle("Statystyki");
    	stage.show();
    	((Node)(event.getSource())).getScene().getWindow().hide();

    }

}

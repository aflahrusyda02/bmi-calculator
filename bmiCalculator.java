import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BmiApp extends Application {

    private final Slider sliderBerat = new Slider(30, 200, 65);
    private final Slider sliderTinggi = new Slider(120, 220, 170);
    private final Label lblBerat = new Label();
    private final Label lblTinggi = new Label();
    private final Label lblNilai = new Label();
    private final Label lblStatus = new Label();
    private final Label lblIdeal = new Label();
    private final ProgressBar bar = new ProgressBar();

    @Override
    public void start(Stage stage) {
        // Judul di bagian atas
        Label lblJudul = new Label("BMI Checker");
        lblJudul.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");
        VBox header = new VBox(lblJudul);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(15));
        header.setStyle("-fx-background-color: #37474F;");

        // Pengaturan slider
        sliderBerat.setMajorTickUnit(10);
        sliderBerat.setShowTickMarks(true);
        sliderTinggi.setMajorTickUnit(10);
        sliderTinggi.setShowTickMarks(true);

        // Hasil
        lblNilai.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");
        lblStatus.setStyle("-fx-font-size: 16px;");
        bar.setPrefWidth(280);
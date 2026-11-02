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

        // Hasil dihitung ulang setiap slider digeser
        sliderBerat.valueProperty().addListener((obs, lama, baru) -> perbarui());
        sliderTinggi.valueProperty().addListener((obs, lama, baru) -> perbarui());

        VBox isi = new VBox(10,
                lblBerat, sliderBerat,
                lblTinggi, sliderTinggi,
                lblNilai, bar, lblStatus, lblIdeal);
        isi.setPadding(new Insets(20));
        isi.setAlignment(Pos.CENTER_LEFT);

        BorderPane root = new BorderPane();
        root.setTop(header);
        root.setCenter(isi);

        perbarui();

        stage.setTitle("BMI Checker");
        stage.setScene(new Scene(root, 360, 460));
        stage.show();
    }

    private void perbarui() {
        double berat = Math.round(sliderBerat.getValue());
        double tinggiCm = Math.round(sliderTinggi.getValue());
        double tinggiM = tinggiCm / 100.0;
        double bmi = berat / (tinggiM * tinggiM);

        String status;
        String warna;
        if (bmi < 18.5) {
            status = "Berat badan kurang";
            warna = "#1E88E5";
        } else if (bmi < 25) {
            status = "Berat badan normal";
            warna = "#43A047";
        } else if (bmi < 30) {
            status = "Berat badan berlebih";
            warna = "#FB8C00";
        } else {
            status = "Obesitas";
            warna = "#E53935";
        }

        // Rentang berat ideal (BMI 18,5 sampai 24,9)
        double beratMin = 18.5 * tinggiM * tinggiM;
        double beratMax = 24.9 * tinggiM * tinggiM;

        lblBerat.setText(String.format("Berat: %.0f kg", berat));
        lblTinggi.setText(String.format("Tinggi: %.0f cm", tinggiCm));
        lblNilai.setText(String.format("%.1f", bmi));
        lblNilai.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: " + warna + ";");
        lblStatus.setText(status);
        lblIdeal.setText(String.format("Berat ideal untuk tinggimu: %.1f - %.1f kg", beratMin, beratMax));

        bar.setProgress(Math.min(bmi, 40.0) / 40.0);
        bar.setStyle("-fx-accent: " + warna + ";");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
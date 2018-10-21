package formulario;

import formulario.model.FuncionLimiteModel;
import grafica.Grafica;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Formulario extends Application {
    private Stage stage;
    private VBox cajaCentral;
    private List<FuncionLimiteModel> valoresFunciones;

    public static void main(String[] args) {
        launch(args);
    }

    public void initForm() {
        // Layouts
        cajaCentral = new VBox();
        HBox cajaBotones = new HBox();

        Button newLine = new Button();
        newLine.setOnMouseClicked(event -> cajaCentral.getChildren().add(newLineForm()));
        newLine.setText("+ Agregar funcion +");

        Button ejecutarFunciones = new Button();
        ejecutarFunciones.setOnMouseClicked(event -> {
            Stage stageFunciones = new Stage();
            Grafica grafica = new Grafica(stageFunciones);
            grafica.setFunciones(valoresFunciones);
            grafica.start();
        });
        ejecutarFunciones.setText("Mostrar grafica");
        cajaBotones.getChildren().addAll(newLine, ejecutarFunciones);

        HBox fLinew = newLineForm();
        cajaCentral.getChildren().add(cajaBotones);
        cajaCentral.getChildren().add(fLinew);

        stage.setTitle("Funciones");
        stage.setScene(new Scene(cajaCentral, Color.rgb(255, 255, 255)));
        stage.show();
    }

    private HBox newLineForm() {
        HBox elementos = new HBox();
        TextField formula = new TextField();
        TextField limiteInicio = new TextField();
        TextField limiteFinal = new TextField();

        final Double id = Math.random();
        final FuncionLimiteModel model = new FuncionLimiteModel();
        model.setId(id);

        formula.textProperty().addListener((observable, oldValue, newValue) ->
                setValue(id, newValue, "funcion"));
        limiteInicio.textProperty().addListener((observable, oldValue, newValue) ->
                setValue(id, newValue, "limiteInicial"));
        limiteFinal.textProperty().addListener((observable, oldValue, newValue) ->
                setValue(id, newValue, "limiteFinal"));

        formula.setPromptText("Funcion");
        limiteInicio.setPromptText("Desde");
        limiteFinal.setPromptText("Hasta");
        valoresFunciones.add(model);
        elementos.getChildren().addAll(formula, limiteInicio, limiteFinal);
        return elementos;
    }

    private void setValue(Double id, String value, String property  ) {
        for (int i = 0; i < valoresFunciones.size(); i++) {
            FuncionLimiteModel model = valoresFunciones.get(i);
            if (model.getId().equals(id)) {
                switch (property) {
                    case "funcion":
                        model.setFuncion(value);
                        break;
                    case "limiteInicial":
                        model.setLimiteInicial(value);
                        break;
                    case "limiteFinal":
                        model.setLimiteFinal(value);
                        break;
                }
                valoresFunciones.set(i, model);
                break;
            }
        }
    }

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        this.valoresFunciones = new ArrayList<>();
        initForm();
    }
}

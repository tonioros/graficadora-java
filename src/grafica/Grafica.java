package grafica;

import formulario.model.FuncionLimiteModel;
import interprete.ExpressionInterpreter;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.List;

// Java 8 code
public class Grafica {
    private Stage stage;
    private StackPane layout;
    private Axes planoCartesiano;
    private List<FuncionLimiteModel> funciones;

    public Grafica(Stage stage) {
        this.stage = stage;
    }

    public void start() {
        // Layout base
        layout = new StackPane();
        layout.setPadding(new Insets(20));
        // Color de Fondo
        layout.setStyle("-fx-background-color: rgb(35, 39, 50);");

        // Especifica el tamaño del Stage (Window)
        planoCartesiano = new Axes(500, 600,
                // Maximo y minimo a presentar en el plano carteciano
                -10, 15, 1,
                -80, 80, 1);

        try {
            if (agregarFunciones()) {
                // Titulo del Stage
                stage.setTitle("y = ");
                stage.setScene(new Scene(layout, Color.rgb(35, 39, 50)));
                stage.show();
            }

        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en la expresion");
            alert.setHeaderText("Oh oh, tenemos problemas");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
            ex.printStackTrace();
        }
    }

    private boolean agregarFunciones() {
        if (!funciones.isEmpty()) {
            for (FuncionLimiteModel funcion : funciones) {
                String expressionInitial = funcion.getFuncion();
                if (expressionInitial != null && !expressionInitial.isEmpty()) {

                    ScriptEngineManager mgr = new ScriptEngineManager();
                    ScriptEngine engine = mgr.getEngineByName("JavaScript");

                    final String expressionFinal = ExpressionInterpreter.transformExpression(expressionInitial);

                    if (expressionFinal.toUpperCase().contains("NO SE PUEDE ")) {
                        // Presenta alerta si hubo error al procesar la funncion
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error en la expresion");
                        alert.setHeaderText("Oh oh, tenemos problemas");
                        alert.setContentText(expressionFinal);
                        alert.showAndWait();
                        return false;
                    } else {
                        // Calcula la grafia para presentar

                        Plot plot = new Plot(x -> {
                            String evaluar = expressionFinal.replace("x", String.valueOf(x));
                            try {
                                String result = engine.eval(evaluar).toString();
                                return Double.parseDouble(result);
                            } catch (ScriptException e) {
                                e.printStackTrace();
                                return 0.0;
                            }
                        },
                                // Limites
                                -8,
                                9,
                                0.1,
                                planoCartesiano);

                        // Agrega al Layout la vista de la grafica
                        layout.getChildren().add(plot);
                    }
                }
            }
        }
        return true;
    }

    public void setFunciones(List<FuncionLimiteModel> funciones) {
        this.funciones = funciones;
    }
}

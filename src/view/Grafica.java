package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

// Java 8 code
public class Grafica extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage stage) {
        // Especifica el tamaño del Stage (Window)
        Axes axes = new Axes(500, 600,
                -10, 15, 1,
                -80, 80, 1);

        // Plantea de forma logica al ecuacion a prentar
        //Plot plot = new Plot(x -> (0.15 * (x + 4) * (x + 1) * (x - 2)), -8, 8, 0.1, axes);
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        String foo = "(40 + 2) * 4";
        try {
            System.out.println(engine.eval(foo));
        } catch (ScriptException e) {
            e.printStackTrace();
        }

        Plot plot = new Plot(x -> (x - 2) * (x - 2) * (x - 2),
                -8, 1, 0.1,
                axes);

        // Agrega al Layout la vista de la grafica
        StackPane layout = new StackPane(plot);
        layout.setPadding(new Insets(20));
        // Color de Fondo
        layout.setStyle("-fx-background-color: rgb(35, 39, 50);");

        // Titulo del Stage
        stage.setTitle("y = \u00BC(x+4)(x+1)(x-2)");
        stage.setScene(new Scene(layout, Color.rgb(35, 39, 50)));
        stage.show();
    }
}

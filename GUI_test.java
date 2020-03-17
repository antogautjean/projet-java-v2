package org.antogautjean;

import javafx.application.Application;
import javafx.stage.Stage;
import org.antogautjean.model.Unit;

public class GUI_test extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("My First JavaFX App");
        primaryStage.show();
    }
    public static void main(String args[]) throws Exception {

        Unit a = Unit.strToUnit("kg");
        System.out.println(a);

        launch(args);
    }
}

package fi.company;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private Button clear;
    private TextArea tekstiAlue;

    @Override
    public void start(Stage stage) {
        MenuBar menuBar = new MenuBar();
        VBox vBox = new VBox(menuBar);
        HBox hBox = new HBox(vBox);

        // File menu
        Menu file = new Menu("File");

            // NEW ITEM
            MenuItem newItem = new MenuItem("New");
                KeyCombination newItemKey = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN);
                    newItem.setAccelerator(newItemKey);
                    newItem.setOnAction(actionEvent -> { System.out.println("CTRL+N"); });

            // OPEN
            MenuItem open = new MenuItem("Open");
                KeyCombination openKey = new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN);
                    open.setAccelerator(openKey);
                    open.setOnAction(actionEvent -> { System.out.println("CTRL+O"); });

            // SAVE
            MenuItem save = new MenuItem("Save");
                KeyCombination saveKey = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
                    save.setAccelerator(saveKey);
                    save.setOnAction(actionEvent -> { System.out.println("CTRL+S"); });

                    // adding the menuItems to the menuBar
                        file.getItems().add(newItem);
                        file.getItems().add(open);
                        file.getItems().add(save);
                        menuBar.getMenus().add(file);

        // Edit menu
        Menu edit = new Menu("Edit");

            // CUT
            MenuItem cut = new MenuItem("Cut");
                KeyCombination cutKey = new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN);
                    cut.setAccelerator(cutKey);
                    cut.setOnAction(actionEvent -> { System.out.println("CTRL+X"); });

            // COPY
            MenuItem copy = new MenuItem("Copy");
                 KeyCombination copyKey = new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN);
                 copy.setAccelerator(copyKey);
                 copy.setOnAction(actionEvent -> { System.out.println("CTRL+C"); });

            // PASTE
            MenuItem paste = new MenuItem("Paste");
                KeyCombination pasteKey = new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN);
                paste.setAccelerator(pasteKey);
                paste.setOnAction(actionEvent -> { System.out.println("CTRL+V"); });

                // adding the menuItems to the menuBar
                    edit.getItems().add(cut);
                    edit.getItems().add(copy);
                    edit.getItems().add(paste);
                    menuBar.getMenus().add(edit);


        // Run menu
        Menu run = new Menu("Run");
        MenuItem RunCompile = new MenuItem("Run & Compile");
        run.getItems().add(RunCompile);
        menuBar.getMenus().add(run);

        // About
        Menu about = new Menu("About");
        MenuItem aboutApp = new MenuItem("About this application");
        about.getItems().add(aboutApp);
        menuBar.getMenus().add(about);

        // Buttons
        clear = new Button("clear");
        vBox.getChildren().add(clear);
            clear.setOnAction(actionEvent -> {
                tekstiAlue.clear();
                    });



        // UI
        tekstiAlue = new TextArea();

        BorderPane layout = new BorderPane();
        layout.setTop(hBox);
        layout.setCenter(tekstiAlue);

        Scene scene = new Scene(layout, 600, 480);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
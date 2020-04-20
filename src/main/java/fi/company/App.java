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
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * JavaFX App
 * Made by Olli-Pekka Nikka
 */
public class App extends Application {

    // showing attributes
    private Button clear;
    private TextArea tekstiAlue;
    private ColorPicker colorPicker;

    @Override
    public void start(Stage stage) {

        // LOCALIZATION SETTINGS
            Locale locale = Locale.getDefault();
            System.out.println(locale);
            ResourceBundle labels = ResourceBundle.getBundle("ui", locale);
            String title = labels.getString("title");

        // HANDLING FILES
            File file = new File("resources/textFile.txt");

        // ELEMENTS FOR STAGE
            MenuBar menuBar = new MenuBar(); // top panel
            VBox vBoxtop = new VBox(menuBar); // top panel

        // FILE MENU
            Menu fileMenu = new Menu("File");

            // NEW ITEM
                MenuItem newItem = new MenuItem("New");
                    KeyCombination newItemKey = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN);
                     newItem.setAccelerator(newItemKey);
                            newItem.setOnAction(actionEvent -> { System.out.println("CTRL+N"); });

                        // FileChooser
                            FileChooser fileChooser = new FileChooser();
                                newItem.setOnAction(e -> { File selectedFile = fileChooser.showOpenDialog(stage); });

            // OPEN
                MenuItem open = new MenuItem("Open");
                    KeyCombination openKey = new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN);
                        open.setAccelerator(openKey);
                            open.setOnAction(actionEvent -> { System.out.println("CTRL+O"); });

                        // FileOpener
                            FileChooser fileOpener = new FileChooser();
                                open.setOnAction(e -> { File selectedFile = fileOpener.showOpenDialog(stage); });

            // SAVE
                MenuItem save = new MenuItem("Save");
                    KeyCombination saveKey = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
                        save.setAccelerator(saveKey);
                            save.setOnAction(actionEvent -> { System.out.println("CTRL+S"); });

            // EXIT - this closes the application
                MenuItem exit = new MenuItem("Exit");
                        exit.setOnAction(ActionEvent -> { System.exit(0); });


                    // adding the menuItems to the menuBar
                        fileMenu.getItems().add(newItem);
                        fileMenu.getItems().add(open);
                        fileMenu.getItems().add(save);
                        fileMenu.getItems().add(exit);
                        menuBar.getMenus().add(fileMenu);

        // EDIT MENU
            Menu edit = new Menu("Edit");

            // cut
                MenuItem cut = new MenuItem("Cut");
                    KeyCombination cutKey = new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN);
                        cut.setAccelerator(cutKey);
                            cut.setOnAction(actionEvent -> { System.out.println("CTRL+X"); });

            // copy
                MenuItem copy = new MenuItem("Copy");
                    KeyCombination copyKey = new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN);
                    copy.setAccelerator(copyKey);
                        copy.setOnAction(actionEvent -> { System.out.println("CTRL+C"); });

            // paste
                MenuItem paste = new MenuItem("Paste");
                    KeyCombination pasteKey = new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN);
                        paste.setAccelerator(pasteKey);
                            paste.setOnAction(actionEvent -> { System.out.println("CTRL+V"); });

                // adding the menuItems to the menuBar
                    edit.getItems().add(cut);
                    edit.getItems().add(copy);
                    edit.getItems().add(paste);
                    menuBar.getMenus().add(edit);


        // RUN MENU
            Menu run = new Menu("Run");
                MenuItem RunCompile = new MenuItem("Run & Compile");

            // adding run to the menu
                run.getItems().add(RunCompile);
                    menuBar.getMenus().add(run);

        // ABOUT
            Menu about = new Menu("About");
                MenuItem aboutApp = new MenuItem("About this application");

            // making alert window
                    about.setOnAction(actionEvent -> {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("About this application");
                        alert.setContentText("This is text editor application made by Olli-Pekka");
                        alert.showAndWait();
                    });

                    // adding about to the menuBar
                        about.getItems().add(aboutApp);
                        menuBar.getMenus().add(about);

        // BUTTONS
            colorPicker = new ColorPicker();
            colorPicker.setStyle("-fx-text-fill: red; -fx-font-family: arial; -fx-font-size: 12 px;");
                vBoxtop.getChildren().add(colorPicker);
                    colorPicker.setOnAction(actionEvent -> { ColorPicker colorPicker = new ColorPicker();colorPicker.getValue(); }); // opens colorPicker

            clear = new Button("Clear");
            clear.setStyle("-fx-text-fill: red; -fx-font-family: arial; -fx-font-size: 12 px;");
                vBoxtop.getChildren().add(clear);
                    clear.setOnAction(actionEvent -> { tekstiAlue.clear(); }); // clears textField


        // UI
            tekstiAlue = new TextArea();
                // replaces tab with 4 whitespaces
                    tekstiAlue.setOnKeyPressed(event -> { if(event.getCode() == KeyCode.TAB) { int position =  tekstiAlue.getCaretPosition();tekstiAlue.replaceText(position-1,position, "    "); }});



        // LAYOUT
            BorderPane layout = new BorderPane();
            layout.setTop(vBoxtop);
            layout.setCenter(tekstiAlue);

        // SCENE
            Scene scene = new Scene(layout, 600, 480);

        // STAGE
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
package fi.company;

import fi.company.utilities.FileHandler;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import fi.company.utilities.FileHandler;
import java.io.*;
import java.nio.Buffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * JavaFX App
 * Made by Olli-Pekka Nikka
 */
public class App extends Application {


    // introducing attributes
    private Button clear;
    private TextArea tekstiAlue;
    private ColorPicker colorPicker;
    private BorderPane layout;
    private MenuBar menuBar;
    private VBox vBoxTop;
    private TextField searchField;
    private ToolBar toolBar;

    @Override
    public void start(Stage stage) {

        // LOCALIZATION SETTINGS
        Locale locale = Locale.getDefault();
        System.out.println(locale);
        ResourceBundle labels = ResourceBundle.getBundle("ui", locale);
        String title = labels.getString("title");


        // ELEMENTS FOR STAGE
        tekstiAlue = new TextArea(); // middle
        layout = new BorderPane(); // top
        menuBar = new MenuBar(); // top
        vBoxTop= new VBox(); // top
        toolBar = new ToolBar();  // toolBar for clear, colorPicker and search
        searchField = new TextField(); // textField for text

        vBoxTop.getChildren().add(menuBar); // adding menuBar to vBox
        vBoxTop.getChildren().add(toolBar); // adding toolBar to vBox

        // FILE MENU
        Menu fileMenu = new Menu("File");

        // NEW ITEM
        MenuItem newItem = new MenuItem("New");
        KeyCombination newItemKey = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN);
        newItem.setAccelerator(newItemKey);
        newItem.setOnAction(e -> {
            System.out.println("CTRL+N");
        });

        // OPEN
        MenuItem open = new MenuItem("Open");
        KeyCombination openKey = new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN);
        open.setAccelerator(openKey);
        open.setOnAction(e -> {
            System.out.println("CTRL+O");
        });

        // FileOpener
            FileHandler fh = new FileHandler();
            FileChooser fileOpener = new FileChooser();
            open.setOnAction(e -> {
                    File selectedFile = fileOpener.showOpenDialog(stage);
                    String path = selectedFile.getAbsolutePath(); // contains selected files PATH
                    fh.setFilePath(path);
                    Thread thread = new Thread(() -> {
                        String sisalto = fh.open(); // stores text content to sisalto string
                        tekstiAlue.setText(sisalto); // adds sisalto to the tekstiAlue
                    });
                    thread.start();
            });


        // SAVE
        MenuItem save = new MenuItem("Save");
        KeyCombination saveKey = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
        save.setAccelerator(saveKey);
        save.setOnAction(e -> {
            System.out.println("CTRL+S");
        });

        // Saving the text
        save.setOnAction(e -> {
            Thread thread = new Thread(() -> {
                String sisalto = tekstiAlue.getText();
                fh.save(sisalto);
            });
            thread.start();
        });

        // EXIT - this closes the application
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> {
            System.exit(0);
        });


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
        cut.setOnAction(e -> {
            System.out.println("CTRL+X");
        });

        // copy
        MenuItem copy = new MenuItem("Copy");
        KeyCombination copyKey = new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN);
        copy.setAccelerator(copyKey);
        copy.setOnAction(e -> {
            System.out.println("CTRL+C");
        });

        // paste
        MenuItem paste = new MenuItem("Paste");
        KeyCombination pasteKey = new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN);
        paste.setAccelerator(pasteKey);
        paste.setOnAction(e -> {
            System.out.println("CTRL+V");
        });

         // adding the menuItems to the menuBar
        edit.getItems().add(cut);
        edit.getItems().add(copy);
        edit.getItems().add(paste);
        menuBar.getMenus().add(edit);

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

            // clear button
        clear = new Button("Clear");
        clear.setStyle("-fx-text-fill: red; -fx-font-family: arial; -fx-font-size: 12 px;");
        toolBar.getItems().add(clear);
        clear.setOnAction(actionEvent -> {
            tekstiAlue.clear(); // clears textField
        });

            // color picker
        colorPicker = new ColorPicker();
        colorPicker.setStyle("-fx-text-fill: red; -fx-font-family: arial; -fx-font-size: 12 px;");
        toolBar.getItems().add(colorPicker);
        colorPicker.setOnAction(actionEvent -> {
            ColorPicker colorPicker = new ColorPicker();
            colorPicker.getValue(); // opens colorPicker
        });

        // Search bar
        searchField.setPromptText("Search...");
        searchField.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) { // When enter is pressed it finds the text
                System.out.println("Enter");
                String searchText = searchField.getText();
                int index = tekstiAlue.getText().indexOf(searchText);
                tekstiAlue.selectRange(index, index + searchText.length());
            }
        });

        toolBar.getItems().add(searchField);


        // UI
            // replaces tab with 4 whitespaces
            tekstiAlue.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.TAB) {
                 int position = tekstiAlue.getCaretPosition();
                tekstiAlue.replaceText(position - 1, position, "    ");
                }
            });


        // LAYOUT
        layout.setTop(vBoxTop);
        layout.setCenter(tekstiAlue);
        layout.setStyle("-fx-background-color:white");

        // SCENE
        Scene scene = new Scene(layout, 800, 600);

        // STAGE
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
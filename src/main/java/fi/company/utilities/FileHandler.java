package fi.company.utilities;

import javafx.scene.control.Alert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHandler {

    private String filePath;

    public FileHandler() {

    }

    public FileHandler(String filePath) {
        setFilePath(filePath);
    }

    // getter and setter for filePath!
    public String getFilePath(String filePath) {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    // Opens the given file and returns the content, uses filePath
        public String open() {
            try {
                String content = Files.readString(Paths.get(filePath));
                return content;
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setContentText("Something went wrong!");
                alert.showAndWait();
                return null;
            }
        }

    // Saves the content to given file path
        public void save(String content) {
            try {
                Files.writeString(Paths.get(filePath), content);
            } catch (IOException e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error");
                    alert.setContentText("Something went wrong!");
                    alert.showAndWait();
                }
        }
}

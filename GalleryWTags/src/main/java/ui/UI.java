package ui;

import gallerywtags.Image;
import gallerywtags.Tag;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;

public class UI extends Application {
    HBox topUI;
    ArrayList<Image> images;

    @Override
    public void start(Stage window) {
        /* Temp stuff */
        images = new ArrayList<>();
        images.add(new Image("Test"));
        images.add(new Image("Test2"));
        images.get(0).addTag(new Tag("Test Tag"));
        images.get(0).addTag(new Tag("Test Tag 2"));
        
        
        
        window.setTitle("GalleryWTags");
        
        BorderPane layout = new BorderPane();
        Scene view = new Scene(layout, 1280, 720);
        
        /* Top UI */
        topUI = new HBox();
        
        Button topUIImagesButton = new Button("All Images");
        topUIImagesButton.setOnAction((event) -> {
                    window.setScene(createGalleryView(window));
                    window.show();
                });
        topUI.getChildren().add(topUIImagesButton);
        
        Button tagsButton = new Button("Tags");
        tagsButton.setOnAction((event) -> {
           window.setScene(createTagsView(window));
           window.show();
        });
        topUI.getChildren().add(tagsButton);
        
        Button uploadButton = new Button("Upload");
        uploadButton.setOnAction((event) -> {
           window.setScene(createUploadView(window));
           window.show();
        });
        topUI.getChildren().add(uploadButton);
        
        topUI.setSpacing(5);
        layout.setTop(topUI);
        
        window.setScene(view);
        window.show();
    }
    
    private Scene createGalleryView(Stage window) {
        BorderPane galleryLayout = new BorderPane();
        
        GridPane thumbs = new GridPane();
        
        /* Individual thumbnail boxes */
        int k = 0;
        
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                VBox thisThumb = new VBox();
                
                Canvas thumbPlaceholder = new Canvas(280, 320);
                GraphicsContext placeholderGC = thumbPlaceholder.getGraphicsContext2D();
                placeholderGC.setFill(Color.LIGHTBLUE);
                placeholderGC.fillRect(10, 10, 260, 300);
                
                thisThumb.getChildren().add(thumbPlaceholder);
                
                Image currentImage = new Image("Test");
                
                if (k < images.size()) {
                    currentImage = images.get(k);
                    thisThumb.getChildren().add(new Label(currentImage.getTitle()));
                } else {
                    thisThumb.getChildren().add(new Label("Test "+i+", "+j));
                }
                k++;
                
                Scene currentImageView = createImageView(window, currentImage);
                
                Button imgButton = new Button("Open");
                
                imgButton.setOnAction((event) -> {
                    window.setScene(currentImageView);
                    window.show();
                });
                thisThumb.getChildren().add(imgButton);
                
                thumbs.add(thisThumb, j, i);
            }
        }
        
        thumbs.setHgap(10);
        thumbs.setVgap(10);
        
        galleryLayout.setTop(topUI);
        galleryLayout.setCenter(thumbs);
        
        Scene galleryScene = new Scene(galleryLayout);
        
        return galleryScene;
    }
    
    private Scene createImageView(Stage window, Image img) {
        BorderPane imgViewLayout = new BorderPane();
        
        VBox imgVBox = new VBox();
        Canvas imgPlaceholder = new Canvas(300, 500);
        GraphicsContext imgGC = imgPlaceholder.getGraphicsContext2D();
        imgGC.setFill(Color.LIGHTBLUE);
        imgGC.fillRect(0, 0, 300, 500);
        
        imgVBox.getChildren().add(imgPlaceholder);
        imgVBox.getChildren().add(new Label(img.getTitle()));
        imgVBox.getChildren().add(new Label(img.getTags()));
        
        Button editTagsButton = new Button("Edit Tags");
        editTagsButton.setOnAction((event) -> {
           window.setScene(createGalleryView(window));
           window.show();
        });
        
        // imgVBox.getChildren().add();
        
        imgViewLayout.setTop(topUI); 
// Ei toimi kun kun createImageView kutsutaan button eventin ulkopuolella
        imgViewLayout.setCenter(imgVBox);
        
        Scene imgViewScene = new Scene(imgViewLayout);
        
        return imgViewScene;
    }
    
    private Scene createEditImgTagsView(Stage window, Image img) {
        BorderPane editTagsLayout = new BorderPane();
        editTagsLayout.setTop(topUI);
        
        Scene editTagsScene = new Scene(editTagsLayout);
        
        return editTagsScene;
    }
    
    private Scene createTagsView(Stage window) {
        BorderPane tagsLayout = new BorderPane();
        tagsLayout.setTop(topUI);
        
        tagsLayout.setCenter(new Label("Tags"));
        
        Scene tagsScene = new Scene(tagsLayout);
        
        return tagsScene;
    }
    
    private Scene createUploadView(Stage window) {
        BorderPane uploadLayout = new BorderPane();
        uploadLayout.setTop(topUI);
        
        GridPane uploadForm = new GridPane();
        uploadForm.add(new Label("Title: "), 0, 0);
        TextField titleField = new TextField();
        uploadForm.add(titleField, 1, 0);
        
        Button uploadButton = new Button("Upload");
        uploadButton.setOnAction((event) -> {
           images.add(new Image(titleField.getText()));
           window.setScene(createGalleryView(window));
           window.show();
        });
        uploadForm.add(uploadButton, 0, 2);
        
        uploadForm.setHgap(10);
        uploadForm.setVgap(10);
        
        uploadForm.setPadding(new Insets(10));
        uploadLayout.setCenter(uploadForm);
        
        Scene uploadScene = new Scene(uploadLayout);
        
        return uploadScene;
    }
    
    public static void main(String[] args) {
        launch(UI.class);
    }
}

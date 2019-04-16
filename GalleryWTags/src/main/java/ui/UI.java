package ui;

import domain.Img;
import domain.ImgService;
import domain.Tag;
import java.io.File;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class UI extends Application {
    HBox topUI;
    ImgService imgService;

    @Override
    public void start(Stage window) {
        imgService = new ImgService();
        
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
                
                Img currentImage = new Img("Test");
                
                if (k < imgService.getImages().size()) {
                    currentImage = imgService.getImage(k);
                    
                    // Kuvatiedosto
                    Image imgfile = new Image("file:"+currentImage.getPath());
                    ImageView image = new ImageView(imgfile);
                    System.out.println(image.getFitHeight()+" "+image.getFitWidth());
                    image.setFitHeight(320);
                    image.setFitWidth(280);
                    thisThumb.getChildren().add(image);
                    
                    thisThumb.getChildren().add(new Label(currentImage.getTitle()));
                } else {
                    Canvas thumbPlaceholder = new Canvas(280, 320);
                    GraphicsContext placeholderGC = thumbPlaceholder.getGraphicsContext2D();
                    placeholderGC.setFill(Color.LIGHTBLUE);
                    placeholderGC.fillRect(10, 10, 260, 300);
                    thisThumb.getChildren().add(thumbPlaceholder);
                    
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
    
    private Scene createImageView(Stage window, Img img) {
        BorderPane imgViewLayout = new BorderPane();
        imgViewLayout.setTop(createTopUI(window)); 
        // Jostain syystä ei toimi topUI-muuttujalla
        
        VBox imgVBox = new VBox();
        Canvas imgPlaceholder = new Canvas(300, 500);
        GraphicsContext imgGC = imgPlaceholder.getGraphicsContext2D();
        imgGC.setFill(Color.LIGHTBLUE);
        imgGC.fillRect(0, 0, 300, 500);
        
        // Kuvatiedosto
        Image imgfile = new Image("file:"+img.getPath());
        ImageView image = new ImageView(imgfile);
        
        
        imgVBox.getChildren().add(image);
        imgVBox.getChildren().add(new Label(img.getTitle()));
        imgVBox.getChildren().add(new Label(img.getTags()));
        
        Button editTagsButton = new Button("Edit Tags");
        editTagsButton.setOnAction((event) -> {
           window.setScene(createEditImgTagsView(window, img));
           window.show();
        });
        
        imgVBox.getChildren().add(editTagsButton);
        
        imgViewLayout.setCenter(imgVBox);
        
        Scene imgViewScene = new Scene(imgViewLayout);
        
        return imgViewScene;
    }
    
    private Scene createEditImgTagsView(Stage window, Img img) {
        BorderPane editTagsLayout = new BorderPane();
        editTagsLayout.setTop(topUI);
        
        VBox editTagsVBox = new VBox();
        
        for (int i = 0; i < img.getTagsList().size(); i++) {
            HBox tagHBox = new HBox();
            tagHBox.getChildren().add(new Label(img.getTagsList().get(i).toString()));
            
            Button removeTagButton = new Button("Remove");
            removeTagButton.setOnAction((event) -> {
                // Tagin poisto ei toimi vielä
                window.setScene(createEditImgTagsView(window, img));
                window.show();
            });
            tagHBox.getChildren().add(removeTagButton);
            
            editTagsVBox.getChildren().add(tagHBox);
        }
        
        HBox newTagHBox = new HBox();
        
        TextField newTagField = new TextField();
        
        Button newTagButton = new Button("Add New Tag");
        newTagButton.setOnAction((event) -> {
           img.addTag(new Tag(newTagField.getText()));
           window.setScene(createEditImgTagsView(window, img));
           window.show();
        });
        
        newTagHBox.getChildren().add(newTagField);
        newTagHBox.getChildren().add(newTagButton);
        
        editTagsVBox.getChildren().add(newTagHBox);
        
        editTagsLayout.setCenter(editTagsVBox);
        
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
        
        // Kuvan valitsin
        
        FileChooser fileChooser = new FileChooser();
        Button selectImgButton = new Button("Choose Image...");
        selectImgButton.setOnAction((event) -> {
           File file = fileChooser.showOpenDialog(window);
           // System.out.println(file.getAbsolutePath());
           imgService.addImage(titleField.getText(), file.getAbsolutePath());
        });
        uploadForm.add(selectImgButton, 0, 2);
        
        Button uploadButton = new Button("Upload");
        uploadButton.setOnAction((event) -> {
           
           window.setScene(createGalleryView(window));
           window.show();
        });
        uploadForm.add(uploadButton, 1, 2);
        
        uploadForm.setHgap(10);
        uploadForm.setVgap(10);
        
        uploadForm.setPadding(new Insets(10));
        uploadLayout.setCenter(uploadForm);
        
        Scene uploadScene = new Scene(uploadLayout);
        
        return uploadScene;
    }
    
    public HBox createTopUI(Stage window) {
        HBox topUIHbox = new HBox();
        
        Button topUIImagesButton = new Button("All Images");
        topUIImagesButton.setOnAction((event) -> {
                    window.setScene(createGalleryView(window));
                    window.show();
                });
        topUIHbox.getChildren().add(topUIImagesButton);
        
        Button tagsButton = new Button("Tags");
        tagsButton.setOnAction((event) -> {
           window.setScene(createTagsView(window));
           window.show();
        });
        topUIHbox.getChildren().add(tagsButton);
        
        Button uploadButton = new Button("Upload");
        uploadButton.setOnAction((event) -> {
           window.setScene(createUploadView(window));
           window.show();
        });
        topUIHbox.getChildren().add(uploadButton);
        
        topUIHbox.setSpacing(5);
        
        return topUIHbox;
    }
    
    public static void main(String[] args) {
        launch(UI.class);
    }
}

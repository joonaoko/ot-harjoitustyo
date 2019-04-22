package ui;

import domain.Img;
import domain.ImgService;
import domain.Tag;
import domain.TagService;
import java.io.File;
import java.sql.SQLException;
import java.util.*;

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
    TagService tagService;

    @Override
    public void start(Stage window) throws Exception {
        imgService = new ImgService();
        tagService = new TagService();
        
        window.setTitle("GalleryWTags");
        
        BorderPane layout = new BorderPane();
        Scene view = new Scene(layout, 1280, 720);
        
        /* Top UI */
        topUI = new HBox();
        
        Button topUIImagesButton = new Button("All Images");
        topUIImagesButton.setOnAction((event) -> {
            try {
                window.setScene(createGalleryView(window));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            } 
            window.show();
        });
        topUI.getChildren().add(topUIImagesButton);
        
        Button tagsButton = new Button("Tags");
        tagsButton.setOnAction((event) -> {
            try {
                window.setScene(createTagsView(window));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
           window.show();
        });
        topUI.getChildren().add(tagsButton);
        
        Button uploadButton = new Button("Add Image");
        uploadButton.setOnAction((event) -> {
            try {
                window.setScene(createUploadView(window));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
           window.show();
        });
        topUI.getChildren().add(uploadButton);
        
        topUI.setSpacing(5);
        layout.setTop(topUI);
        
        window.setScene(view);
        window.show();
    }
    
    private Scene createGalleryView(Stage window) throws Exception {
        BorderPane galleryLayout = new BorderPane();
        
        GridPane thumbs = new GridPane();
        
        /* Individual thumbnail boxes */
        List<Img> allImages = imgService.getImages();
        int row = 0;
        int col = 0;
        
        for (Img currentImg : allImages) {
            VBox thisThumb = new VBox();
            
            if (col >= 5) {
                row++;
                col = 0;
            }
            
            Image imgfile = new Image("file:"+currentImg.getPath());
            ImageView image = new ImageView(imgfile);
            
            /* Resize image for thumbnail */
            image.setPreserveRatio(true);
            double height = imgfile.getHeight();
            double width = imgfile.getWidth();
            
            if (height >= width+40) {
                if (height > 320) image.setFitHeight(320);
            } else {
                if (width > 280) image.setFitWidth(280);
            }
            
            thisThumb.getChildren().add(image);
            thisThumb.getChildren().add(new Label(currentImg.getTitle()));
            Scene currentImageView = createImageView(window, currentImg);
            Button imgButton = new Button("Open");

            imgButton.setOnAction((event) -> {
                window.setScene(currentImageView);
                window.show();
            });
            thisThumb.getChildren().add(imgButton);

            thumbs.add(thisThumb, col, row);
            
            col++;
        }
        
        thumbs.setHgap(10);
        thumbs.setVgap(10);
        
        galleryLayout.setTop(topUI);
        galleryLayout.setCenter(thumbs);
        
        Scene galleryScene = new Scene(galleryLayout);
        
        return galleryScene;
    }
    
    private Scene createTagGalleryView(Stage window, int tag_id) throws Exception {
        BorderPane galleryLayout = new BorderPane();
        
        GridPane thumbs = new GridPane();
        
        /* Individual thumbnail boxes */
        List<Img> allImages = imgService.getTagImages(tag_id);
        
        if (!allImages.isEmpty()) {
            // ********* Kuvattomien tagien käsittely ei toimi vielä ***********
            galleryLayout.setCenter(new Label("No images with this tag"));
        }
        
        int row = 0;
        int col = 0;
        
        for (Img currentImg : allImages) {
            VBox thisThumb = new VBox();
            
            if (col >= 5) {
                row++;
                col = 0;
            }
            
            Image imgfile = new Image("file:"+currentImg.getPath());
            ImageView image = new ImageView(imgfile);
            
            /* Resize image for thumbnail */
            image.setPreserveRatio(true);
            double height = imgfile.getHeight();
            double width = imgfile.getWidth();
            
            if (height >= width+40) {
                if (height > 320) image.setFitHeight(320);
            } else {
                if (width > 280) image.setFitWidth(280);
            }
            
            thisThumb.getChildren().add(image);
            thisThumb.getChildren().add(new Label(currentImg.getTitle()));
            Scene currentImageView = createImageView(window, currentImg);
            Button imgButton = new Button("Open");

            imgButton.setOnAction((event) -> {
                window.setScene(currentImageView);
                window.show();
            });
            thisThumb.getChildren().add(imgButton);

            thumbs.add(thisThumb, col, row);
            
            col++;
        }
        
        thumbs.setHgap(10);
        thumbs.setVgap(10);
        
        galleryLayout.setTop(createTopUI(window));
        galleryLayout.setCenter(thumbs);
        
        Scene galleryScene = new Scene(galleryLayout);
        
        return galleryScene;
    }
    
    private Scene createImageView(Stage window, Img img) throws Exception {
        BorderPane imgViewLayout = new BorderPane();
        imgViewLayout.setTop(createTopUI(window)); 
        // Jostain syystä ei toimi topUI-muuttujalla
        
        VBox imgVBox = new VBox();
        Canvas imgPlaceholder = new Canvas(300, 500);
        GraphicsContext imgGC = imgPlaceholder.getGraphicsContext2D();
        imgGC.setFill(Color.LIGHTBLUE);
        imgGC.fillRect(0, 0, 300, 500);
        
        /* Get image file from path */
        Image imgfile = new Image("file:"+img.getPath());
        ImageView image = new ImageView(imgfile);
        
        
        imgVBox.getChildren().add(image);
        imgVBox.getChildren().add(new Label(img.getTitle()));
        imgVBox.getChildren().add(new Label(img.getTagsString()));
        
        /* Edit tags button */
        Button editTagsButton = new Button("Edit Tags");
        editTagsButton.setOnAction((event) -> {
            try {
                window.setScene(createEditImgTagsView(window, img));
                window.show();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });
        imgVBox.getChildren().add(editTagsButton);
        
        /* Remove image button */
        Button removeImgButton = new Button("Remove Image");
        removeImgButton.setOnAction((event) -> {
            try {
                imgService.removeImage(img.getId());
                window.setScene(createGalleryView(window));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });
        imgVBox.getChildren().add(removeImgButton);
        
        imgViewLayout.setCenter(imgVBox);
        
        Scene imgViewScene = new Scene(imgViewLayout);
        
        return imgViewScene;
    }
    
    private Scene createEditImgTagsView(Stage window, Img img) throws Exception {
        BorderPane editTagsLayout = new BorderPane();
        
        VBox editTagsVBox = new VBox();
        
        List<Tag> allTags = imgService.getImageTags(img.getId());
        
        for (Tag tag : allTags) {
            HBox tagHBox = new HBox();
            tagHBox.getChildren().add(new Label(tag.toString()));
            
            Button removeTagButton = new Button("Remove");
            removeTagButton.setOnAction((event) -> {
                try {
                    imgService.removeImageTag(img.getId(), tag.getId());
                    window.setScene(createEditImgTagsView(window, img));
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                window.show();
            });
            tagHBox.getChildren().add(removeTagButton);
            
            editTagsVBox.getChildren().add(tagHBox);
        }
        
        HBox newTagHBox = new HBox();
        
        TextField newTagField = new TextField();
        
        Button newTagButton = new Button("Add New Tag");
        newTagButton.setOnAction((event) -> {
            try {
                imgService.addImageTag(img.getId(), newTagField.getText());
                window.setScene(createEditImgTagsView(window, img));
                window.show();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });
        
        newTagHBox.getChildren().add(newTagField);
        newTagHBox.getChildren().add(newTagButton);
        
        editTagsVBox.getChildren().add(newTagHBox);
        editTagsLayout.setTop(topUI);
        editTagsLayout.setCenter(editTagsVBox);
        
        Scene editTagsScene = new Scene(editTagsLayout);
        return editTagsScene;
    }
    
    private Scene createTagsView(Stage window) throws Exception {
        BorderPane tagsLayout = new BorderPane();
        tagsLayout.setTop(createTopUI(window));
        
        List<Tag> allTags = tagService.getAllTags();
        VBox tagsVBox = new VBox();
        
        for (Tag t : allTags) {
            HBox tagHBox = new HBox();
            tagHBox.getChildren().add(new Label(t.toString()));
            
            Scene currentTagGalleryScene = createTagGalleryView(window, t.getId());
            
            Button openTagGalleryButton = new Button("Open");
            openTagGalleryButton.setOnAction((event) -> {
                window.setScene(currentTagGalleryScene);
                window.show();
            });
            tagHBox.getChildren().add(openTagGalleryButton);
            
            tagsVBox.getChildren().add(tagHBox);
        }
        
        tagsLayout.setCenter(tagsVBox);
        Scene tagsScene = new Scene(tagsLayout);
        return tagsScene;
    }
    
    private Scene createUploadView(Stage window) throws Exception{
        BorderPane uploadLayout = new BorderPane();
        uploadLayout.setTop(topUI);
        
        GridPane uploadForm = new GridPane();
        uploadForm.add(new Label("Title: "), 0, 0);
        TextField titleField = new TextField();
        uploadForm.add(titleField, 1, 0);
        
        /* Image chooser */
        FileChooser fileChooser = new FileChooser();
        Button selectImgButton = new Button("Choose Image...");
        selectImgButton.setOnAction((event) -> {
           File file = fileChooser.showOpenDialog(window);
            try {
                imgService.addImage(titleField.getText(), file.getAbsolutePath());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });
        uploadForm.add(selectImgButton, 0, 2);
        
        Button uploadButton = new Button("Add Image");
        uploadButton.setOnAction((event) -> {
            try {
                window.setScene(createGalleryView(window));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
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
            try {
                window.setScene(createGalleryView(window));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            window.show();
            });
        topUIHbox.getChildren().add(topUIImagesButton);
        
        Button tagsButton = new Button("Tags");
        tagsButton.setOnAction((event) -> {
            try {
                window.setScene(createTagsView(window));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
           window.show();
        });
        topUIHbox.getChildren().add(tagsButton);
        
        Button uploadButton = new Button("Add Image");
        uploadButton.setOnAction((event) -> {
            try {
                window.setScene(createUploadView(window));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
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

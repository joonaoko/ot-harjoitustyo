package gallerywtags;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Image> images = new ArrayList<>();
        ArrayList<Tag> tags = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        
        images.add(new Image("Test1"));
        images.add(new Image("Test2"));
        
        while (true) {
            System.out.println("\nChoose action: \n"
                    + "1: Upload Image \n"
                    + "2: Edit Image Tags \n"
                    + "3: Remove Image \n"
                    + "4: View Images \n"
                    + "x: Exit");
            
            System.out.print("?: ");
            String i = s.nextLine();
            
            if (i.equals("1")) {
                System.out.print("Image Title: ");
                i = s.nextLine();
                
                Image currentImage = new Image(i);
                images.add(currentImage);
                
            } else if (i.equals("2")) {
                for (int j = 0; j < images.size(); j++) {
                    System.out.print(j+": "+images.get(j).getTitle()+", ");
                }
                System.out.print("\nChoose Image: ");
                
                i = s.nextLine();
                Image currentImage = images.get(Integer.parseInt(i));
                
                while (true) {
                    System.out.println("\nChoose action: \n"
                            + "1: Add Tag \n"
                            + "2: Remove Tag \n"
                            + "x: Exit");
                    
                    System.out.print("?: ");
                    i = s.nextLine();
                    
                    if (i.equals("1")) {
                        System.out.print("Tag: ");
                        i = s.nextLine();
                        currentImage.addTag(new Tag(i));
                        
                    } else if (i.equals("2")) {
                        System.out.println("\nChoose tag: ");
                        System.out.println(currentImage.getTags());
                        
                        i = s.nextLine();
                        currentImage.removeTag(Integer.parseInt(i));
                        
                    } else if (i.equals("x")) {
                        break;
                    }
                }
                
            } else if (i.equals("3")) {
                return;
                
            } else if (i.equals("4")) {
                System.out.println();
                for (Image img : images) {
                    System.out.println(img);
                }
                
            } else if (i.equals("x")) {
                return;
            }
        }
    }
}

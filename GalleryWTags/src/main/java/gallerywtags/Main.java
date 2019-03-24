package gallerywtags;

public class Main {
    public static void main(String[] args) {
        Image f = new Image(1);
        f.addTag(new Tag(1, "tree"));
        f.addTag(new Tag(2, "bird"));
        
        System.out.println(f);
    }
}

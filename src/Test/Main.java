package Test;


public class Main {
    public static void main(String[] args) {

        PhotoAlbum album = new PhotoAlbum();

        album.addPhoto(new Photo("Beach", new Date(12,10,2024), "beach.png"));
        album.addPhoto(new Photo("Mountains", new Date(12,10,2024), "mountain.jpg"));
        album.addPhoto(new Photo("Family", new Date(12,10,2024), "family.png"));
        album.addPhoto(new Photo("City", new Date(12,10,2024), "city.png"));

        System.out.println("=== Parcours complet avec for-each ===");
        for (Photo p : album) {
            System.out.println(p);
        }
    }
}

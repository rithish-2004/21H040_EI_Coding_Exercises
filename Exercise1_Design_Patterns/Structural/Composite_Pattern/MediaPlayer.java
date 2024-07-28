package Exercise1_Design_Patterns.Structural.Composite_Pattern;

import java.util.Scanner;
import java.util.logging.Logger;

public class MediaPlayer {
    private static final Logger logger = Logger.getLogger(MediaPlayer.class.getName());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Playlist rootPlaylist = new Playlist("Root Playlist");
        Playlist currentPlaylist = rootPlaylist;

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Create Playlist");
            System.out.println("2. Add Song");
            System.out.println("3. Play Next");
            System.out.println("4. Find Playlist");
            System.out.println("5. Show Current Playlist");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    System.out.print("Enter playlist name: ");
                    String playlistName = scanner.nextLine();
                    Playlist newPlaylist = new Playlist(playlistName);
                    currentPlaylist.add(newPlaylist);
                    System.out.println("Playlist created: " + playlistName);
                    logger.info("Created playlist: " + playlistName);
                    break;

                case 2:
                    System.out.print("Enter song name: ");
                    String songName = scanner.nextLine();
                    System.out.print("Enter song artist: ");
                    String artist = scanner.nextLine();
                    Song newSong = new Song(songName, artist);
                    currentPlaylist.add(newSong);
                    System.out.println("Song added: " + songName);
                    logger.info("Added song: " + songName + " by " + artist);
                    break;

                case 3:
                    System.out.println("Playing next song...");
                    currentPlaylist.next();
                    break;

                case 4:
                    System.out.print("Enter playlist name to find: ");
                    String nameToFind = scanner.nextLine();
                    Playlist foundPlaylist = rootPlaylist.findPlaylist(nameToFind);
                    if (foundPlaylist != null) {
                        System.out.println("Found playlist:\n" + foundPlaylist);
                        currentPlaylist = foundPlaylist;
                    } else {
                        System.out.println("Playlist not found.");
                    }
                    break;

                case 5:
                    System.out.println("Current Playlist:\n" + currentPlaylist);
                    break;

                case 6:
                    System.out.println("Exiting...");
                    logger.info("Exiting the application.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}

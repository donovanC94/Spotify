package com.user;

import com.search.artist.ArtistTopTracks;
import com.search.artist.SearchArtists;
import com.search.track.SearchTracks;

import java.util.Scanner;

public class UserInteraction {

    public static void userInput() {
        System.out.println("Choose from the following options:");
        System.out.println("Search for a track - 1");
        System.out.println("Search for an album - 2");
        System.out.println("Search for an artist - 3");
        System.out.println("Generate a playlist - 4");

        Scanner scan = new Scanner(System.in);
        String userChoice = scan.next();

        switch(userChoice) {
            case "1":
                searchTrack();
                break;
            case "2":
                searchAlbum();
                break;
            case "3":
                searchArtist();
                break;
            case "4":
                generatePlaylist();
                break;
        }
    }

    public static void searchTrack() {
        System.out.println("Search track");
        Scanner scan = new Scanner(System.in);
        String trackSearch = scan.next();
        SearchTracks.searchTracks_Sync(trackSearch);
    }

    public static void searchAlbum() {
        System.out.println("Search album");
    }

    public static void searchArtist() {
        System.out.println("Search artist");
        Scanner scan = new Scanner(System.in);
        String artistSearch = scan.next();
        String artistId = SearchArtists.searchArtists_Sync(artistSearch);

        System.out.println("Artist top tracks:");
        System.out.println();
        ArtistTopTracks.getArtistsTopTracks_Sync(artistId);

    }

    public static void generatePlaylist() {
        System.out.println("Generate Playlist");
    }
}

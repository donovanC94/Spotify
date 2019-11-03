package com.search.artist;

import com.authorization.ClientCredentials.ClientCredentialsExample;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.requests.data.search.simplified.SearchArtistsRequest;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;

public class SearchArtists {
    private static String artistName;

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(ClientCredentialsExample.getAccessToken())
            .build();

    public static String searchArtists_Sync(String artistName) {

        SearchArtistsRequest searchArtistsRequest = spotifyApi.searchArtists(artistName).limit(5).build();
        HashMap<Integer, String> artistAndPopularity = new HashMap<>();
        HashMap<Integer, String> artistAndId = new HashMap<>();

        try {
            final Paging<Artist> artistPaging = searchArtistsRequest.execute();

            System.out.println("Total: " + artistPaging.getTotal());

            for(Artist artist : artistPaging.getItems()) {
                artistAndPopularity.put(artist.getPopularity(), artist.getName());
                artistAndId.put(artist.getPopularity(), artist.getId());
                System.out.println("Name: " + artist.getName() + ", Popularity: " + artist.getPopularity() + ", Id: " + artist.getId());
            }

            Integer maxKey = Collections.max(artistAndPopularity.keySet());
            setArtistName(artistAndPopularity.get(maxKey));
            String artistId = artistAndId.get(maxKey);
            System.out.println("Most Popular artist for your search: " + artistName);
            System.out.println("Artist ID: " + artistId);

            return artistId;

        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public static String getArtistName() {
        return artistName;
    }

    public static void setArtistName(String artistName) {
        SearchArtists.artistName = artistName;
    }

    /*public static void searchArtists_Async() {
        try {
            final CompletableFuture<Paging<Artist>> pagingFuture = searchArtistsRequest.executeAsync();

            // Thread free to do other tasks...

            // Example Only. Never block in production code.
            final Paging<Artist> artistPaging = pagingFuture.join();

            System.out.println("Total: " + artistPaging.getTotal());
        } catch (CompletionException e) {
            System.out.println("Error: " + e.getCause().getMessage());
        } catch (CancellationException e) {
            System.out.println("Async operation cancelled.");
        }
    }*/
}

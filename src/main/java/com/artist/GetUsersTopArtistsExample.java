package com.artist;

import com.authorization.oauth2.AuthorizationCodeExample;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.requests.data.personalization.simplified.GetUsersTopArtistsRequest;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetUsersTopArtistsExample {

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
           // .setAccessToken(ClientCredentialsExample.getAccessToken())
            .setAccessToken(AuthorizationCodeExample.getSpotifyApi().getAccessToken())
            .setClientId("7fbda6272b7d42228251330f00840933")
            .setClientSecret("a3c949ae48314c5bb5ab05d6114e6e39")
            .build();
    private static final GetUsersTopArtistsRequest getUsersTopArtistsRequest = spotifyApi.getUsersTopArtists()
            .limit(10)
            .offset(0)
            .time_range("long_term")
            .build();

    public static void getUsersTopArtists_Sync() {
        try {
            final Paging<Artist> artistPaging = getUsersTopArtistsRequest.execute();

            System.out.println("Total: " + artistPaging.getTotal());

            for(Artist artist : artistPaging.getItems()) {
                System.out.println(artist.getName());
            }

        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void getUsersTopArtists_Async() {
        try {
            final CompletableFuture<Paging<Artist>> pagingFuture = getUsersTopArtistsRequest.executeAsync();

            // Thread free to do other tasks...

            // Example Only. Never block in production code.
            final Paging<Artist> artistPaging = pagingFuture.join();

            System.out.println("Total: " + artistPaging.getTotal());
        } catch (CompletionException e) {
            System.out.println("Error: " + e.getCause().getMessage());
        } catch (CancellationException e) {
            System.out.println("Async operation cancelled.");
        }
    }
}

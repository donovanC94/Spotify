package com.track;

import com.authorization.AuthorizationCodeExample;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;

import java.io.IOException;


public class SearchTracks {
    private static final String accessToken;
    //private static final String q = "higher love";

    static {
        accessToken = AuthorizationCodeExample.getSpotifyApi().getAccessToken();
    }

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(accessToken)
            .build();

    public static void searchTracks_Sync(String name) {

        SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(name).limit(10).build();

        try {
            final Paging<Track> trackPaging = searchTracksRequest.execute();

            System.out.println("Total: " + trackPaging.getTotal());

            for(Track track : trackPaging.getItems()) {
                System.out.println(track.getName() + " - " + track.getArtists()[0].getName() + " (popularity: " + track.getPopularity() + ")");
            }
        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

//    public static void searchTracks_Async() {
//        try {
//            final CompletableFuture<Paging<Track>> pagingFuture = searchTracksRequest.executeAsync();
//
//            // Thread free to do other tasks...
//
//            // Example Only. Never block in production code.
//            final Paging<Track> trackPaging = pagingFuture.join();
//
//            System.out.println("Total: " + trackPaging.getTotal());
//        } catch (CompletionException e) {
//            System.out.println("Error: " + e.getCause().getMessage());
//        } catch (CancellationException e) {
//            System.out.println("Async operation cancelled.");
//        }
//    }
}

package com.track;

import com.authorization.AuthorizationCodeExample;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.tracks.GetTrackRequest;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetTrackExample {
    private static final String accessToken;
    private static final String id = "7qEHsqek33rTcFNT9PFqLf";

    static {
        accessToken = AuthorizationCodeExample.getSpotifyApi().getAccessToken();
    }

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(accessToken)
            .build();
    private static final GetTrackRequest getTrackRequest = spotifyApi.getTrack(id)
//          .market(CountryCode.SE)
            .build();

    public static void getTrack_Sync() {
        try {
            final Track track = getTrackRequest.execute();

            System.out.println("Name: " + track.getName());
        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void getTrack_Async() {
        try {
            final CompletableFuture<Track> trackFuture = getTrackRequest.executeAsync();

            // Thread free to do other tasks...

            // Example Only. Never block in production code.
            final Track track = trackFuture.join();

            System.out.println("Name: " + track.getName());
        } catch (CompletionException e) {
            System.out.println("Error: " + e.getCause().getMessage());
        } catch (CancellationException e) {
            System.out.println("Async operation cancelled.");
        }
    }
}
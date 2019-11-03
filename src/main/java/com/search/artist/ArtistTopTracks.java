package com.search.artist;

import com.authorization.ClientCredentials.ClientCredentialsExample;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.artists.GetArtistsTopTracksRequest;

import java.io.IOException;

public class ArtistTopTracks {
    private static final CountryCode countryCode = CountryCode.GB;

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(ClientCredentialsExample.getAccessToken())
            .build();

    public static void getArtistsTopTracks_Sync(String artistId) {

        GetArtistsTopTracksRequest getArtistsTopTracksRequest = spotifyApi
                .getArtistsTopTracks(artistId, countryCode)
                .build();

        try {
            final Track[] tracks = getArtistsTopTracksRequest.execute();

            for(Track track : tracks) {
                System.out.println("Artist: " + SearchArtists.getArtistName() + ", Song: " + track.getName() + ", Song Id: " + track.getId() + ", Song Popularity: " + track.getPopularity());
            }

        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /*public static void getArtistsTopTracks_Async() {
        try {
            final CompletableFuture<Track[]> artistsFuture = getArtistsTopTracksRequest.executeAsync();

            // Thread free to do other tasks...

            // Example Only. Never block in production code.
            final Track[] tracks = artistsFuture.join();

            System.out.println("Length: " + tracks.length);
        } catch (CompletionException e) {
            System.out.println("Error: " + e.getCause().getMessage());
        } catch (CancellationException e) {
            System.out.println("Async operation cancelled.");
        }
    }*/
}

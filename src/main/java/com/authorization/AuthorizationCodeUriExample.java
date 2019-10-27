package com.authorization;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

import java.net.URI;
import java.util.Scanner;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class AuthorizationCodeUriExample {
    private static final String clientId = "7fbda6272b7d42228251330f00840933";
    private static final String clientSecret = "a3c949ae48314c5bb5ab05d6114e6e39";
    private static final URI redirectUri = SpotifyHttpManager.makeUri("http://localhost:8080/");
    private static final String state = RandomString.generateRandomString(8);

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .setRedirectUri(redirectUri)
            .build();
    private static final AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
         .client_id(clientId)
         .redirect_uri(redirectUri)
         .state(state)
         .response_type("code")
// .state("x4xkmn9pu3j6ukrs8n")
            .scope("user-library-read, user-top-read, playlist-modify-public, user-read-playback-state")
//          .show_dialog(true)
            .build();

    public static void authorizationCodeUri_Sync() throws Exception {
        final URI uri = authorizationCodeUriRequest.execute();
        System.out.println("URI: " + uri.toString());
        System.out.println("Press enter after user authorization");
        Scanner scan = new Scanner(System.in);
        String x = scan.nextLine();
        HttpCodeRequest.requestCode(uri);
        GetCode.getCode(uri);
    }

    public static void authorizationCodeUri_Async() {
        try {
            final CompletableFuture<URI> uriFuture = authorizationCodeUriRequest.executeAsync();

            // Thread free to do other tasks...

            // Example Only. Never block in production code.
            final URI uri = uriFuture.join();

            System.out.println("URI: " + uri.toString());
        } catch (CompletionException e) {
            System.out.println("Error: " + e.getCause().getMessage());
        } catch (CancellationException e) {
            System.out.println("Async operation cancelled.");
        }
    }

    public static boolean validateState() {

        boolean isStateValidated = true;
        return isStateValidated;
    }
}
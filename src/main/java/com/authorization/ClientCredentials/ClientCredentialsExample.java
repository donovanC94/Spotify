package com.authorization.ClientCredentials;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class ClientCredentialsExample {
  private static final String clientId = "7fbda6272b7d42228251330f00840933";
  private static final String clientSecret = "a3c949ae48314c5bb5ab05d6114e6e39";
  private static String accessToken;

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setClientId(clientId)
          .setClientSecret(clientSecret)
          .build();
  private static final ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
          .build();

  public static void clientCredentials_Sync() {
    try {
      final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

      // Set access token for further "spotifyApi" object usage
      spotifyApi.setAccessToken(clientCredentials.getAccessToken());
      setAccessToken(clientCredentials.getAccessToken());

      System.out.println("Expires in: " + clientCredentials.getExpiresIn());
    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void clientCredentials_Async() {
    try {
      final CompletableFuture<ClientCredentials> clientCredentialsFuture = clientCredentialsRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final ClientCredentials clientCredentials = clientCredentialsFuture.join();

      // Set access token for further "spotifyApi" object usage
      spotifyApi.setAccessToken(clientCredentials.getAccessToken());

      System.out.println("Expires in: " + clientCredentials.getExpiresIn());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static String getAccessToken() {
    return accessToken;
  }

  public static void setAccessToken(String accessToken) {
    ClientCredentialsExample.accessToken = accessToken;
  }
}

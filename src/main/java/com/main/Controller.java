package com.main;

import com.artist.GetUsersTopArtistsExample;
import com.authorization.oauth2.AuthorizationCodeExample;
import com.authorization.oauth2.AuthorizationCodeUriExample;

public class Controller {
    public static void main(String[] args) throws Exception {
        // Authorization to get access code
        AuthorizationCodeUriExample.authorizationCodeUri_Sync();
        AuthorizationCodeExample.authorizationCode_Sync();
        //AuthorizationCodeUriExample.validateState();

        //ClientCredentialsExample.clientCredentials_Sync();

        // User makes choice
        //UserInteraction.userInput();

        //GetTrackExample.getTrack_Sync();
        GetUsersTopArtistsExample.getUsersTopArtists_Sync();

        //com.authorization.AuthorizationCodeRefreshExample.authorizationCodeRefresh_Sync();
        //unneeded.SearchTracksExample.searchTracks_Sync();
    }
}
package com.main;

import com.authorization.AuthorizationCodeExample;
import com.authorization.AuthorizationCodeUriExample;
import com.user.UserInteraction;

public class Controller {
    public static void main(String[] args) throws Exception {


        // Authorization to get access code
        AuthorizationCodeUriExample.authorizationCodeUri_Sync();
        AuthorizationCodeExample.authorizationCode_Sync();
        AuthorizationCodeUriExample.validateState();


        // User makes choice
        UserInteraction.userInput();



        //GetTrackExample.getTrack_Sync();
        //GetUsersTopArtistsExample.getUsersTopArtists_Sync();




        //com.authorization.AuthorizationCodeRefreshExample.authorizationCodeRefresh_Sync();
        //unneeded.SearchTracksExample.searchTracks_Sync();
    }
}
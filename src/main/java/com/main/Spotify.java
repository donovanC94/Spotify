package com.main;

import com.artist.GetUsersTopArtistsExample;
import com.authorization.AuthorizationCodeExample;
import com.authorization.AuthorizationCodeUriExample;
import com.track.GetTrackExample;

public class Spotify {
    public static void main(String[] args) throws Exception {
        AuthorizationCodeUriExample.authorizationCodeUri_Sync();
        AuthorizationCodeExample.authorizationCode_Sync();
        GetTrackExample.getTrack_Sync();
        GetUsersTopArtistsExample.getUsersTopArtists_Sync();
        //com.authorization.AuthorizationCodeRefreshExample.authorizationCodeRefresh_Sync();
        //unneeded.SearchTracksExample.searchTracks_Sync();
    }
}
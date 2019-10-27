package com.authorization;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class GetCode {

public static void getCode(URI uri) throws IOException {
    System.out.println("Entered");
    URL url = uri.toURL();
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");
}

}

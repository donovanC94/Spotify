package com.main.other;

import java.net.*;
import java.io.*;

public class URLReader {

    
    
    public static void URL(String url) throws IOException {
    	URL oracle = new URL(url);
        BufferedReader in = new BufferedReader(
        new InputStreamReader(oracle.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
    }
}
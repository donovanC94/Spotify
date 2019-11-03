package com.authorization.other;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class HttpCodeRequest {

    public static void requestCode(URI uri) throws IOException, URISyntaxException {

            String spotifyRedirectURL = uri.toString();
            URL url = new URL("http%3A%2F%2Flocalhost%3A8080%2Ftoken");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setConnectTimeout(50000);
            con.setReadTimeout(50000);
            con.setInstanceFollowRedirects(false);
            con.setRequestProperty("Content-Type", "application/json");

            String contentType = con.getHeaderField("Content-Type");
            System.out.println("Content type: " + contentType);

            int status = con.getResponseCode();
            System.out.println("Status: " + status);

            System.out.println("Response Message:"
                    + con.getResponseMessage());

            // to print the status of
            // instanceFollowRedirect property.
            System.out.println("InstanceFollowRedirects:"
                    + con.getInstanceFollowRedirects());


   

            // to print if usingProxy flag set or not.
            System.out.println("Using proxy:" + con.usingProxy());

            String fetchedUrl = getFinalURL(spotifyRedirectURL);
            System.out.println("FetchedURL is:" + fetchedUrl);

            System.out.println(con.getHeaderFields());
            System.out.println(con.getInstanceFollowRedirects());

        }



        /*BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        System.out.println("Input line: " + inputLine);

        if (status == HttpURLConnection.HTTP_MOVED_TEMP
                || status == HttpURLConnection.HTTP_MOVED_PERM) {
            String code2 = con.getHeaderField("code");
            System.out.println("Code: " + code2);
            //URL newUrl = new URL(location);
            //con = (HttpURLConnection) newUrl.openConnection();
        }*/


//        Map<String, String> parameters = new HashMap<>();
//        parameters.put("code", "val");
// 
//        con.setDoOutput(true);
//        DataOutputStream out = new DataOutputStream(con.getOutputStream());
//        out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
//        out.flush();
//        out.close();
//
//        con.setRequestProperty("Content-Type", "application/json");
//        String contentType = con.getHeaderField("Content-Type");


    public static String getFinalURL(String url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setInstanceFollowRedirects(false);
        con.connect();
        con.getInputStream();

        if (con.getResponseCode() == HttpURLConnection.HTTP_MOVED_PERM || con.getResponseCode() == HttpURLConnection.HTTP_MOVED_TEMP) {
            String redirectUrl = con.getHeaderField("code");
            return getFinalURL(redirectUrl);
        }
        return url;
    }
}




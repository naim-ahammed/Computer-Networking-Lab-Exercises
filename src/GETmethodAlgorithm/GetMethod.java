package GETmethodAlgorithm;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetMethod {

        public static void main (String[]args){
            try {
                String initialUrl = "http://webcode.me/";
                URL url = new URL(initialUrl);

                boolean redirect = false;

                do {
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    conn.setRequestMethod("GET");
                    conn.setInstanceFollowRedirects(false); // Handle redirects manually

                    int responseCode = conn.getResponseCode();

                    if (responseCode == HttpURLConnection.HTTP_MOVED_PERM || responseCode == HttpURLConnection.HTTP_MOVED_TEMP) {
                        String newUrl = conn.getHeaderField("Location");
                        System.out.println("Redirected to: " + newUrl);
                        url = new URL(newUrl);
                        redirect = true;
                    } else {
                        redirect = false;

                        if (responseCode == HttpURLConnection.HTTP_OK) {
                            System.out.println("Response Code: " + responseCode);
                            System.out.println("Response Message: " + conn.getResponseMessage());

                            StringBuilder responseContent = new StringBuilder();
                            try (BufferedReader buffer = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                                String line;
                                while ((line = buffer.readLine()) != null) {
                                    responseContent.append(line).append(System.lineSeparator());
                                }
                            }

                            System.out.println("Webpage Content:\n" + responseContent.toString());
                        } else {
                            System.out.println("Failed to fetch the webpage. Response Code: " + responseCode);
                        }
                    }
                } while (redirect);

            } catch (Exception e) {
                System.err.println("An error occurred: " + e.getMessage());
            }

            System.out.println("Program finished.");
        }
    }

/*
 * This a Java SE application that demonstrates how to use RingCentral SDK and Analytics API's (beta)
 */
package com.ringcentral.demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import com.ringcentral.RestClient;
import com.ringcentral.definitions.TokenInfo;

public class App {

  private static String RINGCENTRAL_CLIENT_ID = "";
  private static String RINGCENTRAL_CLIENT_SECRET = "";

  private static String RINGCENTRAL_USERNAME = "";
  private static String RINGCENTRAL_PASSWORD = "";
  private static String RINGCENTRAL_EXTENSION = "";

  private static final String AGGREGATE_API_PATH = "/analytics/phone/performance/v1/accounts/~/calls/aggregate";
  private static final String TIMELINE_API_PATH = "/analytics/phone/performance/v1/accounts/~/calls/timeline?interval=Week";

  private static String RINGCENTRAL_SERVER_URL = "https://platform.devtest.ringcentral.com";

  public static void main(String[] args) throws Exception {

    App.initCredentials();
    RestClient rc = new RestClient(RINGCENTRAL_CLIENT_ID, RINGCENTRAL_CLIENT_SECRET, RINGCENTRAL_SERVER_URL);
    TokenInfo token = rc.authorize(RINGCENTRAL_USERNAME, RINGCENTRAL_EXTENSION, RINGCENTRAL_PASSWORD);
    String accessToken = token.access_token;

    String aggregate_json_file_path = "src/main/resources/aggregate-request-body.json";
    String timeline_json_file_path = "src/main/resources/timeline-request-body.json";
    String aggregateJsonStr = App.readFileAsString(aggregate_json_file_path);
    String timelineJsonStr = App.readFileAsString(timeline_json_file_path);

    try {
      HttpResponse<String> aggreageteHttpResponse = getData(aggregateJsonStr, AGGREGATE_API_PATH, accessToken);
      System.out.println("---AGGREGATE API RESPONSE---");
      System.out.println(aggreageteHttpResponse.statusCode());
      System.out.println(aggreageteHttpResponse.body());
      HttpResponse<String> timelineHttpResponse = getData(timelineJsonStr, TIMELINE_API_PATH, accessToken);
      System.out.println("---TIMELINE API RESPONSE---");
      System.out.println(timelineHttpResponse.statusCode());
      System.out.println(timelineHttpResponse.body());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  // Read the local.properties file and set the credentials using that
  static void initCredentials() {
    Properties prop = new Properties();
    InputStream input = null;
    try {
      input = new FileInputStream("local.properties");
      prop.load(input);
      RINGCENTRAL_CLIENT_ID = prop.getProperty("RC_CLIENT_ID");
      RINGCENTRAL_CLIENT_SECRET = prop.getProperty("RC_CLIENT_SECRET");
      RINGCENTRAL_USERNAME = prop.getProperty("RC_USERNAME");
      RINGCENTRAL_PASSWORD = prop.getProperty("RC_PASSWORD");
      RINGCENTRAL_EXTENSION = prop.getProperty("RC_EXTENSION");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  static HttpResponse<String> getData(String jsonStr, String endpoint, String accessToken) throws Exception {
    HttpClient httpClient = HttpClient.newHttpClient();
    HttpRequest httpRequest = HttpRequest.newBuilder()
      .header("Content-Type", "application/json")
      .header("Authorization", "Bearer " + accessToken)
      .uri(URI.create(RINGCENTRAL_SERVER_URL + endpoint))
      .POST(HttpRequest.BodyPublishers.ofString(jsonStr))
      .build();

    HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    return httpResponse;
  }

  static String readFileAsString(String file) throws Exception {
    return new String(Files.readAllBytes(Paths.get(file)));
  }
}

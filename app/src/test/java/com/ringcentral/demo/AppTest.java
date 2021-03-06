/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.ringcentral.demo;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
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
import com.ringcentral.RestException;
import com.ringcentral.definitions.TokenInfo;

public class AppTest {

  private static String RINGCENTRAL_CLIENT_ID = "";
  private static String RINGCENTRAL_CLIENT_SECRET = "";
  private static String RINGCENTRAL_SERVER_URL = "https://platform.devtest.ringcentral.com";
  
  private static String RINGCENTRAL_USERNAME = "";
  private static String RINGCENTRAL_PASSWORD = "";
  private static String RINGCENTRAL_EXTENSION = "";

  private static final String AGGREGATE_API_PATH = "/analytics/phone/performance/v1/accounts/~/calls/aggregate";
  private static final String TIMELINE_API_PATH = "/analytics/phone/performance/v1/accounts/~/calls/timeline?interval=Week";

  private String accessToken = "";

  @Test
  public void testAppAuthorization() throws Exception {
    try {
      String token = getToken();
      assertNotNull("Access Token doesn't exist", token);
    } 
    catch (Exception e) {
      e.printStackTrace();
    } 
  }

  @Test
  public void testAggregteAPIStatus() throws Exception {
    try {
      String token = getToken();
      String aggregateJsonStr = App.readFileAsString("src/main/resources/aggregate-request-body.json");
      HttpResponse<String> aggregateHTTPResponse = App.getData(aggregateJsonStr, "/analytics/phone/performance/v1/accounts/~/calls/aggregate", token);
      assertEquals("Status code should be 200", aggregateHTTPResponse.statusCode(), 200);
    } 
    catch (Exception e) {
      e.printStackTrace();
    } 
  }

  @Test
  public void testTimelineAPIStatus() throws Exception {
    try {
      String token = getToken();
      String timelineJsonStr = App.readFileAsString("src/main/resources/timeline-request-body.json");
      HttpResponse<String> timelineHTTPResponse = App.getData(timelineJsonStr, "/analytics/phone/performance/v1/accounts/~/calls/timeline?interval=Week", token);
      assertEquals("Status code should be 200", timelineHTTPResponse.statusCode(), 200);
    } 
    catch (Exception e) {
      e.printStackTrace();
    } 
  }

  // Helper methods
  private String getToken() throws Exception {
    initCredentials();
    RestClient rc = new RestClient(RINGCENTRAL_CLIENT_ID, RINGCENTRAL_CLIENT_SECRET, RINGCENTRAL_SERVER_URL);
    TokenInfo token = rc.authorize(RINGCENTRAL_USERNAME, RINGCENTRAL_EXTENSION, RINGCENTRAL_PASSWORD);
    return token.access_token;
  }

  private void initCredentials() {
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
}

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

public class CallPerformanceAnalytics {

  private static String RINGCENTRAL_CLIENTID = "";
  private static String RINGCENTRAL_CLIENTSECRET = "";

  private static String RINGCENTRAL_USERNAME = "";
  private static String RINGCENTRAL_PASSWORD = "";
  private static String RINGCENTRAL_EXTENSION = "";

  private static String RC_ACCESS_TOKEN = "Bearer U0pDMDFQMDdQQVMwMHxBQUNtS3lyZzFoV0RHTktQemxNT3pZbTRxSjdLSFV3aGc2UE8tQ2VvTWJvT3p3eUVzbU8xOXA2TkV6ZXBMU081SVpzcUxXOENqdjQweGxfWXNseWRwNVZacy1qUjhadk12dmJKeS1UQ05uLUdWc3VIQzNsQzRZUzl6NmZUZnN3SEY3Vkx1MUlVYXlUMDQxV0RPblNnUUp1dVl4dGFXVnRQX240ejRneTFJUF9NbUQ2Q2FhbXY1cmRTUFZXdi1rc1ZkWWcwMzMwc3xENC1obmd8dTNPbjFDcnZGR2VxUy01TjZqMS1JQXxBUQ";

  private static final String AGGREGATE_API_PATH = "/analytics/phone/performance/v1/accounts/~/calls/aggregate";
  private static final String TIMELINE_API_PATH = "/analytics/phone/performance/v1/accounts/~/calls/timeline?interval=Week";

  public static void main(String[] args) throws Exception {

    CallPerformanceAnalytics.initCredentials();

    String aggregate_json_file_path = "src/main/resources/aggregate-request-body.json";
    String timeline_json_file_path = "src/main/resources/timeline-request-body.json";
    String aggregateJsonStr = CallPerformanceAnalytics.readFileAsString(aggregate_json_file_path);
    String timelineJsonStr = CallPerformanceAnalytics.readFileAsString(timeline_json_file_path);

    try {
      getData(aggregateJsonStr, AGGREGATE_API_PATH);
      getData(timelineJsonStr, TIMELINE_API_PATH );
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static String readFileAsString(String file) throws Exception {
    return new String(Files.readAllBytes(Paths.get(file)));
  }

  // load the gradle.properties file and set the credentials using that
  private static void initCredentials() {
    Properties prop = new Properties();
    InputStream input = null;
    try {
      input = new FileInputStream("local.properties");
      prop.load(input);
      RINGCENTRAL_CLIENTID = prop.getProperty("RINGCENTRAL_CLIENTID");
      RINGCENTRAL_CLIENTSECRET = prop.getProperty("RINGCENTRAL_CLIENTSECRET");
      RINGCENTRAL_USERNAME = prop.getProperty("RINGCENTRAL_USERNAME ");
      RINGCENTRAL_PASSWORD = prop.getProperty("RINGCENTRAL_PASSWORD");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void getData(String jsonStr, String endpoint) throws Exception {

    final String RINGCENTRAL_SERVER_URL = "https://platform.ringcentral.com"; // currently set to run on Production enviornment, optionally can run in Sandbox as well

    HttpClient httpClient = HttpClient.newHttpClient();
    HttpRequest httpRequest = HttpRequest.newBuilder()
      .header("Content-Type", "application/json")
      .header("Authorization", RC_ACCESS_TOKEN)
      .uri(URI.create(RINGCENTRAL_SERVER_URL + endpoint))
      .POST(HttpRequest.BodyPublishers.ofString(jsonStr))
      .build();

    HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    System.out.println(httpResponse.statusCode());
    System.out.println(httpResponse.body());
  }
}

package software.ulpgc.katas.infrastructure.config;

public class ServiceConfig {
    public static final String BASE_URL = "http://localhost:8080";

    public static String getBaseUrl() {
        String envUrl = System.getenv("SERVICE_BASE_URL");
        return (envUrl != null && !envUrl.isBlank()) ? envUrl : BASE_URL;
    }
}
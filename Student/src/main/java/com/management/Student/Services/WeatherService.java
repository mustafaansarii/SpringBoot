package com.management.Student.Services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherService {

    @Value("${Weather.api.key}")
    private String API_KEY;

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public JsonNode getWeather(String city) {
        String url = UriComponentsBuilder.fromHttpUrl("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/")
                .pathSegment(city) // Add the city as a path segment
                .queryParam("unitGroup", "metric")
                .queryParam("include", "current")
                .queryParam("key", API_KEY)
                .toUriString();

        String response = restTemplate.getForObject(url, String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response);

            return objectMapper.createObjectNode()
                    .put("queryCost", root.get("queryCost").asInt())
                    .put("latitude", root.get("latitude").asDouble())
                    .put("longitude", root.get("longitude").asDouble())
                    .put("resolvedAddress", root.get("resolvedAddress").asText())
                    .put("address", root.get("address").asText())
                    .put("timezone", root.get("timezone").asText())
                    .put("tzoffset", root.get("tzoffset").asDouble());

        } catch (Exception e) {
            throw new RuntimeException("Error parsing weather data", e);
        }
    }
}
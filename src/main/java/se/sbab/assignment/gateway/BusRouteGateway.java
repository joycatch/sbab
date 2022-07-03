package se.sbab.assignment.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se.sbab.assignment.domain.JourneyPatternPointOnLine;
import se.sbab.assignment.domain.StopPoint;
import se.sbab.assignment.response.LineDataResponse;

import java.util.List;

@Slf4j
@Service
public class BusRouteGateway {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${trafiklab.key}")
    private String key;

    private final String BASE_URL = "https://api.sl.se/api2/LineData.json?DefaultTransportModeCode=BUS";

    public BusRouteGateway(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<JourneyPatternPointOnLine> getJourneyPatternPointOnLine() {
        return getResponse("jour").getResponseData().toDomain();
    }

    public List<StopPoint> getStopPoints() {
        return getResponse("stop").getResponseData().toDomain();
    }

    private LineDataResponse getResponse(String model) {
        String url = BASE_URL + "&model=" + model + "&key=" + key;
        try {
            ResponseEntity<LineDataResponse> response = restTemplate.getForEntity(url, LineDataResponse.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Could not fetch data from " + url + ". Returning null");
            return null;
        }
    }
}

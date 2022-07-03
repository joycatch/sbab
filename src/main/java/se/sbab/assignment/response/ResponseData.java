package se.sbab.assignment.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import se.sbab.assignment.domain.JourneyPatternPointOnLine;
import se.sbab.assignment.domain.StopPoint;

import java.util.List;

@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData {

    @JsonProperty("Type")

    public String type;

    @JsonProperty("Result")
    public JsonNode result;

    public <T> List<T> toDomain() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.UPPER_CAMEL_CASE);
            ObjectReader objectReader = objectMapper.reader();

            if ("JourneyPatternPointOnLine".equals(type)) {
                objectReader  = objectReader.forType(new TypeReference<List<JourneyPatternPointOnLine>>(){});
            } else {
                objectReader  = objectReader.forType(new TypeReference<List<StopPoint>>(){});
            }
            return objectReader.readValue(result);
        } catch (Exception e) {
            log.error("Could not parse list to domain objects!");
            throw new RuntimeException(e);
        }
    }
}
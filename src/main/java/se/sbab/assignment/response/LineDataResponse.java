package se.sbab.assignment.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LineDataResponse {

    @JsonProperty("StatusCode")
    public int statusCode;

    @JsonProperty("ExecutionTime")
    public int executionTime;

    @JsonProperty("ResponseData")
    public ResponseData responseData;

}

package se.sbab.assignment.gateway

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import se.sbab.assignment.domain.JourneyPatternPointOnLine
import se.sbab.assignment.domain.StopPoint
import se.sbab.assignment.response.LineDataResponse
import se.sbab.assignment.response.ResponseData
import spock.lang.Specification

import static se.sbab.assignment.TestHelper.exampleJourneyPatternPointOnLine
import static se.sbab.assignment.TestHelper.exampleStopPoint

class BusRouteGatewaySpec extends Specification {

    def listOfJourneyPatternPointOnLines = [exampleJourneyPatternPointOnLine(),
                                            exampleJourneyPatternPointOnLine()] as JourneyPatternPointOnLine[]
    def listOfStopPoints = [exampleStopPoint(),
                            exampleStopPoint(),
                            exampleStopPoint()] as StopPoint[]

    def objectMapper = new ObjectMapper()
    RestTemplate restTemplate = Mock()
    BusRouteGateway busRouteGateway = new BusRouteGateway(restTemplate)

    def setup() {
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.UPPER_CAMEL_CASE);
    }

    def "should return expected amount of JourneyPatternPointOnLine from external source"() {
        given:
        def responseData = ResponseData.builder()
                .type("JourneyPatternPointOnLine")
                .result(objectMapper.valueToTree(listOfJourneyPatternPointOnLines))
                .build()
        def lineDataResponse = LineDataResponse.builder()
                .statusCode(200)
                .executionTime(20)
                .responseData(responseData)
                .build()
        restTemplate.getForEntity(_, LineDataResponse.class) >> new ResponseEntity(lineDataResponse, HttpStatus.OK)

        when:
        def journeyPatternPointOnLine = busRouteGateway.getJourneyPatternPointOnLine()

        then:
        journeyPatternPointOnLine.size() == listOfJourneyPatternPointOnLines.length
    }

    def "should return expected amount of StopPoints from external source"() {
        given:
        def responseData = ResponseData.builder()
                .type("StopPoint")
                .result(objectMapper.valueToTree(listOfStopPoints))
                .build()
        def lineDataResponse = LineDataResponse.builder()
                .statusCode(200)
                .executionTime(20)
                .responseData(responseData)
                .build()
        restTemplate.getForEntity(_, LineDataResponse.class) >> new ResponseEntity(lineDataResponse, HttpStatus.OK)

        when:
        def stopPoints = busRouteGateway.getStopPoints()

        then:
        stopPoints.size() == listOfStopPoints.length
    }
}
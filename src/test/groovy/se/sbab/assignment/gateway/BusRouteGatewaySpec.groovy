package se.sbab.assignment.gateway

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import se.sbab.assignment.domain.JourneyPatternPointOnLine
import se.sbab.assignment.domain.StopPoint
import se.sbab.assignment.response.LineDataResponse
import se.sbab.assignment.response.ResponseData
import spock.lang.Specification

class BusRouteGatewaySpec  extends Specification {

    def journeyPoint1 = JourneyPatternPointOnLine.builder()
            .id(1)
            .lineNumber(1)
            .directionCode(1)
            .journeyPatternPointNumber(1)
            .build()
    def journeyPoint2 = JourneyPatternPointOnLine.builder()
            .id(2)
            .lineNumber(1)
            .directionCode(2)
            .journeyPatternPointNumber(2)
            .build()
    def listOfJourneyPatternPointOnLines = [journeyPoint1, journeyPoint2] as JourneyPatternPointOnLine[]

    def stopPoint1 = StopPoint.builder()
            .stopPointNumber(1)
            .stopPointName("A random name")
            .build()
    def stopPoint2 = StopPoint.builder()
            .stopPointNumber(2)
            .stopPointName("Another name")
            .build()
    def stopPoint3 = StopPoint.builder()
            .stopPointNumber(3)
            .stopPointName("A third name")
            .build()
    def listOfStopPoints = [stopPoint1, stopPoint2, stopPoint3] as StopPoint[]

    def objectMapper = new ObjectMapper()
    RestTemplate restTemplate = Mock()
    BusRouteGateway busRouteGateway = new BusRouteGateway(restTemplate)

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
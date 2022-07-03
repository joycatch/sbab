package se.sbab.assignment.gateway

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import se.sbab.assignment.domain.JourneyPatternPointOnLine
import se.sbab.assignment.response.LineDataResponse
import se.sbab.assignment.response.ResponseData
import spock.lang.Specification

class BusRouteGatewaySpec  extends Specification {

    def examplePoint1 = JourneyPatternPointOnLine.builder()
            .id(1)
            .LineNumber(1)
            .DirectionCode(1)
            .JourneyPatternPointNumber(1)
            .build()
    def examplePoint2 = JourneyPatternPointOnLine.builder()
            .id(2)
            .LineNumber(1)
            .DirectionCode(2)
            .JourneyPatternPointNumber(2)
            .build()
    def listOfJourneyPatternPointOnLines = [examplePoint1, examplePoint2] as JourneyPatternPointOnLine[]

    def objectMapper = new ObjectMapper()
    RestTemplate restTemplate = Mock()
    BusRouteGateway busRouteGateway = new BusRouteGateway(restTemplate)

    def "should return expected amount of JourneyPatternPointOnLine from external source"() {
        given:
        def responseData = ResponseData.builder()
                .Type("JourneyPatternPointOnLine")
                .Result(objectMapper.valueToTree(listOfJourneyPatternPointOnLines))
                .build()
        def lineDataResponse = LineDataResponse.builder()
                .StatusCode(200)
                .ExecutionTime(20)
                .ResponseData(responseData)
                .build()
        restTemplate.getForEntity(_, LineDataResponse.class) >> new ResponseEntity(lineDataResponse, HttpStatus.OK)

        when:
        def journeyPatternPointOnLine = busRouteGateway.getJourneyPatternPointOnLine()

        then:
        journeyPatternPointOnLine.size() == listOfJourneyPatternPointOnLines.length
    }
}
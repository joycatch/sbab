package se.sbab.assignment.response

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import se.sbab.assignment.domain.JourneyPatternPointOnLine
import se.sbab.assignment.domain.StopPoint
import spock.lang.Specification

import static se.sbab.assignment.TestHelper.exampleJourneyPatternPointOnLine
import static se.sbab.assignment.TestHelper.exampleStopPoint

class ResponseDataSpec extends Specification {

    def objectMapper = new ObjectMapper()

    def setup() {
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.UPPER_CAMEL_CASE);
    }

    def "should throw a RuntimeException when invoking toDomain() with type set to #type"() {
        given:
        def responseData = ResponseData.builder().type(type).build()

        when:
        responseData.toDomain()

        then:
        thrown(RuntimeException)

        where:
        type << [null, "", "abc", "InvalidType"]
    }

    def "should return a List with #klass elements when toDomain() is invoked with type set to #type"() {
        given:
        def responseData = ResponseData.builder()
                .type(type)
                .result(objectMapper.valueToTree(result))
                .build()

        when:
        def list = responseData.toDomain()

        then:
        list.size() == 1
        list.iterator().next() in klass

        where:
        type                        | result                                || klass
        "JourneyPatternPointOnLine" | [exampleJourneyPatternPointOnLine()]  || JourneyPatternPointOnLine
        "StopPoint"                 | [exampleStopPoint()]                  || StopPoint
    }
}
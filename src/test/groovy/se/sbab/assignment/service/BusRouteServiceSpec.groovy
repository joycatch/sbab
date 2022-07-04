package se.sbab.assignment.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class BusRouteServiceSpec extends Specification {

    @Autowired
    BusRouteService busRouteService

    def "should have fetched and populated DB with JourneyPatternPointOnLine entities once application context has been initialized"() {
        expect:
        busRouteService.journeyPatternPointOnLineRepository.count() > 0
    }

    def "should have fetched and populated DB with StopPoint entities once application context has been initialized"() {
        expect:
        busRouteService.stopPointRepository.count() > 0
    }

    def "returned list should be of correct #length when invoking findRoutesWithMostStops"() {
        given:
        def routes = busRouteService.findRoutesWithMostStops(length)

        expect:
        routes.size() == (length < 0 ? 0 : length)

        where:
        length << [-100, -5, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]
    }

    def "the route with the most stops should have at least 5 StopPoints"() {
        given:
        def list = busRouteService.findRoutesWithMostStops(1)
        def lineNumber = list.iterator().next().getLineNumber()
        def stopPoints = busRouteService.findStopPoints(lineNumber)

        expect:
        stopPoints.size() >= 5
    }
}
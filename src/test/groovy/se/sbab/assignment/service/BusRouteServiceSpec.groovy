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
}
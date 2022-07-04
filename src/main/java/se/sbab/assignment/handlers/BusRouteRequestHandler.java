package se.sbab.assignment.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.sbab.assignment.repository.JourneyPatternPointOnLineRepository.LineNumberAndStopPointCount;
import se.sbab.assignment.service.BusRouteService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/bus-route-service")
public class BusRouteRequestHandler {

    @Autowired
    BusRouteService busRouteService;

    @RequestMapping("/most-stop-points")
    public List<LineNumberAndStopPointCount> getRoutesWithMostStops() {
        return getRoutesWithMostStops(10);
    }

    @RequestMapping("/most-stop-points/{amount}")
    public List<LineNumberAndStopPointCount> getRoutesWithMostStops(@PathVariable(value="amount") Integer amount) {
        return busRouteService.findRoutesWithMostStops(amount);
    }

    @RequestMapping("/stop-points/{lineNumber}")
    public List<String> getStopPoints(@PathVariable(value="lineNumber") Integer lineNumber) {
        return busRouteService.findStopPoints(lineNumber);
    }
}

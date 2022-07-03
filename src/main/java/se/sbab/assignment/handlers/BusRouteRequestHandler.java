package se.sbab.assignment.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.sbab.assignment.repository.JourneyPatternPointOnLineRepository.LineNumberAndCount;
import se.sbab.assignment.service.BusRouteService;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/bus-route-service")
public class BusRouteRequestHandler {

    @Autowired
    BusRouteService busRouteService;

    @RequestMapping("/most-stop-points/{amount}")
    public List<LineNumberAndCount> getRoutesWithMostStops(@PathVariable(value="amount") Optional<Integer> amount) {
        return busRouteService.findRoutesWithMostStops(amount.isPresent() ? amount.get() : 10);
    }

    @RequestMapping("/stop-points/{lineNumber}")
    public List<String> getStopPoints(@PathVariable(value="lineNumber") Integer lineNumber) {
        return busRouteService.findStopPoints(lineNumber);
    }
}

package se.sbab.assignment.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.sbab.assignment.domain.JourneyPatternPointOnLine;
import se.sbab.assignment.domain.StopPoint;
import se.sbab.assignment.gateway.BusRouteGateway;
import se.sbab.assignment.repository.JourneyPatternPointOnLineRepository;
import se.sbab.assignment.repository.JourneyPatternPointOnLineRepository.LineNumberAndStopPointCount;
import se.sbab.assignment.repository.StopPointRepository;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class BusRouteService {

    @Autowired
    BusRouteGateway busRouteGateway;

    @Autowired
    JourneyPatternPointOnLineRepository journeyPatternPointOnLineRepository;

    @Autowired
    StopPointRepository stopPointRepository;

    @PostConstruct
    public void fetchAndStoreExternalDataInDB() {
        List<JourneyPatternPointOnLine> journeyPatternPointOnLine = busRouteGateway.getJourneyPatternPointOnLine();
        journeyPatternPointOnLineRepository.saveAll(journeyPatternPointOnLine);

        List<StopPoint> stopPoints = busRouteGateway.getStopPoints();
        stopPointRepository.saveAll(stopPoints);
    }

    public List<LineNumberAndStopPointCount> findRoutesWithMostStops(int amount) {
        if (amount < 0) {
            return Collections.emptyList();
        }
        return journeyPatternPointOnLineRepository.findRoutesWithMostStops(amount);
    }

    public List<String> findStopPoints(int lineNumber) {
        return stopPointRepository.findStopPoints(lineNumber);
    }

}

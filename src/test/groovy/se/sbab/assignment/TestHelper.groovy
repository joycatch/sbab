package se.sbab.assignment

import se.sbab.assignment.domain.JourneyPatternPointOnLine
import se.sbab.assignment.domain.StopPoint

class TestHelper {

    static exampleStopPoint() {
        def number = Math.abs(new Random().nextInt() % 100) + 1
        StopPoint.builder()
                .stopPointNumber(number)
                .stopPointName("Stop Point #" + number)
                .build()
    }

    static exampleJourneyPatternPointOnLine() {
        JourneyPatternPointOnLine.builder()
                .id(Math.abs(new Random().nextInt() % 100) + 1) // 1 - 100
                .lineNumber(Math.abs(new Random().nextInt() % 1000) + 1) // 1 - 1000
                .directionCode(Math.abs(new Random().nextInt() % 2) + 1) // 1 - 2
                .journeyPatternPointNumber(Math.abs(new Random().nextInt() % 100) + 1) // 1 - 100
                .build()
    }
}

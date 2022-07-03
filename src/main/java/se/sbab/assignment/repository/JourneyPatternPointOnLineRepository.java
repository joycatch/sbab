package se.sbab.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.sbab.assignment.domain.JourneyPatternPointOnLine;

import java.util.List;

public interface JourneyPatternPointOnLineRepository extends JpaRepository<JourneyPatternPointOnLine, Integer> {

    @Query(value="SELECT LINE_NUMBER as lineNumber, COUNT(JOURNEY_PATTERN_POINT_NUMBER) AS stopPointCount FROM JOURNEY_PATTERN_POINT_ON_LINE GROUP BY LINE_NUMBER ORDER BY stopPointCount DESC LIMIT 0, :amount", nativeQuery=true)
    List<LineNumberAndCount> findRoutesWithMostStops(@Param("amount") Integer amount);

    public static interface LineNumberAndCount {
        Integer getLineNumber();
        Integer getStopPointCount();
    }
}

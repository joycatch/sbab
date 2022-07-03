package se.sbab.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.sbab.assignment.domain.StopPoint;

import java.util.List;

public interface StopPointRepository extends JpaRepository<StopPoint, Integer> {

    @Query(value="SELECT DISTINCT STOP_POINT_NAME FROM STOP_POINT WHERE STOP_POINT_NUMBER IN (SELECT JOURNEY_PATTERN_POINT_NUMBER FROM JOURNEY_PATTERN_POINT_ON_LINE WHERE LINE_NUMBER = :lineNumber)", nativeQuery=true)
    List<String> findStopPoints(@Param("lineNumber") Integer lineNumber);

}

package se.sbab.assignment.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "JourneyPatternPointOnLine")
@JsonIgnoreProperties(ignoreUnknown = true)
public class JourneyPatternPointOnLine  {

    @Id
    @GeneratedValue
    public int id;

    @Column
    public int lineNumber;

    @Column
    public int directionCode;

    @Column
    public int journeyPatternPointNumber;
}

package se.sbab.assignment.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "StopPoint")
@JsonIgnoreProperties(ignoreUnknown = true)
public class StopPoint {

    @Id
    public int stopPointNumber;

    @Column
    public String stopPointName;
}

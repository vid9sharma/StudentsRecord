package ca.lambton.c0772709_Project;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Setter
@Table(name = "students")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Size(min = 4, max = 8)
	@NotNull
	private String studentNo;

	@Size(min = 1, max = 30)
	@NotNull
	private String firstName;

	@Size(min = 1, max = 30)
	@NotNull
	private String lastName;

	@Min(0)
	@Max(100)
	@NotNull
	private float score;

	@Size(min = 1, max = 20)
	@NotNull
	private String semester;

	@Min(1990)
	@Max(2021)
	@NotNull
	private int year;

	@Builder
	@JsonCreator
	private static Student of(@JsonProperty("id") int id, 
							@JsonProperty("studentNo") String studentNo,
							@JsonProperty("firstName") String firstName, 
							@JsonProperty("lastName") String lastName,
							@JsonProperty("score") float score, 
							@JsonProperty("semester") String semester,
							@JsonProperty("year") int year) {
		
		return new Student(id, studentNo, firstName, lastName, score, semester, year);
	}
}

package job.com.jobsearch.Entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Job_search")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@EqualsAndHashCode(exclude = { "company", "title", "location" })
public class JobEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  Integer Id;

  @Column(name = "Applied_date")
  String dateTimeUtc; // Timestamp dateTimeUtc;
  String company; // Advertiser name;
  String title; // Job title;
  String location; // Job location

}

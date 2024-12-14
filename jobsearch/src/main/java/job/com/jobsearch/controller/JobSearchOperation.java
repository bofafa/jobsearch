package job.com.jobsearch.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import job.com.jobsearch.model.JobDTO;

public interface JobSearchOperation {
  @GetMapping ("jobsdb/graphql")
  ResponseEntity<JobDTO> getJobDTOs();

  
}

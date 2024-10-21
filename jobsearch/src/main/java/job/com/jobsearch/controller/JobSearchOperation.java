package job.com.jobsearch.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

import job.com.jobsearch.model.JobDTO;

public interface JobSearchOperation {
  @GetMapping ("/graphql")
    List<JobDTO> getJobDTOs();
}

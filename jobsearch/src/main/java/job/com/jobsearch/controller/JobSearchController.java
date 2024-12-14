package job.com.jobsearch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import job.com.jobsearch.Service.JobSearchService;
import job.com.jobsearch.model.JobDTO;

@RestController
public class JobSearchController implements JobSearchOperation{

  @Autowired
  private JobSearchService jobService;

  @Override
  public ResponseEntity<JobDTO> getJobDTOs() {
    return this.jobService.getJobDTOs();
  }

}

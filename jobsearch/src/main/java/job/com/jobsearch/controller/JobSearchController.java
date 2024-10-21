package job.com.jobsearch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import job.com.jobsearch.Service.JobService;
import job.com.jobsearch.model.JobDTO;

@RestController
public class JobSearchController implements JobSearchOperation{

  @Autowired
  private JobService jobService;

  @Override
  public List<JobDTO> getJobDTOs() {
    return this.jobService. getJobDTOs();
  }

}

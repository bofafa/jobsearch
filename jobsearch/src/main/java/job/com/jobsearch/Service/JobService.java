package job.com.jobsearch.Service;

import java.util.List;

import job.com.jobsearch.model.JobDTO;

public interface JobService {
  
    List<JobDTO> getJobDTOs();
}

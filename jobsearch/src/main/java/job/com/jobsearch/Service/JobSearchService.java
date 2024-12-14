package job.com.jobsearch.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import job.com.jobsearch.Entity.JobEntity;
import job.com.jobsearch.model.JobDTO;

public interface JobSearchService {

    ResponseEntity<JobDTO> getJobDTOs();


}


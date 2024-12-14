package job.com.jobsearch.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import job.com.jobsearch.Entity.JobEntity;
import job.com.jobsearch.Service.JobSearchService;
import job.com.jobsearch.mapper.JobSearchMapper;
import job.com.jobsearch.model.JobDTO;
import job.com.jobsearch.repository.JobSearchRepository;

@Component
public class AppStartRunner implements CommandLineRunner {

  @Autowired
  private JobSearchService jobSearchService;

  @Autowired
  private JobSearchMapper jobSearchMapper;

  @Autowired
  private JobSearchRepository jobSearchRepository;

  @Override
  public void run(String... args) throws Exception {
    ResponseEntity<JobDTO> jobs = this.jobSearchService.getJobDTOs();
    if (jobs.getBody() != null && jobs.getBody().getData() != null) {
      List<JobEntity> refreshJobData = jobs.getBody().getData().getViewer().getAppliedJobs()
          .getEdges().stream()//
          .map(edge -> jobSearchMapper.map(edge))//
          .collect(Collectors.toList());//

      if (jobSearchRepository.findAll().isEmpty())
        jobSearchRepository.saveAll(refreshJobData);

      // exist data
      JobEntity lastExistData = jobSearchRepository.findAll().get(jobSearchRepository.findAll().size() - 1);
      List<JobEntity> newDataList = new ArrayList<>();

      int lastJobIndex = refreshJobData.indexOf(lastExistData);
      if (lastJobIndex != -1 && lastJobIndex < refreshJobData.size() - 1) {
        newDataList.addAll(refreshJobData.subList(lastJobIndex + 1, refreshJobData.size()));
      }

      // Save all new job entities to the database
      jobSearchRepository.saveAll(newDataList);
    }
  }
}
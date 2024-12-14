package job.com.jobsearch.repository;

import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import job.com.jobsearch.Entity.JobEntity;


@Repository
public interface JobSearchRepository extends JpaRepository <JobEntity, Integer > {
  
}

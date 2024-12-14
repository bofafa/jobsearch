package job.com.jobsearch.mapper;

import java.util.Optional;

import org.springframework.stereotype.Component;

import job.com.jobsearch.Entity.JobEntity;
import job.com.jobsearch.model.JobDTO;

@Component
public class JobSearchMapper {
  
public JobEntity map(JobDTO.Data.Viewer.AppliedJobs.Edge edge){
   JobDTO.Data.Viewer.AppliedJobs.Edge.Node node = edge.getNode();
            JobEntity jobEntity = JobEntity.builder()
                .dateTimeUtc(Optional.ofNullable(node.getAppliedAt())
                    .map(JobDTO.Data.Viewer.AppliedJobs.Edge.Node.AppliedAt::getDateTimeUtc)
                    .orElse(null))
                .company(Optional.ofNullable(node.getJob())
                    .map(JobDTO.Data.Viewer.AppliedJobs.Edge.Node.Job::getAdvertiser)
                    .map(JobDTO.Data.Viewer.AppliedJobs.Edge.Node.Job.Advertiser::getName)
                    .orElse(null))
                .title(Optional.ofNullable(node.getJob())
                    .map(JobDTO.Data.Viewer.AppliedJobs.Edge.Node.Job::getTitle)
                    .orElse(null))
                .location(Optional.ofNullable(node.getJob())
                    .map(JobDTO.Data.Viewer.AppliedJobs.Edge.Node.Job::getLocation)
                    .map(JobDTO.Data.Viewer.AppliedJobs.Edge.Node.Job.Location::getLabel)
                    .orElse(null))
                .build();
            return jobEntity;
}
  }

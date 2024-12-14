package job.com.jobsearch.Service.Impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import job.com.jobsearch.Service.JobSearchService;
import job.com.jobsearch.model.JobDTO;
import job.com.jobsearch.repository.JobSearchRepository;

@Service
public class JobSearchServiceImpl implements JobSearchService {

  @Autowired
  private JobSearchRepository jobSearchRepository;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  @Qualifier(value = "JobSearchTemplate")
  private RestTemplate restTemplate;

  @Value("${api.jph.domain}")
  private String jobDomain;

  @Value("${api.jph.endpoints.pathA}")
  private String jobsEndpoint;

  @Value("${api.jph.token}")
  private String token;

  @Override
  public ResponseEntity<JobDTO> getJobDTOs() {
    String url = UriComponentsBuilder.newInstance()
        .scheme("https")
        .host(jobDomain)
        .path(jobsEndpoint)
        .toUriString();

   // Build the request headers
   HttpHeaders headers = new HttpHeaders();
   headers.add("Origin", "https://hk.jobsdb.com");
   headers.add("Referer", "https://hk.jobsdb.com/my-activity/applied-jobs");
   headers.add("Accept-Encoding", "gzip, deflate, br, zstd");
   headers.add("Accept-Language", "en-US,en;q=0.9,ja;q=0.8");
   headers.add("sec-ch-ua", "\"Google Chrome\";v=\"129\", \"Not=A?Brand\";v=\"8\", \"Chromium\";v=\"129\"");
   headers.add("sec-ch-ua-mobile", "?0");
   headers.add("sec-ch-ua-platform", "\"macOS\"");
   headers.add("sec-fetch-dest", "empty");
   headers.add("sec-fetch-mode", "cors");
   headers.add("sec-fetch-site", "same-origin");
   headers.add("x-seek-site", "ca-myjobs-ui");
   headers.add("x-seek-web-version", "7877.");
   headers.add("Content-Type", "application/json");
   headers.add("Accept", "application/json");
   headers.setBearerAuth(token);

   // Build the request body
   String requestBody = "{\n" +
           "  \"operationName\": \"GetAppliedJobs\",\n" +
           "  \"query\": \"query GetAppliedJobs($first: Int, $locale: Locale!, $timezone: Timezone!, $zone: Zone!, $sortBy: AppliedJobSortByInput) {\\n  viewer {\\n    id\\n    appliedJobs(first: $first, locale: $locale, sortBy: $sortBy) {\\n      edges {\\n        node {\\n          id\\n          hasAppliedWithResume\\n          hasAppliedWithCoverLetter\\n          isExternal\\n          isActive\\n          notes\\n          events {\\n            status\\n            timestamp {\\n              dateTimeUtc\\n              shortAbsoluteLabel(timezone: $timezone, locale: $locale)\\n              __typename\\n            }\\n            __typename\\n          }\\n          appliedAt {\\n            dateTimeUtc\\n            shortAbsoluteLabel(timezone: $timezone, locale: $locale)\\n            __typename\\n          }\\n          job {\\n            id\\n            title\\n            location {\\n              label(locale: $locale, type: SHORT)\\n              __typename\\n            }\\n            abstract\\n            createdAt {\\n              dateTimeUtc\\n              label(context: JOB_POSTED, length: SHORT, timezone: $timezone, locale: $locale)\\n              __typename\\n            }\\n            advertiser {\\n              id\\n              name(locale: $locale)\\n              __typename\\n            }\\n            salary {\\n              label\\n              currencyLabel(zone: $zone)\\n              __typename\\n            }\\n            products {\\n              branding {\\n                logo {\\n                  url\\n                  __typename\\n                }\\n                __typename\\n              }\\n              __typename\\n            }\\n            tracking {\\n              hasRoleRequirements\\n              __typename\\n            }\\n            __typename\\n          }\\n          __typename\\n        }\\n        __typename\\n      }\\n      __typename\\n    }\\n    __typename\\n  }\\n}\",\n" +
           "  \"variables\": {\n" +
           "    \"first\": 100,\n" +
           "    \"locale\": \"en-HK\",\n" +
           "    \"timezone\": \"Asia/Hong_Kong\",\n" +
           "    \"zone\": \"asia-1\",\n" +
           "    \"sortBy\": {\n" +
           "      \"applicationDate\": \"DESC\"\n" +
           "    }\n" +
           "  }\n" +
           "}";

   // Create the request entity
   HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

   // Execute the request
   ResponseEntity<byte[]> response = restTemplate.exchange(
           url,
           HttpMethod.POST,
           request,
           byte[].class
   );

   // Log response details
//    System.out.println("Response Headers: " + response.getHeaders());
   System.out.println("Response Status Code: " + response.getStatusCode());

   // Handle gzipped response
   String responseString = "";
   try {
       if (response.getHeaders().getFirst(HttpHeaders.CONTENT_ENCODING) != null &&
               response.getHeaders().getFirst(HttpHeaders.CONTENT_ENCODING).contains("gzip")) {
           InputStream gzipStream = new GZIPInputStream(new ByteArrayInputStream(response.getBody()));
           BufferedReader reader = new BufferedReader(new InputStreamReader(gzipStream, StandardCharsets.UTF_8));
           StringBuilder out = new StringBuilder();
           String line;
           while ((line = reader.readLine()) != null) {
               out.append(line);
           }
           responseString = out.toString();
       } else {
           responseString = new String(response.getBody(), StandardCharsets.UTF_8);
       }
   } catch (Exception e) {
       e.printStackTrace();
   }

   System.out.println("Response Body: " + responseString);

   // Deserialize the response
   JobDTO jobDTO = null;
   try {
       jobDTO = objectMapper.readValue(responseString, JobDTO.class);
   } catch (JsonProcessingException e) {
       e.printStackTrace();
   }

   return new ResponseEntity<>(jobDTO, response.getStatusCode());

  }

}

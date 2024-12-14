package job.com.jobsearch.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class JobDTO {
    private Data data;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Data {
        private Viewer viewer;

        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        @Builder
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Viewer {
            private long id;
            private AppliedJobs appliedJobs;

            @Getter
            @Setter
            @AllArgsConstructor
            @NoArgsConstructor
            @Builder
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class AppliedJobs {
                private List<Edge> edges;

                @Getter
                @Setter
                @AllArgsConstructor
                @NoArgsConstructor
                @Builder
                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class Edge {
                    private Node node;

                    @Getter
                    @Setter
                    @AllArgsConstructor
                    @NoArgsConstructor
                    @Builder
                    @JsonIgnoreProperties(ignoreUnknown = true)
                    public static class Node {
                        private String id;
                        private boolean hasAppliedWithResume;
                        private boolean hasAppliedWithCoverLetter;
                        private boolean isExternal;
                        private boolean isActive;
                        private String notes;
                        private List<Event> events;
                        private AppliedAt appliedAt;
                        private Job job;

                        @Getter
                        @Setter
                        @AllArgsConstructor
                        @NoArgsConstructor
                        @Builder
                        @JsonIgnoreProperties(ignoreUnknown = true)
                        public static class Event {
                            private String status;
                            private Timestamp timestamp;

                            @Getter
                            @Setter
                            @AllArgsConstructor
                            @NoArgsConstructor
                            @Builder
                            @JsonIgnoreProperties(ignoreUnknown = true)
                            public static class Timestamp {
                                private String dateTimeUtc;
                                private String shortAbsoluteLabel;
                            }
                        }

                        @Getter
                        @Setter
                        @AllArgsConstructor
                        @NoArgsConstructor
                        @Builder
                        @JsonIgnoreProperties(ignoreUnknown = true)
                        public static class AppliedAt {
                            private String dateTimeUtc;
                            private String shortAbsoluteLabel;
                        }

                        @Getter
                        @Setter
                        @AllArgsConstructor
                        @NoArgsConstructor
                        @Builder
                        @JsonIgnoreProperties(ignoreUnknown = true)
                        public static class Job {
                            private String id;
                            private String title;
                            private Location location;
                            private String jobAbstract;
                            private CreatedAt createdAt;
                            private Advertiser advertiser;
                            private Salary salary;
                            private Products products;
                            private Tracking tracking;

                            @Getter
                            @Setter
                            @AllArgsConstructor
                            @NoArgsConstructor
                            @Builder
                            @JsonIgnoreProperties(ignoreUnknown = true)
                            public static class Location {
                                private String label;
                            }

                            @Getter
                            @Setter
                            @AllArgsConstructor
                            @NoArgsConstructor
                            @Builder
                            @JsonIgnoreProperties(ignoreUnknown = true)
                            public static class CreatedAt {
                                private String dateTimeUtc;
                                private String label;
                            }

                            @Getter
                            @Setter
                            @AllArgsConstructor
                            @NoArgsConstructor
                            @Builder
                            @JsonIgnoreProperties(ignoreUnknown = true)
                            public static class Advertiser {
                                private String id;
                                private String name;
                            }

                            @Getter
                            @Setter
                            @AllArgsConstructor
                            @NoArgsConstructor
                            @Builder
                            @JsonIgnoreProperties(ignoreUnknown = true)
                            public static class Salary {
                                private String label;
                                private String currencyLabel;
                            }

                            @Getter
                            @Setter
                            @AllArgsConstructor
                            @NoArgsConstructor
                            @Builder
                            @JsonIgnoreProperties(ignoreUnknown = true)
                            public static class Products {
                                private Branding branding;

                                @Getter
                                @Setter
                                @AllArgsConstructor
                                @NoArgsConstructor
                                @Builder
                                @JsonIgnoreProperties(ignoreUnknown = true)
                                public static class Branding {
                                    private Logo logo;

                                    @Getter
                                    @Setter
                                    @AllArgsConstructor
                                    @NoArgsConstructor
                                    @Builder
                                    @JsonIgnoreProperties(ignoreUnknown = true)
                                    public static class Logo {
                                        private String url;
                                    }
                                }
                            }

                            @Getter
                            @Setter
                            @AllArgsConstructor
                            @NoArgsConstructor
                            @Builder
                            @JsonIgnoreProperties(ignoreUnknown = true)
                            public static class Tracking {
                                private boolean hasRoleRequirements;
                            }
                        }
                    }
                }
            }
        }
    }

}

package job.com.jobsearch.model;

import java.util.List;

public class JobDTO {
    private Data data;

    public static class Data {
        private Viewer viewer;

        public static class Viewer {
            private long id;
            private AppliedJobs appliedJobs;

            public static class AppliedJobs {
                private List<Edge> edges;

                public static class Edge {
                    private Node node;

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

                        public static class Event {
                            private String status;
                            private Timestamp timestamp;

                            public static class Timestamp {
                                private String dateTimeUtc;
                                private String shortAbsoluteLabel;
                                private String __typename;
                            }
                        }

                        public static class AppliedAt {
                            private String dateTimeUtc;
                            private String shortAbsoluteLabel;
                            private String __typename;
                        }

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

                            public static class Location {
                                private String label;
                                private String __typename;
                            }

                            public static class CreatedAt {
                                private String dateTimeUtc;
                                private String label;
                                private String __typename;
                            }

                            public static class Advertiser {
                                private String id;
                                private String name;
                                private String __typename;
                            }

                            public static class Salary {
                                private String label;
                                private String currencyLabel;
                                private String __typename;
                            }

                            public static class Products {
                                private Branding branding;

                                public static class Branding {
                                    private Logo logo;

                                    public static class Logo {
                                        private String url;
                                        private String __typename;
                                    }

                                    private String __typename;
                                }

                                private String __typename;
                            }

                            public static class Tracking {
                                private boolean hasRoleRequirements;
                                private String __typename;
                            }

                            private String __typename;
                        }

                        private String __typename;
                    }

                    private String __typename;
                }

                private String __typename;
            }

            private String __typename;
        }
    }

    // Getters and setters
    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}

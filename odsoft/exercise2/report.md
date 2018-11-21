# Performance Report

## Testing environment

For the purpose of this report's accuracy the following environment was setup:

* Both pipelines were configured on the same computer and nothing else was running at the time of the builds.
* The only difference between both pipelines is the pipeline script itself.
* The same plugins were used for the same actions in both pipelines, being the only difference in the order stages are executed.

Based on this environment all the obtained values are valid for comparing the performance of the pipelines against each other.

## Component 4 Analysis - Jenkinsfile Sequential Build

### Pipeline Sequencial Sketch

![Sequential Diagram][component3/sequential.png]

### Sucessful Build Average Time

After around 10 successful builds the average build time for this parallel pipeline was:

    6min 13s (373s)

## Component 4 Analysis - Jenkinsfile Parallel Build

**Objective:** Configure a Jenkins Pipeline using a Jenkinsfile, performing a parallel
build.

### Pipeline Parallel Sketch

![Pipeline Diagram][component4/pipeline.png]

### Sucessful Build Average Time

After around 10 successful builds the average build time for this parallel pipeline was:

    4min 13s (253s)

## Conclusion

Having used the environment that was refered in the beginning of this documment all the obtain values are valid for a fair comparison of both pipelines' performances.
Based on the average build times we got (4min 21s for parallel and 6min 12s for the sequential, ) we can safely say that Jenkinsfile Parallel Build is better regarding the performance, by nearly a 32% margin.
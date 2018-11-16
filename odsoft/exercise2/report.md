# Performance Report

## Testing environment

For the purpose of this report's accuracy the following environment was setup:

* both pipelines were configured on the same computer and nothing else was running at the time of the builds.
* The only difference between both pipelines is the pipeline script itself.
* The same plugins were used for the same actions in both pipelines, being the only difference in the order stages are executed.

Based on this environment all the obtained values are valid for comparing the performance of the pipelines against each other.

## Component 4 Analysis - Jenkinsfile Sequential Build

### Pipeline Sequencial Sketch

![Pipeline Diagram][component3/pipeline.png]

### Sucessful Build Average Time

After around XX successful builds the average build time for this parallel pipeline was:

* insert average time here

## Component 4 Analysis - Jenkinsfile Parallel Build

**Objective:** Configure a Jenkins Pipeline using a Jenkinsfile, performing a parallel
build.

### Pipeline Parallel Sketch

![Pipeline Diagram][component4/pipeline.png]

### Sucessful Build Average Time

After around 10 successful builds the average build time for this parallel pipeline was:

    5min 32s

## Conclusion

Having used the environment that was refered in the beginning of this documment all the obtain values are valid for a fair comparison of both pipelines' performance.
Based on the average build times we got (5min 32s for parallel and Xmin XXs for the sequential, ) we can safely say that XXXX is better regarding the performance.
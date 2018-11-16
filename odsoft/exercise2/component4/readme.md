# Component 4 Analysis - Jenkinsfile Parallel Build

**Objective:** Configure a Jenkins Pipeline using a Jenkinsfile, performing a parallel
build.

## Pipeline Sketch

![Pipeline Diagram][pipeline.png]

## Stages

All stages are executed sequentially by the order they are written, unless specified otherwise.

1. ### Checkout

Checks out all files from the git repository, **can't do anything else before** completing this stage since **everything is in the repository**.

2. ### Preparation

This stage only cleans tests and reports output in order to **avoid errors** further down the road.

3. ### Build

Builds all the code at once, so no need to compile anything else from this point forward. (Also generates the war file)

4. ### Parallel Stages

Since all code is now compiled and the **next steps all depended on it**, they can now be ran all together.
Just running automated tests and publishing code related artifacts.
It also doesn't make sense running any of these in case the previous fails.

These stages are:

* Archiving war file
* Building and publishing Javadoc
* Run Unit tests and publish reports
* Run Integration tests and publish reports
* Run Mutation tests and publish reports

5. ### Deploy

Deploys the application to a TomCat Server.
This **should not be done** before passing all the tests.

6. ### System Tests

In this case is just a simple smoke test to check if the application was indeed deployed.
Can only be done **after** deploy.

7. ### Manual Acceptance Tests

Waits for user to accept or reject the build.
Should only be ran **after** checking the deploy was successful.

8. ### Continuous Integration Feedback

Adds a tag to the commit with the current build number and status.
This **must** run as a **post** action since it has to run **always** even when the build fails so that the tag can be added to the repository.
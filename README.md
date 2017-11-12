# event2calendar - a Spring Boot app on AWS Lambda

This project's target is to provide an introduction to bring a _Spring Boot_ app to _AWS Lambda_ making it serverless 
but keeping all the advantages of Spring's dependency injection and annotation based configurations. By the way this 
project solves one problem which I often faced: attending conferences or festivals with multiple concurring events and 
no easy (digital) way of organizing and Google Calendar support. This project contains parsers for some sites where I 
can just click a browser bookmarklet and send an event to this service which parses the site and extracts an event which 
then can be imported to Google Calendar.

## Quick Start
1. Setup a [AWS CodeStar](https://aws.amazon.com/de/codestar/) account and choose the _Java Spring_ template for 
"Web service" and "AWS Lambda". Add the commit state of the event2calendar repository to your newly configured CodeStar 
repo.
1. Install [Docker](https://www.docker.com/get-docker) and [AWS SAM Local](#aws-sam-local) for testing.
1. Start editing in the folder:
   1. `lambda-modules/lambda.backend` for your lambda function
   1. `docs` for your frontend (served by GitHub Pages)
   1. `cfn` for your CloudFormation setup

## Details
### Motivation
Developing for AWS Lambda always required you to test your current dev snapshot within and against the AWS Lambda 
environment resulting in lots of manual or hopefully automated CloudFormation deployments. But there is another point 
which I personally was struggling with: From my DevOps perspective and years of experience developing and deploying Java 
(micro-) services there was no easy transformation between developing a standalone Spring Boot app and going serverless. 
You always would need to make major architectural changes to your code to switch between both worlds. This had to be changed!

### Spring Boot and AWS Lambda
This project is based on [this](https://github.com/awslabs/aws-serverless-java-container) project, a Java wrapper made by 
AWS Labs. It basically provides a generic entrypoint (Lambda handler) for your serverless function. You then provide your 
Spring configuration and upon instantiation your `ApplicationContext` is brought to life.

I then took this approach to create a Maven multi module project. One submodule containing the Lambda handler code and 
one pretty simple submodule containing everything to run a `SpringBootApplication`.

You now can easily switch between the two worlds running the Spring Boot application out of your IDE, as standalone Java 
application or even as Docker container or on the other side deployed as a Lambda function. You can take advantage of 
low AWS fees for prototypes or small projects and take advantage of the great scaling capabilities of AWS when you suddenly 
get a raise of users. However you can always switch to a big standalone app server or even facilitate container orchestration 
when your costs of billions of Lambda invocations would require you to rethink your hosting concept.

### AWS SAM Local
Recently AWS pitches its newest Labs development: [AWS SAM Local](https://github.com/awslabs/aws-sam-local). With it you 
can run your Lambda code locally through the use of Docker. You can utilize the tool not only to send specific events to 
your function but also to simulate an AWS API Gateway, i.e. you use local endpoints and AWS SAM Local spawns your Lambda 
instances.

#### There's a bug
When using _sam_ I realized there is a problem using the latest version 0.2.2 when using local API Gateway functionality. 
I submitted pull request [#189](https://github.com/awslabs/aws-sam-local/pull/189) but it is not yet merged. Until the 
next official release a made my own bugfix release [available](https://github.com/adulescentulus/aws-sam-local/releases/tag/v0.2.2-STAGEFIX-2).

#### Local API Test
`./aws-sam-local.exe local start-api -t cfn/aws-resources.yml` 
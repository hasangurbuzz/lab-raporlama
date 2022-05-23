# Lab Reporting
Lab Reporting app aims to help scientists who works in labs.
It stores reports, patients and laboratorians.



## Main Features

- Stores report data
- Secures data from unauthorized person
- Avoids from Cross Site Request Forgery
- Runnable in a Docker Container


## Techs Used

**Client:** Thymeleaf with Bootstrap

**Server:** Spring Boot with in-memory H2 Database


## Installation

### Install with Maven
 Prerequisites

 - Java 17 or newer

```bash
  > git clone https://github.com/hasangurbuzz/lab-raporlama.git
  > cd lab-raporlama
  > mvn spring-boot:run
```
    
### Install with Docker
Prerequisites 
 - Docker Engine

##### Pull image from repository
```bash
  > docker pull hasangurbuz/lab-reporting
```
Make sure to check Docker image is available
```bash
  > docker images
  --------------------------------------------------
  REPOSITORY                  TAG       IMAGE ID        
  hasangurbuz/lab-reporting   0.0.1     3b2167561321
```
Run Docker image in a container
```bash
  > docker run --name {name} -p {PORT}:8080 hasangurbuz/lab-reporting:{tag}
```
Example 
```bash
  > docker run --name labreporting -p 8080:8080 hasangurbuz/lab-reporting:0.0.1
```
##### Giving a name to docker container is optional
```bash
  > docker run -p 8080:8080 hasangurbuz/lab-reporting:0.0.1
```
You can list running containers with
```bash
  > docker ps
  CONTAINER ID   IMAGE                              COMMAND 
  7e160fd5c254   hasangurbuz/lab-reporting:0.0.1    "java.."
```
If container is active you can go to [http://localhost:8080](http://localhost:8080)
---
Also you can change connection port. 

```bash
  > docker run --name labreporting -p 9090:8080 hasangurbuz/lab-reporting:0.0.1
```
To stop running container
```bash
  > docker kill {GIVEN NAME}
```
Or
```bash
  > docker kill {CONTAINER ID}
```
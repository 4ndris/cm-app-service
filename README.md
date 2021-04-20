## Content Manager [![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-ready--to--code-blue?logo=gitpod)](https://gitpod.io/from-referrer/)


<a href="https://gitpod.io/from-referrer/" style="padding: 10px;">
    <img src="https://gitpod.io/button/open-in-gitpod.svg" width="150" alt="Push" align="center">
</a>

#### Requirements
* Java 11 (JDK) (only for debug)
* Maven 3.6+ (only for debug)
* Docker 2.3+

#### Run
Run the application from the following directory: ```${project.home}/docker```

Command: ```docker-compose up```

#### Debug - optional

Run the DB only from the following directory: ```${project.home}/docker```

Run DB: ```docker-compose -f docker-compose-db.yml up```

After DB spin up:

Run application: ```mvn spring-boot:run```

### Gitpod

Enable docker:
run sudo docker-up in a Terminal
run docker-compose up in another Terminal


#### URLs

Welcome page: ```http://localhost:8080/```

Swagger UI: ```http://localhost:8080/swagger-ui.html```

Management: ```http://localhost:8080/actuator``` 

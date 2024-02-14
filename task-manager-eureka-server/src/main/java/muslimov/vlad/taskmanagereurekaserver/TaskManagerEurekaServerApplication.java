package muslimov.vlad.taskmanagereurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TaskManagerEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskManagerEurekaServerApplication.class, args);
    }

}

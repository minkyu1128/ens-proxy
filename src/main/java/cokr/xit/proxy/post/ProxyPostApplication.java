package cokr.xit.proxy.post;

import cokr.xit.proxy.post.filter.CustomGlobalFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProxyPostApplication {

    public static void main(String[] args) {
//        SpringApplication.run(ProxyPostApplication.class, args);

        // PID Setting ...
        SpringApplication application = new SpringApplication(ProxyPostApplication.class);
        application.addListeners(new ApplicationPidFileWriter());	//PID(Process ID 작성)
        application.run(args);
    }


    @Bean
    public CustomGlobalFilter customFilter() {
        return new CustomGlobalFilter();
    }

}

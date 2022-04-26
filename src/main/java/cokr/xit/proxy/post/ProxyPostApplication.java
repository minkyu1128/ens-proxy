package cokr.xit.proxy.post;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
public class ProxyPostApplication {

    public static void main(String[] args) {
//        SpringApplication.run(ProxyPostApplication.class, args);

        // PID Setting ...
        SpringApplication application = new SpringApplication(ProxyPostApplication.class);
        application.addListeners(new ApplicationPidFileWriter());	//PID(Process ID 작성)
        application.run(args);
    }


//    @Bean
//    public GlobalCustomFilter customFilter() {
//        return new GlobalCustomFilter();
//    }

}

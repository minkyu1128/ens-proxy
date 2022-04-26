package cokr.xit.proxy.proxypost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class ProxyPostApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProxyPostApplication.class, args);
    }


//    @Bean
//    public GlobalCustomFilter customFilter() {
//        return new GlobalCustomFilter();
//    }

}

package cokr.xit.proxy.post.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.RewritePathGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class RewriteFilter extends AbstractGatewayFilterFactory<RewriteFilter.Config> {

    public RewriteFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        // grab configuration from Config object
        return (exchange, chain) -> {

            List<String> param1 = Arrays.asList("dummy");
            List<String> param2 = exchange.getRequest().getHeaders().get("XIT-PARAM2");
            String uri = exchange.getRequest().getURI().toString();
            String rewritePath = uri + "/" + param1.get(0);


            log.info("===============================================================");
            log.info("URI: " + exchange.getRequest().getURI().toString());
            log.info("Re-Write-Path: " + rewritePath);
            log.info("===============================================================");


            //If you want to build a "pre" filter you need to manipulate the
            //request before calling chain.filter
            ServerHttpRequest.Builder builder = exchange.getRequest().mutate();

            //use builder to manipulate the request
//            return chain.filter(exchange.mutate().request(builder.path("/nice/ci").build()).build());
            return chain.filter(exchange.mutate().request(builder.path(rewritePath).build()).build());
        };
    }

    public static class Config {
        //Put the configuration properties for your filter here
    }
}

package cokr.xit.proxy.proxypost.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.RewritePathGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RewriteFilter extends AbstractGatewayFilterFactory<RewriteFilter.Config> {

    public RewriteFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        // grab configuration from Config object
        return (exchange, chain) -> {

            List<String> param1 = exchange.getRequest().getHeaders().get("XIT-PARAM1");
            String param2 = String.valueOf(exchange.getRequest().getHeaders().get("XIT-PARAM2"));
            String uri = exchange.getRequest().getURI().toString();


            System.out.println("===============================================================");
            System.out.println("param1: " + param1.toString());
            System.out.println("param2: " + param2);
            System.out.println("URI: " + exchange.getRequest().getURI().toString());
            System.out.println("TransformUrl: " + exchange.transformUrl("/kko/bill/url"));
            System.out.println("===============================================================");


            exchange.getApplicationContext().getBean(RewritePathGatewayFilterFactory.class)
                    .apply(c -> c.setRegexp(uri).setReplacement(uri + "/" + param1.get(0)));

            //If you want to build a "pre" filter you need to manipulate the
            //request before calling chain.filter
            ServerHttpRequest.Builder builder = exchange.getRequest().mutate();

            //use builder to manipulate the request
            return chain.filter(exchange.mutate().request(builder.path(uri + "/" + param1.get(0)).build()).build());
//            return chain.filter(exchange.mutate().request(builder.path("/nice/ci").build()).build());
        };
    }

    public static class Config {
        //Put the configuration properties for your filter here
    }
}

package cokr.xit.proxy.post.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
public class CustomGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        List<String> param1 = exchange.getRequest().getHeaders().get("XIT-PARAM1");
        String param2 = String.valueOf(exchange.getRequest().getHeaders().get("XIT-PARAM2"));
        String uri = exchange.getRequest().getURI().toString();


        log.info("===============================================================");
//        log.info("param1: " + param1==null?"null":param1.toString());
        log.info("param2: " + param2);
        log.info("URI: " + uri);
        log.info("===============================================================");

//        GatewayFilter f = exchange.getApplicationContext().getBean(RewritePathGatewayFilterFactory.class)
//                .apply(c -> c.setRegexp(uri).setReplacement(uri+"/"+param1.get(0)));

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

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
        String uri = exchange.getRequest().getURI().toString();

        log.info("===============================================================================================");
        log.info("===== Request Info... =====");
        log.info("Header: " + exchange.getRequest().getHeaders());
        log.info("URI: " + uri);
        log.info("===============================================================================================");

//        GatewayFilter f = exchange.getApplicationContext().getBean(RewritePathGatewayFilterFactory.class)
//                .apply(c -> c.setRegexp(uri).setReplacement(uri+"/"+param1.get(0)));

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

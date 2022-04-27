package cokr.xit.proxy.post.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

            String path = exchange.getRequest().getPath().toString();

            Map<String, Object> info = config.getMConf().get(path.replaceAll("[^0-9a-zA-Z]", ""));
            String rewritePath = (String) info.get("rewrite_path");
            LinkedHashMap<Integer, String> params = (LinkedHashMap<Integer, String>) info.get("params");
            for(String param : params.values()){
                List<String> values = exchange.getRequest().getHeaders().getOrEmpty(param);
                if(values.isEmpty()) continue;

                rewritePath = rewritePath.replace("{"+ param +"}", values.get(0));
            }



            log.info("===============================================================================================");
            log.info("==== Rewrite... ====");
            log.info("URI: " + exchange.getRequest().getURI().toString());
            log.info("Path: " + path);
            log.info("Re-Write-Path: " + rewritePath);
            log.info("===============================================================================================");


            //If you want to build a "pre" filter you need to manipulate the
            //request before calling chain.filter
            ServerHttpRequest.Builder builder = exchange.getRequest().mutate();

            //use builder to manipulate the request
//            return chain.filter(exchange.mutate().request(builder.path("/nice/ci").build()).build());
            return chain.filter(exchange.mutate().request(builder.path(rewritePath).build()).build());
        };
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Config {
        //Put the configuration properties for your filter here
//        private List<Map<String, String>> listMapExample;
        private Map<String, Map<String, Object>> mConf;
    }
}

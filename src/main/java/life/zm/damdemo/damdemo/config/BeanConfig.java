package life.zm.damdemo.damdemo.config;

import com.bimface.sdk.BimfaceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dup, 2017-11-22
 */
@Configuration
public class BeanConfig {


    private String appKey="5QL7ALlAdQ8I4zzxTr8y2pXT0GYhsW7K";

    private String appSecret="h4UmmasoTtZIqQiUBHyqOxWeIbQTHJE2";

    @Bean
    public BimfaceClient bimfaceClient(){
        return new BimfaceClient(appKey, appSecret);
    }

}

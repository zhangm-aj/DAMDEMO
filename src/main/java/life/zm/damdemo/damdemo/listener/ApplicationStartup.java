package life.zm.damdemo.damdemo.listener;


import life.zm.damdemo.damdemo.Service.ClearDbService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty("clear.open.initiated")
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        ClearDbService clearDbService = event.getApplicationContext().getBean(ClearDbService.class);
        clearDbService.clearFile();
        clearDbService.clearIntegrate();
        clearDbService.clearIntegrateFile();
    }
}
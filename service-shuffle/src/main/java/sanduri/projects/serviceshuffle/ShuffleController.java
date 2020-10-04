package sanduri.projects.serviceshuffle;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
public class ShuffleController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    @Qualifier("eurekaClient")
    private EurekaClient eurekaClient;

    @Value("${service.log.id}")
    private String logServiceId;

    @Value("${service.log.api}")
    private String logServiceApi;

    @PostMapping("/generateArray")
    public List<Integer> generate(@RequestParam Integer numberInput) {

        List<Integer> result = IntStream.rangeClosed(1, numberInput).boxed().collect(Collectors.toList());
        Collections.shuffle(result);

        logObject(result);
        return result;
    }

    @Async
    public void logObject(List<Integer> array) {

        try {
            // GET log service details
            Application application = eurekaClient.getApplication(logServiceId);
            InstanceInfo instanceInfo = application.getInstances().get(0);
            String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + logServiceApi;

            // Add param to request
            MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
            map.add("object", array);

            restTemplate.postForEntity(url, map, null);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
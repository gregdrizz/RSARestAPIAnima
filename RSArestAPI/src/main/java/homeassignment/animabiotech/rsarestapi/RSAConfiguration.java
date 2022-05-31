package homeassignment.animabiotech.rsarestapi;

import homeassignment.animabiotech.rsarestapi.model.RSAService;
import homeassignment.animabiotech.rsarestapi.repository.RSAInstanceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RSAConfiguration {

    @Bean
    public RSAInstanceRepository rsaInstanceRepository(){
        return new RSAInstanceRepository();
    }

    @Bean
    public RSAService rsaService(RSAInstanceRepository rsaInstanceRepository){
        return new RSAService(rsaInstanceRepository);
    }
}

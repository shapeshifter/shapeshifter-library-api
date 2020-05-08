package eu.uftpapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import eu.uftplib.repository.MessageRepository;
import eu.uftplib.service.UftpService;
import eu.uftplib.service.UftpServiceImplementation;

@SpringBootApplication(scanBasePackages = "eu.uftplib,eu.uftpapi")
@EnableJpaRepositories("eu.uftplib")
@EntityScan("eu.uftplib")

public class UftpapiApplication {

    public static void main(String[] args){
        System.out.println("Welcome");
        SpringApplication.run(UftpapiApplication.class, args);
    }

    @Bean
    public UftpService uftpService(MessageRepository messageRepository) {
        return new UftpServiceImplementation(messageRepository);
    }
}

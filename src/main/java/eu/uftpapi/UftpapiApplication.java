package eu.uftpapi;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import eu.uftplib.repository.MessageRepository;
import eu.uftplib.service.UftpService;
import eu.uftplib.service.NewMessageListener;
import eu.uftplib.service.DeliveryStatusListener;
import eu.uftplib.service.DeliveryStatus;
import eu.uftplib.service.UftpServiceImplementation;

@SpringBootApplication(scanBasePackages = "eu.uftplib,eu.uftpapi")
@EnableScheduling()
@EnableJpaRepositories("eu.uftplib")
@EntityScan("eu.uftplib")

public class UftpapiApplication implements NewMessageListener, DeliveryStatusListener {

    public static void main(String[] args){
        System.out.println("Welcome");
        SpringApplication.run(UftpapiApplication.class, args);
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public UftpService uftpService(MessageRepository messageRepository) {
        return new UftpServiceImplementation(messageRepository);
    }

    @Bean
    public CommandLineRunner getRunner(ApplicationContext ctx){
        return (args) -> {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            var i = ctx.getBean(UftpService.class);
            i.addNewMessageListener(this);
            i.addDeliveryStatusListener(this);
        };
    }

    public void newMessage(Long message, String xml) {
        System.out.println("new mesage:::::::: " + message + " - " + xml);
    };

    public void deliveryStatus(Long message, DeliveryStatus status) {
        System.out.println("new status:::::::: " + message + " : " + status);
    };

}

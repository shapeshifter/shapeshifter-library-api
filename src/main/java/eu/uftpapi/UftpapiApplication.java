package eu.uftpapi;

import org.springframework.beans.factory.annotation.Value;
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
import eu.uftplib.service.UftpSigningService;
import eu.uftplib.service.UftpSigningServiceImplementation;
import eu.uftplib.service.UftpParticipantService;
import eu.uftplib.service.UftpParticipantServiceStub;
import eu.uftplib.service.UftpValidationService;
import eu.uftplib.service.UftpValidationServiceImplementation;
import eu.uftplib.service.UftpSendMessageService;
import eu.uftplib.service.UftpSendMessageServiceImplementation;

@SpringBootApplication(scanBasePackages = "eu.uftplib,eu.uftpapi")
@EnableScheduling()
@EnableJpaRepositories("eu.uftplib")
@EntityScan("eu.uftplib")

public class UftpapiApplication implements NewMessageListener, DeliveryStatusListener {

    @Value("${uftplib.role}")
    private String role;
    @Value("${uftplib.privatekey}")
    private String privateKey;

    public static void main(String[] args){
        System.out.println("Welcome");
        SpringApplication.run(UftpapiApplication.class, args);
    }

    @Bean
    public UftpParticipantService uftpParticipantService() {
        return new UftpParticipantServiceStub();
    }

    @Bean
    public UftpSigningService uftpSigningService() {
        return new UftpSigningServiceImplementation(role);
    }

    @Bean
    public UftpSendMessageService uftpSendMessageService(UftpParticipantService uftpParticipantService, UftpSigningService uftpSigningService) {
        return new UftpSendMessageServiceImplementation(uftpParticipantService, uftpSigningService);
    }

    @Bean
    public UftpValidationService uftpValidationService() {
        return new UftpValidationServiceImplementation(role);
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public UftpService uftpService(MessageRepository messageRepository, UftpSendMessageService uftpSendMessageService, UftpValidationService uftpValidationService) {
        return new UftpServiceImplementation(messageRepository, uftpSendMessageService, uftpValidationService, privateKey, 5L);
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

package eu.uftpapi;

import eu.uftpapi.entity.Message;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UftpapiApplication {

    public static void main(String[] args){
        System.out.println("Welcome");
        SpringApplication.run(UftpapiApplication.class, args);
        Message message = new Message();
    }
}

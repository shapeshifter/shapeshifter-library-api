package eu.uftpapi;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import eu.uftplib.service.UftpService;

@Component
public class Housekeeping {

    private UftpService uftpService;

    public Housekeeping(UftpService uftpService) {
        this.uftpService = uftpService;
    }

    @Scheduled(fixedRate = 5000)
	public void housekeeping() {
        System.out.println("The time is now: " + new Date());
        uftpService.houseKeeping();
	}
}
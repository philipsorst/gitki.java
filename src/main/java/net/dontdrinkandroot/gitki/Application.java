package net.dontdrinkandroot.gitki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
@SpringBootApplication
@EnableScheduling
public class Application
{
    public static void main(String args[])
    {
        SpringApplication.run(Application.class, args);
    }
}

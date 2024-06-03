package org.chat.investpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InvestProApplication {


    public static void main(String[] args) {
        Client client = new Client();
        client.menuStart();
    }
}

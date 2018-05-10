package com.jackshenorion.smarts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmartsApplication {

    public static void main(String[] args) {
//        SpringApplication.run(SmartsApplication.class, args);

        PrimeGenerator g = new PrimeGenerator();
        for (int i = 0; i < 10; i++) {
			System.out.println(g.next());
        }
    }
}

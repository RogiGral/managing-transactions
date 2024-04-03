package com.example.managingtransactions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class ManagingTransactionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagingTransactionsApplication.class, args);
    }

}

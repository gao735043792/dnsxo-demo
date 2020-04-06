package com.dnsxo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.dnsxo.mapper.*")
@SpringBootApplication
public class DnsxoDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DnsxoDemoApplication.class, args);
    }

}

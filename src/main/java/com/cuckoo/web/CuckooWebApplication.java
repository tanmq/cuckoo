package com.cuckoo.web;

import com.cuckoo.web.utils.Config;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@MapperScan("com.cuckoo.web.mysql.mapper")
public class CuckooWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(CuckooWebApplication.class, args);
	}

	@PostConstruct
	public void configLoad() throws Exception {
		Config.reload();
	}
}

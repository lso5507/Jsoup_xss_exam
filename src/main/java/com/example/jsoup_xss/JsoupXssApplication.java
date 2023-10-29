package com.example.jsoup_xss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class JsoupXssApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsoupXssApplication.class, args);
	}

}

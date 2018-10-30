package com.zyh.springcloud.jobquartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuartzJobApplication {
    public static void main(String[] args){
    	SpringApplication sa = new SpringApplication(QuartzJobApplication.class);
    	sa.run(args);
        System.out.println( "Hello World!" );
    }
}

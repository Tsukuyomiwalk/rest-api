package vk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class VkApplication {
    public static void main(String[] args) {
        SpringApplication.run(VkApplication.class, args);
    }
}

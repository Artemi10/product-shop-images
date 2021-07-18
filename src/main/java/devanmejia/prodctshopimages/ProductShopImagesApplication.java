package devanmejia.prodctshopimages;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProductShopImagesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductShopImagesApplication.class, args);
    }

}

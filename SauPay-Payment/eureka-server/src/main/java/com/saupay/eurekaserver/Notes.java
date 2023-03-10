package com.saupay.eurekaserver;

public class Notes {
    /*

    Eureka Server Configuration

----    @SpringBootApplication
        @EnableEurekaServer
        public class EurekaServerApplication {

            public static void main(String[] args) {
                SpringApplication.run(EurekaServerApplication.class, args);
            }

        }

----    application.properties

        server.port=8761
        eureka.client.register-with-eureka=false
        eureka.client.fetch-registry=false



Eureka Server Nedir?

    Eureka Server, microservice mimarisindeki servislerin birbirleri ile haberleşmesini sağlayan bir yapıdır.

    Eureka Server ile servislerin birbirleri ile haberleşmesi sağlanırken, servislerin birbirlerine ait ip adreslerini
    kullanmaları gerekmez. Eureka Server, servislerin birbirleri ile haberleşmesini sağlamak için, servislerin
    kendilerine ait bir isim vermesini sağlar. Bu isimler, Eureka Server üzerinde kayıtlıdır. Servisler, Eureka Server
    üzerinde kayıtlı olan isimler ile birbirleri ile haberleşirler. Bu sayede, servislerin birbirleri ile haberleşmesi
    için ip adreslerini kullanmalarına gerek kalmaz. Bu sayede, servislerin birbirleri ile haberleşmesi için ip adreslerini
    kullanmalarına gerek kalmaz.

!!    Microservice ler Eureka ya kayıt olmak için Eureka Client kullanırlar.
        @EnableEurekaClient

        Daha sonra application.properties dosyasında config ayarları yapılır.

        #Eureka Client
        server.port=0 ==> Random port
        spring.application.name=card-service
        eureka.instance.prefer-ip-address=true
        eureka.client.service-url.default-zone=${EUREKA_URI:http://localhost:8761/eureka}





































    */
}

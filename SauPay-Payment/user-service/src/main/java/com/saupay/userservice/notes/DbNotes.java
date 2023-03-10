package com.saupay.userservice.notes;

public class DbNotes {

    /*

----  Öncelikle application.properties dosyasında postgresql kullanacağımızı belirtiyoruz.

        spring.datasource.url=jdbc:postgresql://localhost:5433/paymentdb
        spring.datasource.username=postgres
        spring.datasource.password=1234

        #uygulama kapandığında tablolar silinmez --> Gelistirme ortamında kullanılır ..... --->spring.jpa.hibernate.ddl-auto=none...... --->production ortamında kullanılır
        spring.jpa.hibernate.ddl-auto=update

        !!!!!
                 spring.jpa.hibernate.ddl-auto Spring Boot uygulamalarında kullanılan bir özelliktir. Bu özellik, Hibernate aracılığıyla oluşturulan veritabanı tablolarının yönetimini yapar.

                Bu özellik, create-drop, create, update, validate ve none gibi beş farklı modda çalışabilir:

              --  create-drop: Uygulama başladığında tabloları oluşturur ve uygulama sonlandığında tabloları kaldırır.
              --  create: Uygulama başladığında tabloları oluşturur.
              --  update: Var olan tabloların yapısını günceller ve yeni tabloları oluşturur.
              --  validate: Var olan tabloların yapısını doğrular, ancak güncelleme veya oluşturma yapmaz.
              --  none: Herhangi bir şey yapmaz.

                ddl-auto özelliği özellikle geliştirme aşamasında kullanışlıdır, çünkü yeni bir varlık sınıfı eklediğinizde,
                bu özellik sayesinde Hibernate, veritabanı tablolarını otomatik olarak oluşturur veya günceller.
                Ancak, üretim ortamında bu özellik none olarak ayarlanmalıdır, çünkü var olan tabloların yapısını değiştirmek,
                veri kaybına ve sistem arızalarına neden olabilir. Bu nedenle, üretim ortamında veritabanı yönetimi için farklı bir yöntem kullanılmalıdır.



        uygulama localhost:8080 adresinde çalışıyor.
        uygulama localhost:5433 de çalışan postgresql veritabanındaki paymentdb ye  bağlanıyor
        uygulama postgres'e bağlanırken kullanıcı adı ve şifre ile bağlanıyor.


 -----Daha sonra docker-compose.yml doyası oluşturup pgadmin ve postgresql i docker ile çalıştırıyoruz.

         docker-compose.yml dosyası içeriği:

        version: '3.7'
        services:

          postgresdb:
            image: postgres
            environment:
              - POSTGRES_USER=postgres
              - POSTGRES_PASSWORD=1234
              - POSTGRES_DB=paymentdb
            ports:
              - "5433:5432"

          pgadmin:
            image: dpage/pgadmin4
            environment:
              - PGADMIN_DEFAULT_EMAIL=guzelomerfaruk9@gmail.com
              - PGADMIN_DEFAULT_PASSWORD=1234
            ports:
              - "5050:80"


       Docker'da çalıştırmak için terminalden docker-compose up --build komutunu çalıştırıyoruz.



 ----- Docker da çalışan postgresql'e pgadmin ile bağlanmak için pgadmin uygulamasını açıyoruz.

        local







































    */
}

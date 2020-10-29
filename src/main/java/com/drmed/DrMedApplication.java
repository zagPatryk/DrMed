package com.drmed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DrMedApplication {

    public static void main(String[] args) {
        SpringApplication.run(DrMedApplication.class, args);
    }

}


/*
- dodać logi do bazy danych i stworzyć 'MOMa' do sprawdzania ich z poziomu aplikacji
- dodać jakaś klasę z przygotowaniem dodatkowych tabel i logów, np
- obliczanie kosztów pacjenta dekoratorem np
- gdy doktor dostanie nowe zamówienie to na tablicy w trello pojawia mu się order i jego status czy coś :v
- wykorzystując factory zrobić testy singlowe/grupowe/różne technologie

 */

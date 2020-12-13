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
dodać przenoszenie karty przy zmianie doktora

mapper hibernaqte

ogarnąć metody które są 'wewnętrzne' czy potrzeba zwracać coś i czy muszą mieć dosep do innych klas
ogarnać poprawne exceptiony żeby dopisywały się dane do logów

dodać maila dla doktorów żeby można było wysyłać maile do osób testujacych aplikacje i jakiś sposób który usunie tego maila
gdy doktor dostanie nowe zamówienie to na tablicy w trello pojawia mu się order i jego status

wykorzystując factory zrobić testy singlowe/grupowe/różne technologie
obliczanie kosztów pacjenta dekoratorem
web security które zabroni wejście np pielęgniarce do dodawania rezultatów

400 błąd kliencki np nei podano imienia
500 błąd serwera powinny sie mapować na tobuisness exception i client exception i moje podklasy powinny być rozszerzeniem ich
jedno ma się mapować na 400 a drugie nas 500

dodać logi do bazy danych i stworzyć 'MOMa' do sprawdzania ich z poziomu aplikacji
    CQRS pattern - zapisujemy wszystkie komendy - przez to
 */

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
automatycznu kod wizyty jeżeli nie jest podany

;-; wywalić doktora z pacjenta i dodać do orderu i wizyty
na orderze orderingProvider jako interfejs może i tak można doktorka, albo pacjenta np

zamienić MRN, code doktora na identyfikator i wprowadzić interfejs person

dodać wizyte lekarską i połączyć z orderami
order może być połączony i z pacjentem i z wizytą ale nei koniecznie
https://rapidapi.com/priaid/api/symptom-checker?endpoint=58cfd524e4b0b3d27af3e1c9
na pewno:
pokarz wszystkie symptomy i wybierz z listy które występują i system pokazuje proponowane diagnozy

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






Linkedin
zdj w tle coszwiązanego z pracą, firmą, coś neutralnego, coś z programowaniem
zdj bardziej luźne
ew nagłówek teste/java developer
ew usunać medical physicist
ew usunąć certyfikaty - zostawić tylko labview
dodać certryfikat z bootcamu
przystąp do quizu
przegląd zainteresowań
 */

package com.drmed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DrMedApplication {
    public static void main(String[] args) {
        SpringApplication.run(DrMedApplication.class, args);
    }
}

// czy w serwisach odnosić się do innych serwisów czy reposytoriów?
// czy rozdzielić mapper ogólny od hibernatowego żeby zablokować klase do pakietu
// co robić z excepttionami
// czy vaadin w tym samym czy w osobnym
// podpowiedź do tego https://sandbox-authservice.priaid.ch/docs.html

/*
przerobić pod ten pattern któy pozwala zachować poprzednie wyniki order albo ordered test

ogarnać elvisa w mapperach

poprawić relacje miedzy encjami żeby się wszystko updejtowało

gogać płeć i wiek

automatycznu kod wizyty jeżeli nie jest podany

na orderze orderingProvider jako interfejs może i tak można doktorka, albo pacjenta np

wprowadzić interfejs person

https://rapidapi.com/priaid/api/symptom-checker?endpoint=58cfd524e4b0b3d27af3e1c9
pokarz wszystkie symptomy i wybierz z listy które występują i system pokazuje proponowane diagnozy

dodać przenoszenie karty przy zmianie doktora

ogarnąć metody które są 'wewnętrzne' czy potrzeba zwracać coś i czy muszą mieć dosep do innych klas
ogarnać poprawne exceptiony żeby dopisywały się dane do logów


wykorzystując factory zrobić testy singlowe/grupowe/różne technologie
obliczanie kosztów pacjenta dekoratorem
web security które zabroni wejście np pielęgniarce do dodawania rezultatów

400 błąd kliencki np nei podano imienia
500 błąd serwera powinny sie mapować na tobuisness exception i client exception i moje podklasy powinny być rozszerzeniem ich
jedno ma się mapować na 400 a drugie nas 500

dodać logi do bazy danych i stworzyć 'MOMa' do sprawdzania ich z poziomu aplikacji
 CQRS pattern - zapisujemy wszystkie komendy - przez to


*-*-*-*-*-*-*-*

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

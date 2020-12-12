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
// ogarnąć metody które są 'wewnętrzne' czy potrzeba zwracać coś i czy muszą mieć dosep do innych klas

// ogarnać poprawne exceptiony żeby dopisywały się dane do logów

pytanie do workstation
1 czy jest git to mapowanie i takie działania czy nei trzeba coś poprzenosić

2 czy test/ workstation mogą być componentami i używać mapperów, serviów?
    jak coś to przeta by było prototyp uzyć ale chyba to bez sensu

3 jakie ułożenie folderów w projekcie - np workstation wszystkie w jednym czy osobno repository, service, co z dto




repozytorium powinien już zwracać workstacje domenową

get id nie nowy obiekt tylko exception albo 404

dzielić domenami tzn doktor a tam osobno controler, repozyorium a nie na odwrót
hibernate nie publiczna a widoczna tylko przez pakiet

repozytorium ma tylko dostęp do obiektu hibernejtowego

15.12

18:00


 */


/*
dodać liste issue dostępną dla wszystkich
 */



/*
- dodać logi do bazy danych i stworzyć 'MOMa' do sprawdzania ich z poziomu aplikacji

- dodać streone z paramterami które można zmieniać takie GDM albo DMI
    np identytfikatory pacjenta żeby dodawać i widoczne były kontrolki do wpisana w aplikacji

- dodać jakaś klasę z przygotowaniem dodatkowych tabel i logów, np

- dodać maila dla doktorów żeby można było wysyłać maile do osób testujacych aplikacje i jakiś sposób który usunie tego maila
- gdy doktor dostanie nowe zamówienie to na tablicy w trello pojawia mu się order i jego status

- wykorzystując factory zrobić testy singlowe/grupowe/różne technologie
- obliczanie kosztów pacjenta dekoratorem

- web security które zabroni wejście np pielęgniarce do dodawania rezultatów

Pytania:
1. Czy jest sens robienia różnych dto dla jednego czegoś np DoctorInfoDto, DoctorFullDto
    tak jak najwięcej takich rzeczy
2. Jak to jest z modyfikacjami? Czy wystarczy przy zapisywaniu tylko zapisać obiekt czy pasuje najpeirw go pobrać, podmienic wartości a późhninj zapisać na nowo. Np da się coś zrobić żeby ułatwić np przy przy dodawaniu nowych pacjentów do listy?
    w repozytorium wyciągam odktorka i updejtuje pole po polu i na końcu zapisuje
3. Jak z zapisywaniem? np dla doktora: (nazwy nie prawdziwe tylko zobrazowane)
    Nowy doktor z controller DoctorNewDto -> mapper do DoctorGłówny -> mapper do DoctorHibernate -> save?
4. A jak z update?
    Dotkot z controllera DoctorDto -> mapper do docrotGłówny -> pobranie listy np istniejących pacjentów lub danych które nie zostały zmienione -> mappowanie do DoctorHibernate -> save?

5. Czy robić exceptiony na wypadek nie znalezienia jakiegoś pacjenta/orderu?

400 błąd kliencki np nei podano imienia
500 błąd serwera powinny sie mapować na tobuisness exception i client exception i moje podklasy powinny być rozszerzeniem ich
jedno ma się mapować na 400 a drugie nas 500

6. Jak audyt
    CQRS pattern - zapisujemy wszystkie komendy - przez to
 */

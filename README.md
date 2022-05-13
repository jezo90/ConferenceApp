# ConferenceApp

Aby uruchomić projekt należy pobrać projekt z GitHuba do swojego IDE. <br/>
W pliku konfiguracyjnym <b>conference-api/src/main/resources/application.yml</b> ustawić połączenie ze swoją lokalną bazą danych. <br/>
W aplikacji została wykorzystane baza PostgreSQL i z niej zaleca się korzystać.  <br/>
Dodatkowo aplikacja wykorzystuje Hibernate dzięki czemu automatycznie generowane są schematy bazy danych. <br/>
Aby schematy zostały utworzone najpierw wymagane jest stworzenie bazy danych o odpowiedniej nazwie. <br/>
W celu łatwiejszej obsługi zapytań został wykorzystany SwaggerGUI, <br/>
aby z niego skorzystać po uruchomienu aplikacji wystarczy przejść pod adres <br/>
http://localhost:8080/swagger-ui/index.html 

![image](https://user-images.githubusercontent.com/52450235/168387973-0f5079d3-0102-4604-a0d2-b439bc107448.png)


Zmiana adresu email
```
PUT 
/user/update
{
  "username": "Adam",
  "currentEmail": "adam@o2.pl",
  "newEmail": "adam@adam.pl"
}
````


Dodanie użytkownika 
```
POST 
/user/add
{
  "username": "Adam",
  "email": "adam@o2.pl"
}
````

Pobranie listy użytkowników 
```
GET 
/user/all
````

Zapisanie użytkownika na prelekcję
```
POST
/meeting/register
{
  "username": "Adam",
  "email": "adam@o2.pl",
  "topicId": 1
}
````

Pobranie planu konferencji
```
GET 
/meeting 
````

Pobranie konferencji użytkownika o podanym loginie
```
GET
/meeting/{login}
````

Pobranie informacji na temat zainteresowania ścieżkami tematycznymi
```
GET
/meeting/topicStats
````

Pobranie informacji na temat zainteresowania prelekcjami
```
GET
/meeting/stats
````

Usunięcie użytkownika o podanym loginie z prelekcji
```
DELETE
/meeting/remove
{
  "username": "Adam",
  "email": "adam@o2.pl",
  "topicId": 1
}
````



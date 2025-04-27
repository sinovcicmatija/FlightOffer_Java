# Flight Offer App (Java + React)

## Opis
Aplikacija za pretraživanje avionskih letova korištenjem Amadeus API-ja.  
Frontend razvijen u **Reactu**, backend u **Spring Boot**.

## Tehnologije
- Java 21 (Spring Boot 3.4)
- React 19
- Redis (za caching)
- Docker

### Brzo pokretanje

### Preduvjeti
 - Docker
 - Amadeus ključevi

### Dobavljanje API ključeva
   
Za dobavljanje API ključeva:
 - Registrirati se na https://developers.amadeus.com
 - Kreirati aplikaciju (Create new app) unutar Self Service Workspace.
 - ![image](https://github.com/user-attachments/assets/36401e53-78ea-495c-be2d-8ffddaa67aea)
 - Nakon kreiranja aplikacije, dohvatiti API Key i API Secret (vidljivo dolje lijevo).
 - ![image](https://github.com/user-attachments/assets/101c200f-1847-481c-829a-54c28acc672d)
 - Kopirati ključeve u .env.example fajl u root folderu i preimenovati ga u .env.

Nakon toga projekt je moguće pokrenuti pomoću jedne naredbe preko Dockera:
```bash
docker-compose up --build
```

## Pokretanje lokalno

Ukoliko želite, projekt možete pokrenuti i lokalno, no potrebni su određeni alati.

### Preduvjeti
- Node.js
- Java 21
- Redis server
- Docker

### Koraci
1. Klonirati repozitorij:
    ```bash
    git clone https://github.com/sinovcicmatijao/FlightOffer_Java.git
    ```

2. Postaviti `.env` varijable prema vlastitim klučevima sa Amadeus-a.

3. Pokrenuti:
    - Backend:
      ```bash
      cd Backend/FlightSearch_KingICT
      ./gradlew bootRun
      ```
    - Frontend:
      ```bash
      cd Frontend/flight-search-app
      npm install
      npm start
      ```
    - Redis:
    -Pokrenuti Redis server kroz docker, molim da ime porta bude tocno kako je u opisu, zato jer je beckend povezan točno na ovaj port:
    ```bash
      docker run -d --name redis-server -p 6379:6379 redis
      ```

## Docker
- Backend i frontend imaju vlastite `Dockerfile`.
- Sve pokrenuto kroz `docker-compose.yml`.

## Napomene
- API ključevi su potrebni (`.env`).
- Redis mora biti aktivan ako se pokreće lokalno.
- Ukoliko testirate projekte jedan za drugim, molim da prije testiranje drugog projekta očistite cache u redis serveru, razlog iza tog je što .net/angular i java/react projekti imaju različite načine spremanja i čitanja podataka sa cache, te ukoliko probate testirati jedan projekt bez da ste izbrisali podatke od proslog projekta doći će do greške.

-u redis cli možete ući preko iduće naredbe:
```bash
docker exec -it <ime_server> redis-cli
```
-nakon toga pokrenite naredbu 
```bash 
FLUSHALL
```


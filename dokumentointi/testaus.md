# Testausdokumentti
Testaus on toteutettu automatisoidusti JUnitilla ja manuaalisesti järjestelmätasolla.

## JUnit

### domain
Domain-pakkauksen, eli sovelluuslogiikkaa toteuttavien luokkien testauksen toteuttaa ImgTest, TagTest, ImgServiceTest ja TagServiceTest, ja ne testaavat nimiensä mukaisten luokkien toimintoja. ImgServiceTest ja TagServiceTest hyödyntävät TemporaryFolder-säännöllä toteutettua väliaikaista testitietokantaa.

### dao
Dao-pakkauksen, eli tietokannan käyttöä toteuttavien luokkien testauksen toteuttaa ImgDaoTest ja TagDaoTest, jotka jälleen testaavat nimiensä mukaisia luokkia hyödyntäen jälleen väliaikaista testitietokantaa.

### Testauskattavuus
Käyttöliittymää huomioimatta testien rivi- ja haarautumakattavuus on 100%.

![Kattavuus](https://github.com/joonaoko/ot-harjoitustyo/blob/master/dokumentointi/kuvat/testikattavuus.png)

## Manuaalinen testaus
Sovellus on ladattu ja käynnistetty Windows 10:ssä, ja tämän jälkeen sitä on testattu kokeilemalla manuaalisesti kaikkia määrittelydokumentissa ja käyttöohjeessa mainittuja toimintoja.
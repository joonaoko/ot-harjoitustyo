# Arkkitehtuuri

## Rakenne
Ohjelma on rakennettu pakkauksittain mukaillen kolmitasoista kerrosarkkitehtuuria siten, että: 

- `ui` sisältää käyttöliittymästä vastaavat luokat,

- `domain` sisältää sovelluslogiikasta vastaavat luokat ja

- `dao` sisältää tietokannan käytöstä vastaavat luokat.

![Pakkauskaavio](https://raw.githubusercontent.com/joonaoko/ot-harjoitustyo/master/dokumentointi/kuvat/pakkauskaavio.png)

## Sovelluslogiikka
Luokka `ImgService`

- hakee `dao`-pakkauksen `ImgDao`- ja `TagDao`-luokkien avulla tietokannasta kuvia ja niiden tageja, ja 

- välittää ne UI:lle `Image`- ja `Tag`-luokkien olioina.

`TagService` hakee `TagDao`-luokan avulla kaikki tagit tietokannasta.

### Luokka-/pakkauskaavio

![Pakkauskaavio](https://raw.githubusercontent.com/joonaoko/ot-harjoitustyo/master/dokumentointi/kuvat/luokkapakkauskaavio.png)

## Päätoiminnallisuudet

Kuvan näyttäminen:

![Open Image Sekvenssikaavio](https://raw.githubusercontent.com/joonaoko/ot-harjoitustyo/master/dokumentointi/kuvat/openimg-sekvenssikaavio.png)

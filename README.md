# GalleryWTags
Sovelluksella voi selata valokuvia niille asetettujen tagien perusteella, ja lisätä uusia valokuvia, sekä asettaa niille tageja.

## Dokumentaatio
[Käyttöohje](https://github.com/joonaoko/ot-harjoitustyo/blob/master/dokumentointi/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/joonaoko/ot-harjoitustyo/blob/master/dokumentointi/vaatimusmaarittely.MD)

[Työaikakirjanpito](https://github.com/joonaoko/ot-harjoitustyo/blob/master/dokumentointi/tyoaikakirjanpito.MD)

[Arkkitehtuuri](https://github.com/joonaoko/ot-harjoitustyo/blob/master/dokumentointi/arkkitehtuuri.md)

## Releaset
[Viikko 5](https://github.com/joonaoko/ot-harjoitustyo/releases/tag/viikko5)

[Viikko 6](https://github.com/joonaoko/ot-harjoitustyo/releases/tag/viikko6)

## Komentorivitoiminnot

### Testit
Testit voidaan suorittaa komennolla `mvn test`

Testikattavuusraportti voidaan generoida komennolla `mvn jacoco:report`

Raportti voidaan avata selaimessa avaamalla `target/site/jacoco/index.html`

### Suoritettava .jar
Komennolla `mvn package` voidaan generoida suoritettava `GalleryWTags-1.0-SNAPSHOT.jar`-niminen tiedosto target-hakemistoon

### JavaDoc
JavaDoc voidaan generoida komennolla `mvn javadoc:javadoc`

JavaDoc voidaan avata selaimessa avaamalla `target/site/apidocs/index.html`

### Checkstyle
Checkstyle-tarkistus voidaan suorittaa komennolla `mvn jxr:jxr checkstyle:checkstyle`

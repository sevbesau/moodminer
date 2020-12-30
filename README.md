# moodminer

neuraal netwerk getrained op activiteiten en hun categorieen,
op basis van de gemoedstoestand van de gebruiker.
elke dag word de gebruiker gevraag om zijn activiteiten in te vullen
en zijn dag een score te geven.

## categroieen

lichaamsbeweging
ontspanning
creativiteit
uitdaging
sociaal
rust

- manier om categorieen voor te stellen
- globaal voor iedereen dezelfde
- default icoon

## activiteiten

- persoonlijk
- opgeslagen
- frequentie en duur -> elkde dag tussen 9 en 5 ben ik op het werk

velden: 
				- titel
				- beschrijving
				- foto / icoon v categorie


## views

### home
homepage met navigatie

### categorieen
pagina waar alle categorieen staan

### categorie voorstellen
pagina om een categorie voor te stellen
(gebruikers laten stemmen?)

### activiteiten
pagina met alle opgeslagen activiteiten
hier kunnen ook activiteiten toevoegd worden

### activiteit toevoegen
form met velden voor de activiteit 

### activiteiten van vandaag
satisfying interface om je activiteiten snel in te vullen
- animatie
- haptic feedback
- sound

### dagweergave
pagina met alle activiteiten van die dag op, 
en ook de score als deze beschikbaar is

### kalender met scores
een kalender met een overzicht van de scores

### statistieken
pagina waarop grafieken komen over de score, 
de voorspellingen, invloed van activiteiten

### voorspelling
pagina waarop activiteiten worden voorgesteld

### voorspellingen
pagina waarop vorige voorspellingen te vinden zijn

### about
uitleg over hoe de applicatie werkt
wie ik ben en waarom ik dit gemaakt heb


## Voorspellingen

### Doel:

een voorspelling maken over wat de score van de dag zal zijn,
en wat er eventueel gedaan kan worden om de dag beter te maken.
op basis van de lengte, frequentie, categorie
van alle activiteiten van een dag, en de dagscore

### Technisch: 

Dataset -> eigen data, data van gebruiker aka information donation
door data zoeken met een gelijkaardige dag op basis van 
de lengte, frequentie, categorie
van alle activiteiten van een dag en de dagscore

neuraal netwerk? -> classification
eigen gewogen vergelijking algoritme


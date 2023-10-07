

***void zrus()*** 
> zrušení celého seznamu

***boolean jePrazdny()***
> test naplněnosti seznamu

***void vlozPrvni(T data)***
> vložení prvku do seznamu na první místo

***void vlozPosledni(T data)***
> vložení prvku do seznamu na poslední místo

***void vlozNaslednika(T data)***
> vložení prvku do seznamu jakožto následníka aktuálního prvku

***void vlozPredchudce(T data)***
> vložení prvku do seznamu jakožto předchůdce aktuálního prvku
 
***T zpristupniAktualni()***
> zpřístupnění aktuálního prvku seznamu

***T zpristupniPrvni()***
> zpřístupnění prvního prvku seznamu

***T zpristupniPosledni()***
> zpřístupnění posledního prvku seznamu

***T zpristupniNaslednika()***
> zpřístupnění následníka aktuálního prvku

***T zpristupniPredchudce()***
>zpřístupnění předchůdce aktuálního prvku

 
***T odeberAktualni()*** 
> odebrání (vyjmutí) aktuálního prvku ze seznamu poté je aktuální prvek nastaven na první prvek

***T odeberPrvni()*** 
> odebrání prvního prvku ze seznamu

***T odeberPosledni()***
> odebrání posledního prvku ze seznamu

***T odeberNaslednika()*** 
> odebrání následníka aktuálního prvku ze seznamu

***T odeberPredchudce()***
> odebrání předchůdce aktuálního prvku ze seznamu

***Iterator<T> iterator()*** 
> vytvoří iterátor (dle rozhraní Iterable)

Dále pomocí datové struktury ***ABSTRDOUBLELIST*** vybudujte abstraktní datovou strukturu
***zásobník***, jež bude v samostatném modulu ***ABSTRLIFO***. Tato datová struktura 
implementuje následující rozhraní: ***IAbstrLifo***

***void zrus()***
> zrušení celého zásobníku

***boolean jePrazdny()***
> test naplněnosti zásobníku

***void vloz(T data)***
> vložení prvku do zásobníku

***T odeber()***
> odebrání prvku ze zásobníku


***int importDat(String soubor)*** 
> provede import dat z datového souboru import.csv. Návratová hodnota přestavuje počet úspěšně načtených záznamů.

***void vlozProces(Proces proces, enumPozice pozice)*** 
> vloží nový proces do seznamu procesů na příslušnou pozici (první, poslední, předchůdce, následník)

***Proces zpristupniProces(enumPozice pozice)***
> zpřístupní proces z požadované pozice (první, poslední, předchůdce, následník, aktuální)

***Proces odeberProces(enumPozice pozice)*** 
> odebere proces z požadované pozice (první, poslední, předchůdce, následník, aktuální)

***Iterator iterator()*** 
> vrátí iterátor

***IAbstrLifo vytipujKandidatiReorg(int cas, enumReorg reorgan) ***
> pomocí iterátoru provede vytipování všech procesů k reorganizaci 
(dekompozice/agregace). Vstupním kritériem je čas a typ potenciální reorganizace.
Vytipovaní kandidáti jsou ukládáni do zásobníku.

***void reorganizace(enumReorg reorgan, IAbstrLifo zasobnik)*** 
> s vybranými procesy provede požadovaný typ reorganizace (dekompozice/agregace)

***void zrus()*** 
> zruší všechny procesy.

Modul ***Procesy*** – třídy procesů jsou potomci abstraktní třídy Proces

+ ***ProcesRoboticky***

i. id

ii. casProcesu

+ ***ProcesManualni***

i. id

ii. pocetOsob

iii. casProcesu

D) Pro obsluhu aplikace vytvořte uživatelské ***formulářové rozhraní ***ProgVyrobniProces***, 
které umožňuje obsluhu programu a volat požadované operace.

Zmíněný program, nechť umožňuje zadávání vstupních dat z klávesnice, ze souboru a 
z generátoru, výstupy z programu nechť je možné zobrazit na obrazovce a uložit do souboru.

package com.example.turizmoprograma.tourism_locations;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.turizmoprograma.R;

public class TourismLocationsDB extends SQLiteOpenHelper {

    private static final String TAG = "LocationsDBHelper: ";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "locationsManager";
    private static final String TABLE_LOCATIONS = "locations";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_ADDRESS = "address";
    private static final String COL_DESCRIPTION = "description";
    private static final String COL_LAT = "lat";
    private static final String COL_LNG = "lng";
    private static final String COL_TAGS = "tags";
    private static final String COL_IMG = "img";

    public TourismLocationsDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TABLE_LOCATIONS + "(" +
                COL_ID + " INTEGER PRIMARY KEY ," +
                COL_TAGS + " TEXT," +
                COL_NAME + " TEXT," +
                COL_ADDRESS + " TEXT," +
                COL_DESCRIPTION + " TEXT," +
                COL_LAT + " REAL," +
                COL_LNG + " REAL," +
                COL_IMG + " INTEGER)";
        db.execSQL(CREATE_TABLE);
        //Popular locations
        String ROW_1 = "INSERT INTO " + TABLE_LOCATIONS + " VALUES (" +
                "1,'senamiestis;istorija','Gedimino pilies bokštas','Arsenalo g. 5, Vilnius','Šis Vilniaus simbolis matyti iš daugelio Senamiesčio vietų ir yra vaizduojamas įvairiausiuose meno kūriniuose. Užlipus ant Gedimino kalno ar pakilus dar aukščiau - ant Gedimino bokšto apžvalgos aikštelės - atsiveria nuostabiausios Vilniaus panoramos ir auksiniai saulėlydžiai. Raudoni Vilniaus stogai, bažnyčių bokštai ir siauros gatvelės atrodo stulbinančiai. Gedmino bokšte pamatysite istorinę parodą, kurioje eksponuojami Vilniaus pilių rekonstrukcijos modeliai, ginkluotė ir senojo Vilniaus ikonografija.', 54.591540, 25.256610," + R.drawable.gediminopilis + ")";
        db.execSQL(ROW_1);
        String ROW_2 = "INSERT INTO " + TABLE_LOCATIONS + " VALUES (" +
                "2,'senamiestis;istorija','Stiklo kvartalas','Švarco g. / Stiklių g. / Gaono g.','Stiklo kvartalo pavadinimas gimė 2018 m., susikūrus tvirtai bendruomenei, primindamas laikus, kai čia driekėsi net trys gatvės, kurioms buvo suteiktas „stiklo“ pavadinimas; Stiklo 1-oji, Stiklo 2-oji ir Stiklo 3-oji g. Tarsi paslėptas tarp didžiųjų miesto gatvių kvartalas iki šiol glaudžia juvelyrus, vietos menininkų, amatininkų krautuvėles ir dirbtuves, jaukius restoranus, kamerinį orkestrą, meno galerijas ir vieną seniausių, iki šiol veikiančių, viešbučių  – viešbutį „Stikliai“.', 54.680990, 25.287630," + R.drawable.stiklokvartalas + ")";
        db.execSQL(ROW_2);
        String ROW_3 = "INSERT INTO " + TABLE_LOCATIONS + " VALUES (" +
                "3,'senamiestis;istorija','Valdovų rūmai','Katedros a. 4, Vilnius','Istorinėje vietoje,  Valdovų rūmų teritorijoje, IV–VIII a. buvo įsikūrusi gyvenvietė su mediniais statiniais, XIII a. antroje pusėje dalis gyvenvietės virto mūro pilimi. XIV a. pirmoje pusėje ji tapo svarbiausiu statiniu mūro siena juosiamoje didelėje Vilniaus Žemutinės pilies teritorijoje. Nuo pirmųjų Gediminaičių dinastijos atstovų laikų čia rezidavo beveik visi Lietuvos valdovai. Jie ne kartą plėtė mūro pilį, o XV a. pab. buvo pradėta jos esminė rekonstrukcija.', 54.685370, 25.288990," + R.drawable.valdovurumai + ")";
        db.execSQL(ROW_3);
        String ROW_4 = "INSERT INTO " + TABLE_LOCATIONS + " VALUES (" +
                "4,'istorija','Trijų Kryžių kalnas','Kalnų parkas, Vilnius','XII – XIII a. ant Trijų kryžių kalno buvo pastatyta medinė pilis, čia buvo įkurta gyvenvietė. Istorija pasakoja, kad ant Trijų Kryžių kalno kankinių mirtimi mirė septyni pranciškonai, kuriuos pagonys, didžiajam kunigaikščiui Algirdui nežinant, pririšo prie kryžių ir nuo šio kalno numetė į upę. Tada kalnas ir gavo Trijų Kryžių vardą, nes šiam įvykiui atminti buvo pastatyti trys kryžiai, kurie iki šiol vis atnaujinami. Visgi yra istorikų, kurie teigia, kad šie kryžiai, kitų miestų pavyzdžiu, pastatyti Magdeburgo teisių suteikimo Vilniui proga.', 54.707870, 25.111620," + R.drawable.trijukryziukalnas + ")";
        db.execSQL(ROW_4);
        String ROW_5 = "INSERT INTO " + TABLE_LOCATIONS + " VALUES (" +
                "5,'senamiestis','Užupio respublika','Paupio g. 3A, Vilnius','Užupis – vienas seniausių Vilniaus rajonų, kurį beprotiškai įsimylėję menininkai ir žmonės, trykštantys... laisve! Tai rajonas, kuris neretai lyginamas su Paryžiaus Monmartru, apsuptas dailininkų, muzikantų, žavių dirbtuvėlių, besididžiuojantis rūpestingai išgražintomis kavinėmis ir kruopščiai paruoštu maistu. Šiandien šis rajonas – vienas prestižiškiausių Vilniuje, turintis ne tik savo nuosavą himną, konstituciją, bet net ir atskirą prezidentą. Užupio dvasią ir ramybę saugo dvi bažnyčios bei trimituojantis bronzinis angelas.', 54.679640, 25.295820," + R.drawable.uzupiorespublika + ")";
        db.execSQL(ROW_5);
        String ROW_6 = "INSERT INTO " + TABLE_LOCATIONS + " VALUES (" +
                "6,'muziejus','MO muziejus','Pylimo g. 17, Vilnius','MO muziejus – viena naujausių meno ir kultūros vietų Vilniuje, stebinanti įspūdinga architektūra, kuri jau yra pelniusi ne vieną apdovanojimą. Muziejuje besikeičiant ekspozicijoms galima pamatyti modernaus meno parodas, kalbančias skirtingomis temomis, problemoms ir pristatančias vis skirtingus menininkus. Čia atvykus taip pat galima žiūrėti filmus, dažnai vyksta koncertai, meno performansai, tad ruošiantis keliauti po Vilnių, būtinai pasidomėkite kokia ekspozicija MO muziejuje lankytojus stebina šiandien.', 54.6793422, 25.2775129," + R.drawable.momuziejus + ")";
        db.execSQL(ROW_6);
        String ROW_7 = "INSERT INTO " + TABLE_LOCATIONS + " VALUES (" +
                "7,'muziejus;istorija','Okupacijų muziejus','Aukų g. 2A, Vilnius','Okupacijų ir laisvės kovų (KGB) muziejus – vienintelis toks muziejus Baltijos valstybėse. Jis įkurtas tame pačiame pastate, kuriame 1940–1991 m. dirbo sovietinės represinės institucijos. Pirmajame muziejaus aukšte, buvusiame MGB (KGB) vidaus kalėjimo viršininko pavaduotojo kabinete, galima pamatyti dokumentus, nuotraukas, žemėlapius ir kitus daiktus, pasakojančius apie krašto sovietizaciją. Antrajame muziejaus aukšte lankytojai gali apžiūrėti ekspozicijas, skirtas Lietuvos gyventojų kalinimui gulage, trėmimams ir KGB veiklai.', 54.688023, 25.2706894," + R.drawable.okupacijumuziejus + ")";
        db.execSQL(ROW_7);
        String ROW_8 = "INSERT INTO " + TABLE_LOCATIONS + " VALUES (" +
                "8,'gatvė;senamiestis','Literatų gatvė','Literatų g., Vilnius','Literatų gatvė visada sulaukia didelio lietuvių ir užsienio turistų susidomėjimo – tai, be abejonės, viena iš lankomiausių Vilniaus senamiesčio vietų. Manoma, kad Literatų gatvė šį savo pavadinimą gavo tik XIX a. pirmojoje pusėje, šios gatvės pradžioje gyvenusio rašytojo Adomo Mickevičiaus garbei. Tai liudija ir trys lentos ant namo sienos su užrašais lietuvių, rusų bei lenkų kalbomis. 2008 metais grupei menininkų kilo mintis atgaivinti šią gatvę ir papuošti ją meno kūriniais, susijusiais su literatais.', 54.6821595, 25.2898688," + R.drawable.literatugatve + ")";
        db.execSQL(ROW_8);
        String ROW_9 = "INSERT INTO " + TABLE_LOCATIONS + " VALUES (" +
                "9,'religija;senamiestis','Vilniaus Šv. Stanislovo ir Šv. Vladislovo arkikatedra bazilika ir varpinė','Šventaragio g., Vilnius','Ši šventovė – Lietuvos krikšto simbolis. Vilniaus Šv. Stanislovo ir Šv. Vladislovo arkikatedra bazilika, pastatyta pačiame miesto centre, buvusios pagonių šventyklos vietoje, šalia gynybinės miesto pilies, pergyveno gražiausius ir dramatiškiausius Vilniaus ir visos Lietuvos istorijos įvykius. Dėl dažnų gaisrų, karų bei nepatvaraus po pamatais esančio grunto Vilniaus katedra buvo ne kartą perstatinėjama.', 54.6850707, 25.2857924," + R.drawable.bazilikairvarpine + ")";
        db.execSQL(ROW_9);
        String ROW_10 = "INSERT INTO " + TABLE_LOCATIONS + " VALUES (" +
                "10,'senamiestis','Vilniaus Universitetas','Universiteto g. 3, Vilnius','Vilniaus universitetas – vienas seniausių pasaulio universitetų. Jame šiuo metu veikia 12 fakultetų ir mokosi apie 23 tūkstančiai studentų. Senuosiuose Vilniaus universiteto rūmuose yra įsikūrusi universiteto administracija ir 3 fakultetai: istorijos, filologijos bei filosofijos. Čia veikia ir biblioteka, kurioje sukaupta daugiau negu 5 milijonai spaudinių ir senųjų rankraščių. Tarp jų – vienas iš dviejų pasaulyje žinomų pirmosios lietuviškos knygos – Martyno Mažvydo „Katekizmo“ egzempliorių originalas.', 54.682708, 25.2866715," + R.drawable.vilniausuniversitetas + ")";
        db.execSQL(ROW_10);
        //History
        String ROW_11 = "INSERT INTO " + TABLE_LOCATIONS + " VALUES (" +
                "11,'istorija;senamiestis','Pilies gatvė','Pilies g., Vilnius','Pilies gatvė – seniausia ir puošniausia Vilniaus senamiestyje. Gatvė susiformavo ten, kur kelias iš Vilniaus pilies vedė į pietus – Lenkijos ir Rusijos link. Tai pagrindinis kelias į pilį, kurio atsišakojimai ilgainiui virto šoninėmis gatvėmis. Pilies gatvės pavadinimas istoriniuose šaltiniuose minimas jau 1530 metais. Pilies gatvė dėmesį patraukia architektūros įvairove: gyvenamieji namai, pažymėti Pilies gatvės 12-uoju ir 14-uoju numeriu, yra gotikiniai, Pilies g. 4-asis namas – renesansinis kapitulos namas, Šventų Jonų bažnyčios frontonas – barokinis.', 55.249159, 24.7646776," + R.drawable.piliesgatve + ")";
        db.execSQL(ROW_11);
        String ROW_12 = "INSERT INTO " + TABLE_LOCATIONS + " VALUES (" +
                "12,'istorija;sodyba','Dvaro sodyba Trakų vokėje','Žalioji g. 2A, Vilnius','Trakų Vokės dvaro rūmus pastatė Vilniaus bajorų maršalka, grafas Jonas Tiškevičius (1831–1892 m.). Iki šių dienų išliko visi senieji parko keliai ir takai, lapuočių medžių alėjos, deja, per karą nukentėjo dvaro rūmai. Jie restauruoti 1971 – 1978 metais (architektai Alfonsas Lagunavičius, Jonas Zibolis). Šiandieninį dvaro ansamblį sudaro rūmai, puošti kolonomis, varpinė su dviem bokšteliais, neogotikinė koplyčia – Tiškevičių mauzoliejus, vienintelės Lietuvoje arklidės, turinčios neobarokinių bruožų, ir kiti pastatai.', 54.4567254, 25.1228909," + R.drawable.dvarosodybatrakai + ")";
        db.execSQL(ROW_12);
        String ROW_13 = "INSERT INTO " + TABLE_LOCATIONS + " VALUES (" +
                "13,'istorija;muziejus','Vilniaus gynybinės sienos bastėja','Bokšto g. 20/18, Vilnius','Vilniaus gynybinės sienos bastėjos muziejus pasakoja Vilniaus ir Lietuvos Didžiosios Kunigaikštystės gynybos ir ginkluotės istoriją. Čia galima pamatyti ne tik pirmuosius parakinius šaunamuosius ginklus, bet ir išsamiai susipažinti su artilerine Lietuvos praeitimi: nuo pirmųjų artilerinių ginklų, tokių kaip bombardos ir mortyros, iki XVII–XVIII amžiaus mobilių ir funkcionalių patrankų rūšių. Bastėja, kaip pastatas, skaičiuojantis net penktąjį savo šimtmetį, lankytojui pasakoja ir apie Vilniaus praeitį, atskleisdama daugybę netikėtų klausimų: nuo ko gynėsi XVI amžiaus vilniečiai statydami Vilniaus gynybinę sieną? Kiek vartų ji turėjo ir kokiomis spynomis jie buvo rakinami?', 55.2275095, 24.7279667," + R.drawable.gynybinessienosbasteja + ")";
        db.execSQL(ROW_13);
        String ROW_14 = "INSERT INTO " + TABLE_LOCATIONS + " VALUES (" +
                "14,'istorija','Prezidento rūmai','S. Daukanto g. 3, Vilnius','Prezidento rūmai kasdieniame gyvenime dažnai yra vadinami Lietuvos Respublikos Prezidentūra, sutrumpintai – Prezidentūra. Prezidento rūmai pastatyti Simono Daukanto aikštėje, kuri šį pavadinimą gavo Simono Daukanto – Vilniaus universiteto auklėtinio, XIX amžiuje parašiusio pirmąją Lietuvos istoriją lietuvių kalba, – garbei. Iš kurios pusės beateitumėte į Daukanto aikštę, siaura gatvelė staiga prasiplečia ir įsilieja į aikštę, kurioje dominuoja klasicistiniai XVIII a. pabaigos – XIX a. pradžios statiniai: buvę didikų namai ir dabartiniai Prezidento rūmai. Aikštės didingumą „sušildo“ barokiniai bokšteliai virš pastatų stogų.', 54.6830121, 25.2864244," + R.drawable.prezidentura + ")";
        db.execSQL(ROW_14);
        String ROW_15 = "INSERT INTO " + TABLE_LOCATIONS + " VALUES (" +
                "15,'istorija;gatvė','Gedimino prospektas','Gedimino pr., Vilnius','Tai pagrindinė Vilniaus centro arterija, prasidedanti nuo Katedros aikštės, ir pasibaigianti prie LR Seimo rūmų. Šioje gatvėje gyvenimas verda aktyviausiai - čia įsikūrę pagrindinės valstybinės institucijos, šalia - kultūros centrai ir garsiausi teatrai, pramogų ir parduotuvių vietos. Dalis prospekto vakarais ir savaitgaliais skirta tik pėstiesiems, tačiau veiksmas čia nesiliauja - abipus gatvės daugybė barų ir restoranų. Žengdami prospektu nepamirškite stebėti, kaip keičiasi miesto architektūra - vos 1,8 km, tačiau pokyčiai akivaizdūs.', 54.6894452, 25.2659319," + R.drawable.gediminoprospektas + ")";
        db.execSQL(ROW_15);
        //Popular museums
        String ROW_16 = "INSERT INTO " + TABLE_LOCATIONS + " VALUES (" +
                "16,'muziejus','Vilniaus miesto muziejus','Vokiečių g. 6, Vilnius','Vilniaus muziejus – nauja sostinės pažinimo erdvė miesto gyventojams ir svečiams. 2021 m. pavasarį duris lankytojams atvėrusiame muziejuje pristatomi unikalūs ir dar nežinomi, tačiau aktualūs pasakojimai bei miesto istorijos. Dinamiškas ir nuolat besikeičiantis, kaip ir pats miestas, muziejus kasmet planuoja pristatyti po 2–3 parodas, grįstas autentiškais miesto gyvenimo tyrimais. Išskirtinai Vilniui skirtas muziejus kviečia įdėmiau įsižiūrėti į mus nuolat supančią aplinką ir joje išvysti ką nors netikėto.', 54.6784222, 25.2849577," + R.drawable.vilniausmuziejus + ")";
        db.execSQL(ROW_16);
        String ROW_17 = "INSERT INTO " + TABLE_LOCATIONS + " VALUES (" +
                "17,'muziejus','Lietuvos meno pažinimo centras „Tartle“','Užupio g. 40, Vilnius','Užupyje įsikūrusiame Lietuvos meno pažinimo centre „Tartle“ lankytojai kviečiami susipažinti su Lietuvos meno vertybėmis ir istoriniais artefaktais, nuo pagonybės laikų išlikusiu kultūriniu palikimu iki šiuolaikinio meno kūrinių. Centro tikslas – puoselėti, surinkti ir grąžinti į Lietuvą po pasaulį išbarstytą kultūrinį bei istorinį lietuvių paveldą, o svarbiausia – prisidėti prie to, kad jis būtų prieinamas visuomenei. „Tartle“ steigėjai: VšĮ „Lietuvos dailės fondas“ ir kolekcininkas Rolandas Valiūnas.', 54.6817345, 25.2991171," + R.drawable.tartlemuziejus + ")";
        db.execSQL(ROW_17);
        String ROW_18 = "INSERT INTO " + TABLE_LOCATIONS + " VALUES (" +
                "18,'muziejus','Parodų erdvė Istorijų namais','T.Kosciuškos g. 3, Vilnius','Istorijų namai – tai erdvė, kurioje istorija lankytojams pristatoma ne kaip fiksuota praeitis, o kaip mūsų visų sukurta ir tebekuriama realybė. Čia lankytojas yra kviečiamas kalbėti apie daugeriopą istorijos sampratą ir skirtingus požiūrius. Tai, kas vyko praeityje, dabar yra tiriama istorikų, archeologų, antropologų ir įkvepia dizainerius, menininkus, fotografus ir daugybę kitų kūrėjų. Visą šią skirtybių sintezę apibendrina vienas kūrinys – paroda – ir jos autorius – kuratorius.', 54.6894671, 25.2957649," + R.drawable.istorijunamai + ")";
        db.execSQL(ROW_18);
        String ROW_19 = "INSERT INTO " + TABLE_LOCATIONS + " VALUES (" +
                "19,'muziejus','Vytauto Kasiulo dailės muziejus','A. Goštauto g. 1, Vilnius','Vytauto Kasiulo dailės muziejus kviečia pažinti XX–XXI a. Lietuvos išeivijos dailę. Jame nuolat vykstančiomis parodomis simboliškai į Tėvynę sugrįžta po visą pasaulį pasklidę ir ten išgarsėję menininkai. Muziejuje eksponuojama Lietuvos nacionalinio dailės muziejaus sukaupta diasporos dailės kolekcija, kūriniai iš privačių ir korporatyvinių rinkinių. Įsimintinas ir pats neoklasicistinis muziejaus pastatas, egzistuojantis nuo XX a. pradžios, dabar, po ilgametės jo paskirties kaitos, veikiantis kaip svetinga kultūrinių renginių erdvė.', 54.690189, 25.2782818," + R.drawable.vytautokasiulomuziejus + ")";
        db.execSQL(ROW_19);
        String ROW_20 = "INSERT INTO " + TABLE_LOCATIONS + " VALUES (" +
                "20,'muziejus','TV bokštas','Sausio 13-osios g. 10, Vilnius','Vilniaus TV bokštas yra aukščiausias statinys šalyje ir teisėtai užima garbingą vietą tarp aukščiausių pasaulio televizijos bokštų. Esant giedram orui Vilniaus miestas ir jo apylinkės pro čia įsikūrusio restorano „Paukščių takas“ langus matomos net 50 km spinduliu. Minėtasis restoranas gali didžiuotis turėdamas vienintelę Lietuvoje tokiame aukštyje įrengta apžvalgos aikštelę. Žiedinės formos restorano grindys per 55 minutes apsisuka 360 laipsnių. Restoranas mielai priima tiek pavienius lankytojus, tiek turistų grupes. Čia vyrauja europietiškos virtuvės skoniai. Restoranas kviečia skanauti šviežių salotų, įvairių užkandžių, kepsnių ir desertų.', 54.6871468, 25.214721," + R.drawable.tvbokstas + ")";
        db.execSQL(ROW_20);
        //Religion
        String ROW_21 = "INSERT INTO " + TABLE_LOCATIONS + " VALUES (" +
                "21,'religija','Aušros Vartai','Aušros Vartų g. 14, Vilnius','Šiandien Aušros Vartai daugumai žmonių asocijuojasi su maldos namais. Tačiau prieš kelis šimtmečius išgirdę Aušros Vartų vardą vilniečiai pirmiausia būtų pagalvoję apie miesto gynybinę sieną. Tuo metu Vilniaus miesto gynybinė siena turėjo dešimt vartų, o Aušros Vartai – vieninteliai išlikę iš tų dešimties. Apie gynybinę statinio funkciją primena ir išorinėje vartų pusėje iki šiol gerai matomos šaudymo angos. Aušros Vartų koplyčioje kabantis stebuklingas Švenčiausiosios Mergelės Marijos Gailestingumo Motinos paveikslas – vienas žymiausių renesanso tapybos kūrinių Lietuvoje.', 54.6749179, 25.2895846," + R.drawable.ausrosvartai + ")";
        db.execSQL(ROW_21);
        String ROW_22 = "INSERT INTO " + TABLE_LOCATIONS + " VALUES (" +
                "22,'religija;bažnyčia','Šv. Onos ir Bernardinų kompleksas','Maironio g. 8, Vilnius','Barselona turi savo pasididžiavimą – Sagrada Familia, o Vilnius savo – Šventos Onos bažnyčią. Per penkis šimtmečius beveik nepakitusi bažnyčia yra vienas gražiausių ir, ko gero, garsiausių Vilniaus statinių. Tai vėlyvosios gotikos šedevras. Ją gaubia daug legendų. Žymiausia jų pasakoja, jog Napoleonas Bonapartas pamatęs Šv. Onos bažnyčią norėjo ją ant delno nusinešti į Paryžių. Šalia bažnyčios stovi XIX amžiuje pastatyta varpinė, imituojanti gotikos stilių. Galima drąsiai teigti, kad Šv. Onos bažnyčia jau yra neatsiejamas Vilniaus simbolis.', 54.6830429, 25.2932156," + R.drawable.svonosbaznycia + ")";
        db.execSQL(ROW_22);
        String ROW_23 = "INSERT INTO " + TABLE_LOCATIONS + " VALUES (" +
                "23,'religija;muziejus','Bažnytinio paveldo muziejus','Šv. Mykolo g. 9, Vilnius','Vilniaus senamiestyje, Šv. Mykolo bažnyčios ir buvusio Bernardinų vienuolyno ansamblyje, įsikūręs unikalus Bažnytinio paveldo muziejus sudomins ne tik sakralinio meno mėgėjus, bet ir kiekvieną senąja Lietuvos kultūra besidomintį žmogų. Bažnytinio paveldo muziejuje saugomos ir visuomenei pristatomos sakralinės vertybės. Čia akį traukia prabangūs auksakalystės šedevrai, puošnūs bažnytinės tekstilės pavyzdžiai. Lankytojai muziejuje susipažins su išskirtine Vilniaus katedros lobyno istorija ir vertingiausiais jo eksponatais.', 54.8486367, 25.4657212," + R.drawable.paveldomuziejus + ")";
        db.execSQL(ROW_23);
        String ROW_24 = "INSERT INTO " + TABLE_LOCATIONS + " VALUES (" +
                "24,'religija;bažnyčia','Šv. Jonų bažnyčia','Šv. Jono g. 12, Vilnius','Pilnas bažnyčios pavadinimas – Švento Jono Krikštytojo ir Švento Jono Apaštalo ir Evangelisto bažnyčia. Tačiau žmonės naudojo ir iki šiol tebenaudoja sutrumpintą jos pavadinimą, bažnyčią vadindami Šventų Jonų bažnyčia. Šventų Jonų bažnyčios statybos užtruko beveik 40 metų ir buvo baigtos 1426-aisiais. 1571 metais bažnyčia perėjo Jėzuitų ordino žinion, ir nuo tada ji laikoma universiteto komplekso dalimi. Šventų Jonų bažnyčia su varpine – viena vaizdingiausių universiteto komplekso dalių. Varpinė, kurios aukštis siekia 68 metrus, yra ir vienas aukščiausių senamiesčio pastatų.', 54.6826992, 25.2885845," + R.drawable.jonubaznycia + ")";
        db.execSQL(ROW_24);
        String ROW_25 = "INSERT INTO " + TABLE_LOCATIONS + " VALUES (" +
                "25,'religija;bažnyčia','Šv. Kazimiero bažnyčia','Didžioji g. 34, Vilnius, Vilnius','Barokinio stiliaus Šv. Kazimiero bažnyčia yra vienintelė Lietuvoje, kuri buvo pastatyta pagal Romos Jėzaus (Il Gesu) bažnyčios pavyzdį. Bažnyčios bokštus puošia karališkoji karūna, vidų – trys vėlyvojo baroko stiliaus dirbtinio marmuro altoriai. Bažnyčiai paveikslus 1993 m. nutapė dailininkas Antanas Kmieliauskas. Didžiajame altoriuje puikuojasi „Kristaus prisikėlimas” ir „Šv. Kazimieras“, šoniniuose – „Šv. Andriejus Bobola“ ir „Šv. Ignacas Lojola“.', 54.6776799, 25.2885776," + R.drawable.kazimierobaznycia + ")";
        db.execSQL(ROW_25);
        //Popular parks
        String ROW_26 = "INSERT INTO " + TABLE_LOCATIONS + " VALUES (" +
                "26,'parkas','Vilniaus universiteto botanikos sodas','Kairėnų g. 43, Vilnius','Jau daugiau nei 140 metų VU botanikos sodas yra neatsiejama universiteto ir sostinės gyventojų bendruomenės mokslo, švietėjiško ir socialinio gyvenimo dalis. Istorinės aplinkybės lėmė, kad botanikos sodas net keletą kartų buvo perkeltas. Botanikos sodas yra įsikūręs ir lankytojų laukia bei įvairias paslaugas teikia dviejose Vilniaus vietose: Kairėnuose ir Vingio parke. Bendras botanikos sodo plotas – net 199 ha. Kiekvienu šiltojo sezono metu čia auga, žydi ir bręsta skirtingi augalai: tiek rūšiniai augalai bei įvairios jų veislės, atvežtos iš tolimų ir artimų kraštų, tiek vietinės žolės ir medžiai, čia augantys natūraliai.', 54.7356049, 25.4013226," + R.drawable.vubotanikossodas + ")";
        db.execSQL(ROW_26);
        String ROW_27 = "INSERT INTO " + TABLE_LOCATIONS + " VALUES (" +
                "27,'parkas','Pavilnių regioninis parkas','Belmonto g., Vilnius','Pavilnių regioninio parko širdis – Vilnios slėnis. Jos pasididžiavimas – Pūčkorių atodanga. Ši atodanga – tai unikalus geologinis paminklas. Čia galite aplankyti ir buvusio vandens malūno teritoriją, kurioje šiuo metu veikia restoranų ir pramogų tinklas „Belmontas“. Šalia teka Belmonto kriokliai. Mėgstantys aktyvų poilsį gali apsilankyti žirgyne, pasivažinėti keturračiais motociklais. Šalia Pūčkorių atodangos, jaukioje Vilnios pakrantėje, stovi saulės laikrodis. Dažniausiai visi norintys laiką praleisti gamtoje ar pažvejoti renkasi būtent prie Vilnios Saulės laikrodžio ekspozicijos ar prie Pūčkorių užtvankos.', 54.6797076, 25.3186679," + R.drawable.pavilniuparkas + ")";
        db.execSQL(ROW_27);
        String ROW_28 = "INSERT INTO " + TABLE_LOCATIONS + " VALUES (" +
                "28,'parkas','Vingio parkas','M. K. Čiurlionio g. 100, Vilnius','Vingio parkas – vilniečių mėgstama pasivaikščiojimų, pasivažinėjimų dviračiais ar riedučiais vieta. Parko centre esančioje estradoje vyksta įvairūs renginiai: koncertai, minimos šventės. 1988 m. stadione, prie estrados, vyko Sąjūdžio mitingai, o 1993 m. popiežius Jonas Paulius II laikė mišias. Parke, gamtos apsuptyje, taip malonu atsipalaiduoti ir pasinerti į koncertų metu skambančios muzikos garsus arba praleisti laiką su draugais. Dienos metu čia dažnai renkasi tiek jaunimas, tiek vyresnio amžiaus žmonės tiesiog norėdami pailsėti ir pasėdėti ant žolės.', 54.6828199, 25.2357264," + R.drawable.vingioparkas + ")";
        db.execSQL(ROW_28);
        String ROW_29 = "INSERT INTO " + TABLE_LOCATIONS + " VALUES (" +
                "29,'parkas;sodas','Bernardinų sodas','B. Radvilaitės g. 8A, Vilnius','Bernardinų sodas, anksčiau vilniečių vadintas Sereikiškių parku, įsikūręs pačiame Vilniaus centre tarp Gedimino pilies bokšto, Vilnelės ir Bernardinų vienuolyno. Ypatinga parko erdvė – botanikos ekspozicija, skirta įamžinti XVIII a. čia buvusį vieną didžiausių sodų Rytų Europoje – Vilniaus universiteto botanikos sodą. Šalia įėjimo į parką nuo Šv. Brunono gatvės pusės auga seniausias Vilniaus ąžuolas, kurio amžius siekia net 400 metų.', 54.6849983, 25.2915597," + R.drawable.bernardinusodas + ")";
        db.execSQL(ROW_29);
        String ROW_30 = "INSERT INTO " + TABLE_LOCATIONS + " VALUES (" +
                "30,'parkas','Europos parkas','Europos Parko g. 302, Joneikiškės','Europos parke eksponuojama virš 100 skulptūrų, kurių autoriai – menininkai iš 32 valstybių. Tarp jų – žymūs šiuolaikinio meno kūrėjai, tokie kaip Lenkijos dailininkė Magdalena Abakanovič (Magdalena Abakanowicz),  JAV dailininkas, dailės teoretikas Solis Le Vitas (Sol LeWitt), amerikiečių menininkas, skulptorius ir fotografas Denisas Openheimas (Dennis Oppenheim). Viena įspūdingiausių Europos parko skulptūrų – iš senų televizorių pastatytas LNK Infomedis, kuris rekordus fiksuojančios agentūros „Guinness World Records“  buvo pripažintas kaip didžiausia tokio tipo skulptūra.', 54.8309512, 25.3517341," + R.drawable.europosparkas + ")";
        db.execSQL(ROW_30);

        Log.d(TAG, "CREATE STATEMENT invoked");
        Log.d(TAG, "Table \"" + TABLE_LOCATIONS + "\" created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_LOCATIONS;
        db.execSQL(DROP_TABLE);
        Log.d(TAG, "Table \"" + TABLE_LOCATIONS + "\" dropped");
        onCreate(db);
        Log.d(TAG, "Table \"" + TABLE_LOCATIONS + "\" created again");
    }
}

# AliveJson

[![Release](https://jitpack.io/v/maniac4j/alive-json.svg)](https://jitpack.io/p/maniac4j/alive-json)
[![Javadoc](https://img.shields.io/badge/javadoc-reference-blue.svg)](https://jitpack.io/com/github/maniac4j/alive-json/latest/javadoc/)

AliveJson — bu [Elegant Objects](https://www.elegantobjects.org/) tamoyillari asosida qurilgan, qat'iy obyektga-yo'naltirilgan (Pure OOP) Java JSON kutubxonasi.

## O'rnatish (Installation)

AliveJson-ni loyihangizga qo'shish uchun `pom.xml` yoki `build.gradle` faylingizga JitPack repozitoriyasini qo'shishingiz kerak:

### Maven
Buni `pom.xml` ga qo'shing:
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.maniac4j</groupId>
    <artifactId>alive-json</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle
`build.gradle` faylining oxiriga qo'shing:
```gradle
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.maniac4j:alive-json:1.0.0'
}
```

Aksariyat an'anaviy JSON kutubxonalari DTO (Data Transfer Object) lar, `null` qiymatlar, getter/setter'lar hamda ulkan markaziy "Parser" klasslariga tayanadi. AliveJson esa umuman boshqacha yondashuvni taklif etadi. Biz JSON ni shunchaki o'zgartiriladigan jonsiz ma'lumotlar tuzilmasi deb emas, balki jonli, o'zgarmas (immutable) obyektlar sifatida qabul qilamiz.

## Nega Jackson yoki Gson emas?

Agar sizga har bir nanosekund muhim bo'lgan o'ta yuqori tezlikdagi tizim kerak bo'lsa, **Jackson'dan foydalaning**. Ammo siz uzoq yillar yashaydigan, xatoliklarga o'rin yo'q va **qo'llab-quvvatlash (maintainability)** muhim bo'lgan biznes tizim yozayotgan bo'lsangiz, AliveJson eng yaxshi tanlovdir.

| Xususiyat | Jackson / Gson | AliveJson |
| :--- | :--- | :--- |
| **Falsafa** | Ma'lumotga yo'naltirilgan (DTO) | Obyektga yo'naltirilgan (Sof OOP) |
| **Null xavfsizligi** | `null` qaytaradi (NPE xavfi) | **Mutlaqo null-siz** (`JsonNull`) |
| **O'zgarmaslik** | Odatda o'zgaruvchan POJO'lar | **Qat'iy o'zgarmas (Immutable)** |
| **Reflection** | Ko'p ishlatiladi (sekin/xavfli) | **Umuman ishlatilmaydi** |
| Qo'llab-quvvatlash | DTO'lar bilan kuchli bog'liqlik | Interfeyslar orqali kuchsiz bog'liqlik |
| **Thread Safety** | POJO'ga bog'liq | **Tabiatan thread-safe** |

## Tezlik va Qulaylik (Performance vs. Maintainability)


Boshidan ochiq aytishimiz kerak: **AliveJson eng tezkor JSON kutubxonasi bo'lish uchun yaratilmagan.** Agar loyihangiz soniyasiga millionlab JSON obyektlarni o'qishi kerak bo'lsa (masalan, High-Frequency Trading tizimlari) va har bir mikrosekund muhim bo'lsa, `fastjson2` yoki `Jackson` kabi vositalar ancha yaxshi tanlov. Ular tezlikka erishish uchun xotirani to'g'ridan-to'g'ri o'zgartiradi va obyektlarni ochiq mutatsiya qiladi.

AliveJson esa mutlaqo boshqa narsani optimallashtiradi: **Dasturchining vaqti va kodning yashovchanligi.**

Agar siz biznes mantiqlarga boy, xatolikka o'rin yo'q bo'lgan, uzoq yillar davomida jamoa bilan yoziladigan va oson qo'llab-quvvatlanadigan (maintainable) dastur yozayotgan bo'lsangiz — AliveJson va "Pure OOP" bebahodir.

- **`null` larsiz hayot:** Kutubxona hech qachon `null` qaytarmaydi, uning o'rniga `JsonNull` obyekti qaytadi. Shu orqali kodingizdagi `NullPointerException` (NPE) lar va cheksiz `if != null` tekshiruvlari yo'qoladi.
- **Qat'iy O'zgarmaslik:** Har bir obyekt `final` va o'zgarmas. Siz holatni mutatsiya qilmaysiz, aksincha dekoratorlarni bir-biriga o'raysiz. Bu kodni to'g'ridan-to'g'ri ko'p oqimli ishlashga (thread-safe) moslashtiradi.
- **Markazlashmagan Mantiq:** Barcha narsaga javob beruvchi bitta katta `ObjectMapper` klassi yo'q. Mantiq kichik, o'qishli va aniq maqsadli obyektlarga bo'lingan.
- **Kengaytiriluvchi Arxitektura (Extensibility):** Tahlil qilish (parsing) mexanizmi qotib qolgan String matniga emas, balki abstrakt `Input` interfeysiga tayanadi. Bu Ochiq-Yopiqlik (Open-Closed) tamoyilini ta'minlaydi. Ya'ni kelajakda kodni o'zgartirmasdan turib, to'g'ridan-to'g'ri Tarmoqdan yoki Fayldan (`InputStream`) JSON o'qiydigan qo'shimchalar yozish imkonini beradi.

Biz ozgina mashina tezligidan voz kechib, evaziga mutlaq barqarorlik va inson uchun tushunarli kodga ega bo'lamiz.

## Misollar

AliveJson sof OOP paradigmasiga amal qiladi. Quyidagi misollar static metodlarsiz va yordamchi klasslarsiz obyektlarni qanday kompozitsiya qilishni ko'rsatadi.

### 1. Oddiy o'qish va tahlil qilish

JSON matnni o'qish va undan xavfsiz tarzda qiymatlarni ajratib olish:

```java
import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.io.Parsed;

// Tahlil qilish (parsing) faqat qiymat so'ralgandagina ishga tushadi
final Json source = new Parsed("{\"name\":\"AliveJson\",\"version\":1}");
final String name = source.value("name").text(); // "AliveJson"
// Yo'q kalit so'ralsa dastur qulamaydi, xavfsiz JsonNull qaytadi
final String missing = source.value("missing").text(); // "null"
```

### 2. Obyektni noldan qurish

Yangi JSON obyektini yaratish uchun sizga matn kerak emas. Uni dekoratorlar yordamida qadamba-qadam qurish mumkin.

```java
import java.util.Collections;
import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.build.With;
import uz.maniac4j.alivejson.build.Without;
import uz.maniac4j.alivejson.io.Formatted;
import uz.maniac4j.alivejson.types.JsonNumber;
import uz.maniac4j.alivejson.types.JsonObject;
import uz.maniac4j.alivejson.types.JsonString;

// Bo'sh obyektdan boshlab dekoratorlarni zanjir kabi ulaymiz
final Json user = new Without(
    new With(
        new With(
            new JsonObject(Collections.emptyMap()),
            "id", new JsonNumber(101)
        ),
        "name", new JsonString("Pure OOP")
    ),
    "status"
);
final String text = new Formatted(user).text();
// {"id":101,"name":"Pure OOP"}
```

### 3. Ichma-ich joylashgan xususiyatni yangilash (Deep Update)

Ichma-ich joylashgan maydonni kompozitsiya orqali xavfsiz yangilash:

```java
import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.build.With;
import uz.maniac4j.alivejson.io.Parsed;
import uz.maniac4j.alivejson.types.JsonString;

final Json origin = new Parsed("{\"user\":{\"profile\":{\"theme\":\"dark\"}}}");
final Json updated = new With(
    origin, 
    "user", 
    new With(
        origin.value("user"), 
        "profile", 
        new With(
            origin.value("user").value("profile"), 
            "theme", 
            new JsonString("light")
        )
    )
);
```

### 4. Yo'q qiymatlar bilan ishlash

`if != null` dan qochish uchun maxsus dekoratorlardan foydalanamiz.

**`Fallback` dekoratori:**
```java
import uz.maniac4j.alivejson.build.Fallback;
import uz.maniac4j.alivejson.types.JsonNumber;

// Agar "age" topilmasa, 18 qaytadi
final Json age = new Fallback(source, new JsonNumber(18)).value("age");
```

**`Strict` dekoratori:**
```java
import uz.maniac4j.alivejson.build.Strict;

// Agar "age" topilmasa, IllegalArgumentException otiladi
final Json strict = new Strict(source).value("age");
```

### 5. Sof OOP uslubida Obyektlarga O'tkazish

JSON ni domen obyektlariga Reflection-siz, Kompozitsiya yordamida o'tkazish.

```java
public final class JsonUser implements User {
    private final Json origin;
    public JsonUser(final Json json) {
        this.origin = json;
    }
    @Override
    public String name() {
        return this.origin.value("name").text().replace("\"", "");
    }
    @Override
    public int age() {
        return Integer.parseInt(
            new Fallback(this.origin, new JsonNumber(0)).value("age").text()
        );
    }
}
```

## Asosiy Abstraksiya

Hamma narsa quyidagi yagona interfeysni implement qiladi, bu esa cheksiz kompozitsiya imkonini beradi.

```java
public interface Json {
    String text();
    Json value(String key);
    Json value(int index);
}
```

## Litsenziya

MIT Litsenziyasi. Bafsil ma'lumot uchun `LICENSE` faylini ko'ring.

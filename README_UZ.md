# AliveJson

AliveJson — bu [Elegant Objects](https://www.elegantobjects.org/) tamoyillari asosida qurilgan, qat'iy obyektga-yo'naltirilgan (Pure OOP) Java JSON kutubxonasi.

Aksariyat an'anaviy JSON kutubxonalari DTO (Data Transfer Object) lar, `null` qiymatlar, getter/setter'lar hamda ulkan markaziy "Parser" klasslariga tayanadi. AliveJson esa umuman boshqacha yondashuvni taklif etadi. Biz JSON ni shunchaki o'zgartiriladigan jonsiz ma'lumotlar tuzilmasi deb emas, balki jonli, o'zgarmas (immutable) obyektlar sifatida qabul qilamiz.

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

Quyida AliveJson yordamida qilinishi mumkin bo'lgan ba'zi amaliyotlar ko'rsatilgan.

### 1. Oddiy o'qish va tahlil qilish

JSON matnni o'qish va undan xavfsiz tarzda qiymatlarni ajratib olish:

```java
import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.io.Parsed;

public class BasicReading {
    public static void main(String[] args) {
        // Tahlil qilish (parsing) faqat qiymat so'ralgandagina ishga tushadi
        Json source = new Parsed("{\"name\":\"AliveJson\",\"version\":1}");
        
        System.out.println(source.value("name").text()); // "AliveJson"
        
        // Yo'q kalit so'ralsa dastur qulamaydi, xavfsiz JsonNull qaytadi
        System.out.println(source.value("missing").text()); // "null"
    }
}
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

public class BuildingFromScratch {
    public static void main(String[] args) {
        // Bo'sh obyektdan boshlaymiz
        Json empty = new JsonObject(Collections.emptyMap());
        
        // Xususiyatlarni o'zgarmas tarzda ustma-ust qo'shamiz
        Json first = new With(empty, "id", new JsonNumber(101));
        Json second = new With(first, "name", new JsonString("Pure OOP"));
        Json third = new With(second, "status", new JsonString("Active"));
        
        // O'zgarmas tarzda xususiyatni olib tashlash
        Json finalJson = new Without(third, "status");
        
        System.out.println(new Formatted(finalJson).text());
        // {"id":101,"name":"Pure OOP"}
    }
}
```

### 3. Ichma-ich joylashgan xususiyatni yangilash (Deep Update)

Odatda ichma-ich (deeply nested) joylashgan maydonni o'zgartirish qiyin va xavfli bo'ladi. AliveJson buni xavfsiz funksional usulda bajaradi.

```java
import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.build.With;
import uz.maniac4j.alivejson.io.Formatted;
import uz.maniac4j.alivejson.io.Parsed;
import uz.maniac4j.alivejson.types.JsonString;

public class DeepUpdate {
    public static void main(String[] args) {
        Json origin = new Parsed("{\"user\":{\"profile\":{\"theme\":\"dark\"}}}");
        
        // 1. O'zgarishi kerak bo'lgan eng ichki qismni ajratib olamiz
        Json profile = origin.value("user").value("profile");
        
        // 2. Uni yangi qiymat bilan o'raymiz
        Json updatedProfile = new With(profile, "theme", new JsonString("light"));
        
        // 3. Uning ota elementini o'raymiz
        Json updatedUser = new With(origin.value("user"), "profile", updatedProfile);
        
        // 4. Asosiy ildiz elementini o'raymiz
        Json updatedOrigin = new With(origin, "user", updatedUser);
        
        System.out.println(new Formatted(updatedOrigin).text());
        // {"user":{"profile":{"theme":"light"}}}
    }
}
```

### 4. Fayldan JSON o'qish

Jismoniy fayldan o'qish ham xuddi shunday oson. Parsing yalqov (lazy) bo'lgani uchun, u matnni xavfsiz o'rab oladi va faqat siz so'ragan qisminigina tahlil qiladi.

```java
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.io.Parsed;

public class FileReading {
    public static void main(String[] args) throws Exception {
        // Fayl tarkibini matn ko'rinishida o'qib olish
        Path path = Paths.get("data.json");
        String content = Files.readString(path);
        
        // Yalqov tahlilchiga (lazy parser) uzatish
        Json fileJson = new Parsed(content);
        
        // Ma'lumotlarni xavfsiz ajratib olish
        System.out.println(fileJson.value("settings").value("port").text());
    }
}
```

### 5. Yo'q qiymatlar bilan ishlash (`if != null` siz hayot)

AliveJson qat'iyan `null` qaytarishdan qochadi. Agar kalit topilmasa, u indamasdan `JsonNull` obyektini qaytaradi. Agar siz yo'q qiymatlarni aniq nazorat qilmoqchi bo'lsangiz, iflos `if` shartlari o'rniga maxsus dekoratorlardan foydalanasiz.

**`Fallback` dekoratori:**
Agar so'ralgan kalit yoki indeks topilmasa, siz bergan zaxira (default) qiymatni qaytaradi.

```java
import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.io.Parsed;
import uz.maniac4j.alivejson.build.Fallback;
import uz.maniac4j.alivejson.types.JsonNumber;

public class FallbackExample {
    public static void main(String[] args) {
        Json json = new Parsed("{\"name\":\"AliveJson\"}");
        
        // Zaxira qiymatga o'raymiz. Agar "age" topilmasa, 18 qaytadi.
        Json age = new Fallback(json, new JsonNumber(18)).value("age");
        System.out.println(age.text()); // Natija: 18
    }
}
```

**`Strict` dekoratori:**
"Fail Fast" (Tez xato otish) paradigmasiga asoslanadi. Agar so'ralgan kalit topilmasa, u indamasdan `JsonNull` qaytarish o'rniga darhol `IllegalArgumentException` xatoligini otadi.

```java
import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.io.Parsed;
import uz.maniac4j.alivejson.build.Strict;

public class StrictExample {
    public static void main(String[] args) {
        Json json = new Parsed("{\"name\":\"AliveJson\"}");
        
        // Kerakli kalit topilmasa darhol dasturni to'xtatadi
        Json strictJson = new Strict(json);
        Json age = strictJson.value("age"); // IllegalArgumentException otiladi!
    }
}
```

### 6. Obyektlarga O'tkazish (Deserialization) - Sof OOP uslubi

An'anaviy JSON kutubxonalari JSON matnni "Reflection" va Setterlar yordamida DTO larga o'tkazib (map qilib) beradi. AliveJson da esa, Domen obyektlaringiz kompozitsiya (Composition) yordamida o'zlarini o'zlari JSON bilan bog'laydi. Hech qanday reflection, hech qanday setterlar yo'q, faqat sof interfeyslar.

1. **Domen (Biznes) Interfeysingizni aniqlang:**
```java
public interface User {
    String name();
    int age();
}
```

2. **Uning JSON versiyasini yarating:**
```java
import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.build.Fallback;
import uz.maniac4j.alivejson.types.JsonNumber;

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
        Json safeAge = new Fallback(this.origin, new JsonNumber(0)).value("age");
        return Integer.parseInt(safeAge.text());
    }
}
```

3. **Dasturda ishlatilishi:**
```java
import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.io.Parsed;

public class DomainMappingExample {
    public static void main(String[] args) {
        Json source = new Parsed("{\"name\":\"Ali\", \"age\":25}");
        User user = new JsonUser(source);
        
        System.out.println("Foydalanuvchi ismi: " + user.name());
        System.out.println("Foydalanuvchi yoshi: " + user.age());
    }
}
```
Bu yondashuv kodning mutlaq xavfsizligini (Thread Safety), xotira tejamkorligini (qiymatlar faqat so'ralganda - lazy o'qiladi) kafolatlaydi va kodni sekinlashtiradigan "Reflection" zaruratini umuman yo'qqa chiqaradi!

## Asosiy Abstraksiya

Hamma narsa quyidagi yagona interfeysni implement qiladi. Shuning uchun siz dekoratorlarni istaganingizcha bir-biriga qo'shishingiz mumkin.

```java
public interface Json {
    String text();
    Json value(String key);
    Json value(int index);
}
```

## Litsenziya

MIT Litsenziyasi. Bafsil ma'lumot uchun `LICENSE` faylini ko'ring.

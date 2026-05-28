# Foydalanish qo'llanmasi

Ushbu qo'llanma AliveJson kutubxonasidan Java loyihalarida qanday samarali foydalanishni tushuntiradi.

## JSON tahlili (Parsing)

Matnlarni tahlil qilish uchun asosiy kirish nuqtasi - `Parsed` klassi. Tahlil qilish **yalqov (lazy)** usulda ishlaydi, ya'ni haqiqiy ishlov berish faqat qiymatga murojaat qilinganda sodir bo'ladi.

```java
import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.io.Parsed;

final Json json = new Parsed("{\"id\": 1, \"name\": \"EO\"}");
```

## Qiymatlarga murojaat

Obyekt maydonlariga kalit (key) orqali, massiv elementlariga esa indeks orqali murojaat qilishingiz mumkin. Agar qiymat mavjud bo'lmasa, AliveJson `null` o'rniga `JsonNull` obyektini qaytaradi.

```java
final String name = json.value("name").text(); // "EO" qaytaradi
final Json missing = json.value("noma'lum"); // JsonNull obyektini qaytaradi
final String text = missing.text(); // "null" qaytaradi (Xavfsiz!)
```

## Dekoratorlar (Qurish va O'zgartirish)

AliveJson JSON obyektlarini "o'zgartirish" uchun Dekorator naqshidan foydalanadi. Obyektlar o'zgarmas (immutable) bo'lgani uchun, dekorator ma'lumotlarning yangi ko'rinishini hosil qiladi.

### With
Xususiyatni qo'shadi yoki yangilaydi.

```java
import uz.maniac4j.alivejson.build.With;
import uz.maniac4j.alivejson.types.JsonString;

final Json yangilangan = new With(json, "versiya", new JsonString("1.0"));
```

### Without
Xususiyatni olib tashlaydi.

```java
import uz.maniac4j.alivejson.build.Without;

final Json soddalashtirilgan = new Without(json, "id");
```

### Formatted
JSON matnini chiroyli formatda chiqaradi.

```java
import uz.maniac4j.alivejson.io.Formatted;

System.out.println(new Formatted(json).text());
```

## Yo'q qiymatlar bilan ishlash

### Fallback
Agar so'ralgan kalit yoki indeks topilmasa, zaxira (default) qiymatni qaytaradi.

```java
import uz.maniac4j.alivejson.build.Fallback;
import uz.maniac4j.alivejson.types.JsonNumber;

// Agar "age" topilmasa, 18 qaytadi
final Json yosh = new Fallback(json, new JsonNumber(18)).value("age");
```

### Strict
"Fail Fast" (Tezda xato otish) paradigmasiga amal qiladi. Kalit topilmasa `IllegalArgumentException` xatosini otadi.

```java
import uz.maniac4j.alivejson.build.Strict;

final Json yosh = new Strict(json).value("age"); // "age" bo'lmasa xato otadi
```

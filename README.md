# CakeRegions API

[![GitHub Release](https://img.shields.io/github/release/CakesStudio/CakeRegionsAPI.svg?logo=github&color=brightgreen)](https://github.com/CakesStudio/CakeRegionsAPI/releases/latest)
[![JitPack](https://img.shields.io/jitpack/v/github/CakesStudio/CakeRegionsAPI.svg?logo=jitpack&color=blue)](https://jitpack.io/#CakesStudio/CakeRegionsAPI)
![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk)
[![License](https://img.shields.io/badge/License-MIT-blue.svg?logo=opensourceinitiative&color=blue)](LICENSE)

Официальный интерфейс разработчика для **CakeRegions**. Данное API предоставляет управляемую среду для создания расширений (аддонов), взаимодействия с приват-системой и интеграции кастомных элементов.

<br/>

## 📝 Описание
CakeRegions API спроектировано для обеспечения высокоуровневой абстракции над системой регионов, сохраняя изоляцию за счет выделенной системы аддонов.

**Ключевые возможности:**
- **Управляемый жизненный цикл:** Автоматическая регистрация и очистка слушателей событий, команд и задач.
- **Интеграция с Folia:** Встроенная поддержка FoliaLib для многопоточности по регионам (regional threading).
- **Изоляция зависимостей:** Каждый аддон загружается в собственном ClassLoader.
- **Событийная модель:** Возможность отмены событий создания, удаления или изменения приватов до выполнения транзакций в БД.

<br/>

## 🚀 Установка

> [!IMPORTANT]
> **Не требуется отдельная установка:** API уже встроено в основной плагин **CakeRegions**. Вам **не** нужно размещать отдельный JAR-файл API в папке `/plugins` вашего сервера.

> [!TIP]
> CakeRegions API хостится на **JitPack**. Обязательно используйте область видимости `compileOnly`, чтобы не собирать API внутрь вашего JAR-файла.

### Gradle (Groovy)
```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    compileOnly 'com.github.CakesStudio:CakeRegionsAPI:VERSION'
}
```

### Maven
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.CakesStudio</groupId>
        <artifactId>CakeRegionsAPI</artifactId>
        <version>VERSION</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```
*(Замените `VERSION` на целевой релиз, например, `v2.0.1`)*

<br/>

## 📦 Релокация библиотек и зависимости
Core-плагин CakeRegions перемещает свои внутренние библиотеки (такие как **Adventure API** и **FoliaLib**) во избежание конфликтов версий.
Ваш аддон **обязан релоцировать** эти библиотеки так, чтобы они соответствовали внутренней структуре пакетов CakeRegions.

> [!CAUTION]
> **Зачем это делать?** Если вы не настроите релокацию, ваш аддон будет использовать базовый пакет `net.kyori`, в то время как CakeRegions API уже трансформирован под релоцированные пакеты. Это приведет к ошибке `NoSuchMethodError` во время работы плагина.

Просто используйте `compileOnly` для этих зависимостей в вашем `build.gradle` и всегда релоцируйте их:

**Настройка build.gradle для аддона:**
```gradle
dependencies {
    // Зависимость API
    compileOnly 'com.github.CakesStudio:CakeRegionsAPI:VERSION'

    // Ядерные библиотеки, используемые API (должны быть compileOnly)
    compileOnly "com.tcoded:FoliaLib:0.5.1"
    compileOnly 'net.kyori:adventure-platform-bukkit:4.4.1'
    compileOnly 'net.kyori:adventure-text-minimessage:4.26.1'
    compileOnly 'net.kyori:adventure-text-serializer-legacy:4.26.1'
    compileOnly 'net.kyori:adventure-text-serializer-plain:4.26.1'
}

tasks.shadowJar {
    // Релокация под структуру CakeRegions
    relocate 'net.kyori', 'dev.cakestudio.cakeregions.libs.kyori'
    relocate 'com.tcoded.folialib', 'dev.cakestudio.cakeregions.libs.folialib'
}
```

<br/>

## 📖 Документация
Подробные руководства, использование менеджеров и описание событий находятся здесь:
### 👉 [**Документация API (DOCUMENTATION.md)**](DOCUMENTATION.md)

<br/>

## Лицензия
Этот проект распространяется по лицензии MIT - подробности смотрите в файле [LICENSE](LICENSE).

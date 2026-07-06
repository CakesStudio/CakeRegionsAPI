# CakeRegions API Подробная Документация

Данный документ представляет собой подробное руководство для разработчиков расширений (аддонов) под **CakeRegions**.

---

## 🛠️ Доступ к API
Точка входа для взаимодействия с приват-системой. Доступ к ней осуществляется через `CakeRegionsAPI.getApi()`.

```java
IRegionsAPI api = CakeRegionsAPI.getApi();

// Проверить, защищена ли локация регионом
if (api.isProtected(location)) {
    // Выполнить логику
}
```

---

## 🧩 Управляемая Система Аддонов

### 1. Метаданные (`addon.yml`)
Каждому аддону необходим файл `addon.yml` в директории `resources`.

```yaml
name: MyAwesomeAddon
main: com.example.myaddon.MyAddon
version: 1.0.0
api-version: '1.0.0'       # Минимальная поддерживаемая версия CakeRegions API
folia-supported: true      # Поддержка серверов Folia
description: "Пример описания аддона"
authors: [ "Developer" ]
depend: []                 # Обязательные зависимости
soft-depend: []            # Необязательные зависимости
```

### 2. Реализация класса аддона
Унаследуйте класс от `AbstractAddon` для автоматического управления его жизненным циклом.

```java
public class MyAddon extends AbstractAddon {
    @Override
    protected void onEnable() {
        saveDefaultConfig(); // Конфигурация сохранится в /plugins/CakeRegions/addons/MyAddon/
        
        // Автоматическая регистрация
        registerListener(new MyListener());
        registerCommand(new MyCommand());
    }

    @Override
    protected void onDisable() {
        // Логика при выключении
    }
}
```

> [!IMPORTANT]
> **Автоматическая очистка ресурсов:** При отключении аддона CakeRegions автоматически:
> - Выгружает все зарегистрированные через `registerListener` слушатели событий.
> - Удаляет все зарегистрированные через `registerCommand` команды.
> - Отменяет все задачи, запланированные методами `runTask*`.
> - Закрывает все GUI-меню, открытые вашим аддоном.

---

## ⚡ Многопоточность и Schedulers
Используйте встроенные методы планировщика (на базе **FoliaLib**), чтобы гарантировать корректную работу как на классическом Spigot/Paper, так и на многопоточном Folia.

```java
// Безопасное асинхронное выполнение
runTaskAsync(() -> {
    // Тяжелая логика в фоне
});

// Отложенное асинхронное выполнение
runTaskLaterAsync(() -> {
    // Выполнить через 20 тиков
}, 20L);
```

---

## 💰 Экономика (`IEconomyManager` & `IEconomyProvider`)
Вы можете работать с различными экономическими плагинами (Vault, BVault, VaultUnlocked, PlayerPoints, Experience) или зарегистрировать свою валюту.

```java
IEconomyManager economy = api.getEconomyManager();

// Получить баланс игрока
double balance = economy.getBalance(player);

// Списать средства
if (economy.has(player, 100.0)) {
    economy.withdraw(player, 100.0);
}
```

### Регистрация кастомной экономики
Если ваш плагин вводит собственную валюту, реализуйте `IEconomyProvider` и зарегистрируйте её:

```java
public class MyCustomGems implements IEconomyProvider {
    @Override
    public String getName() { return "Gems"; }

    @Override
    public double getBalance(OfflinePlayer player) { ... }

    @Override
    public boolean has(OfflinePlayer player, double amount) { ... }

    @Override
    public boolean withdraw(OfflinePlayer player, double amount) { ... }

    @Override
    public boolean deposit(OfflinePlayer player, double amount) { ... }

    @Override
    public String format(double amount) { return amount + " Gems"; }
}

// Регистрация
economy.registerProvider(new MyCustomGems());
```

---

## 📦 Кастомные предметы (`IItemHookManager` & `IItemHookProvider`)
CakeRegions умеет распознавать и выдавать кастомные предметы из **ItemsAdder**, **Oraxen** и **Nexo**. Вы также можете добавить поддержку своего плагина предметов.

```java
IItemHookManager itemHook = api.getItemHookManager();

// Получить ItemStack по строковому ID
ItemStack customItem = itemHook.getItem("oraxen:my_sword");

// Получить ID кастомного предмета
String itemId = itemHook.getItemId(customItem); // вернет "oraxen:my_sword"
```

### Регистрация своего провайдера кастомных предметов
```java
public class MyItemsProvider implements IItemHookProvider {
    @Override
    public @Nullable ItemStack getItem(@NonNull String id) { ... }

    @Override
    public @Nullable String getItemId(@NonNull ItemStack item) { ... }

    @Override
    public @NonNull String getPrefix() { return "myitems"; }
}

// Регистрация
itemHook.registerProvider(new MyItemsProvider());
```

---

## 🔔 События API (Events)
Все события поддерживают интерфейс `Cancellable`. Вы можете отменять их до выполнения логики на сервере и сохранения изменений в БД.

- **`RegionCreateEvent`**: Вызывается при установке блока привата и успешном создании региона.
- **`RegionDestroyEvent`**: Вызывается при удалении/взрыве привата игроком или администратором.
- **`RegionExplosionEvent`**: Вызывается при нанесении урона региону взрывами.
- **`RegionDurabilityChangeEvent`**: Вызывается при изменении прочности региона.
- **`RegionMemberAddEvent` / `RegionMemberRemoveEvent`**: Добавление/удаление обычных участников региона.
- **`RegionOwnerAddEvent` / `RegionOwnerRemoveEvent`**: Добавление/удаление владельцев региона.

# Book Management API

## Описание
Это REST API для управления книгами. Вы можете создавать, обновлять, удалять и просматривать книги.

## Установка
Шаги для установки и запуска проекта локально:
1. Клонируйте репозиторий:
    ```bash
    git clone https://github.com/yourusername/book-management-api.git
    ```
2. Перейдите в каталог проекта:
    ```bash
    cd book-management-api
    ```
3. Соберите проект:
    ```bash
    ./mvnw clean install
    ```
4. Запустите приложение:
    ```bash
    ./mvnw spring-boot:run
    ```

## Использование
API предоставляет следующие конечные точки:

- **GET** `/` - Получить список всех книг.
- **GET** `/create-form` - Получить форму для создания книги.
- **POST** `/create` - Создать новую книгу.
- **GET** `/delete/{id}` - Удалить книгу по её ID.
- **GET** `/edit-form/{id}` - Получить форму для редактирования книги.
- **POST** `/update` - Обновить книгу.

## Примеры запросов
Примеры запросов с использованием `curl`:

Получить список книг:
```bash
curl -X GET http://localhost:8080/
curl -X POST http://localhost:8080/create -d 'title=Book Title&author=Author Name&isbn=1234567890'
curl -X GET http://localhost:8080/delete/{id}

## Технология разработки программного обеспечения
## Лабораторная работа №1: создание микросервиса на Spring Boot с базой данных
## Куракина Юлия Владимировна МАС2031 
### Цель лабораторной работы:
Знакомство с проектированием многослойной архитектуры Web-API (веб-приложений, микро-сервисов).

#### Инструкция по сборке и запуску приложения
1. Загрузить проект из Github. Команда для клонирования репозитория: `git clone https://github.com/IuliiaKurakina/simpleapiiiuk.git`
2. Установить Docker и PostgreSQL. Команда для установки PostgreSQL через терминал: `docker pull postgres`
3. Запустить Postgres Docker-контейнер. Команда для запуска через терминал: `docker run -e POSTGRES_PASSWORD=root -p 5432:5432`
4. Настроить подключение БД в `...\src\main\...application.properties`
5. Собрать приложение, при помощи Maven. Команда для сборки приложения:'mvn package -D maven.test.skip=true'
6. Создать образ Docker для запуска приложения в контейнере. Команда для создания: docker build . -t myapi:latest / 1.0
7. Запустить контейнер с приложением, используя Docker. Команда для запуска образа: docker run -p 8080:8080 myapi:latest / 1.0

#### 4 curl для обращения к ендпоинтам приложения:
1. Получить список всех клиентов: curl GET http://localhost:8080/api/v1/clients
2. Получить запись по id: curl GET http://localhost:8080/api/v1/clients/{id}
3. Добавить новую запись: curl POST http://localhost:8080/api/v1/clients 
{
	"serial": 4005,
	"number": "501578",
	"surname": "Ковалева",
	"name": "Алёна",
	"phone": "7903548878"
}
4. Удалить имеющуюся запись: curl DELETE http://localhost:8080/api/v1/{id}
5. Получить hostname: curl GET http://localhost:8080/api/v1/status 
 
## Лабораторная работа №3: CI/CD и деплой приложения в Heroku
### Цель лабораторной работы:
Знакомство с CI/CD и его реализацией на примере Travis CI и Heroku.
Ссылка на приложение: 
https://simpleapiiuk.herokuapp.com/api/v1/clients
https://simpleapiiuk.herokuapp.com/api/v1/status

Прикрепляем Travis CI badge:
[![Build Status](https://travis-ci.com/IuliiaKurakina/simpleapiiuk.svg?branch=main)](https://travis-ci.com/IuliiaKurakina/simpleapiiuk)

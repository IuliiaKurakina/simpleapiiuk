## Технология разработки программного обеспечения
## Лабораторная работа №1: создание микросервиса на Spring Boot с базой данных
## Куракина Юлия Владимировна МАС2031 

#### Целью лабораторной работы является знакомство с проектированием многослойной архитектуры Web-API (веб-приложений, микро-сервисов).

#### Дополнить README.md, в котором очень подробно будут изложены инструкции по сборке запуску приложения. 
#### Склонировать репозиторий. 
Команда для клонирования репозитория git clone https://github.com/IuliiaKurakina/simpleapiiiuk.git
#### Команда для сборки приложения в Maven из командной строки: 
Сборка приложения осуществляется системой сборки проектов Maven: mvn package -D maven.test.skip=true
#### Команда для сборки docker-образа из командной строки: 
Сборка образа: docker build . -t myapi:latest / 1.0
#### Команда для запуска docker-контейнера из docker-образа с указанием маппинга портов: 
Запуск образа: docker run -p 8080:8080 myapi:latest / 1.0
#### 4 curl (или больше) для обращения к ендпоинтам приложения:
#### Получить список всех клиентов: 
curl GET http://localhost:8080/api/v1/clients
#### Получить запись по id: 
curl GET http://localhost:8080/api/v1/clients/{id}
#### Добавить новую запись: 
curl SAVE http://localhost:8080/api/v1/clients 
{
	"serial": 4005,
	"number": "501578",
	"surname": "Ковалева",
	"name": "Алёна",
	"phone": "7903548878"
}
#### Удалить имеющуюся запись: 
curl POST http://localhost:8080/api/v1/{id}
#### Получить hostname: 
curl GET http://localhost:8080/api/v1/status 
 
## Лабораторная работа №3: CI/CD и деплой приложения в Heroku
#### Целью лабораторной работы является знакомство с CI/CD и его реализацией на примере Travis CI и Heroku.
Ссылка на приложение: 
https://simpleapiiuk.herokuapp.com/api/v1/clients
https://simpleapiiuk.herokuapp.com/api/v1/status

Прикрепляем Travis CI badge:
[![Build Status](https://travis-ci.com/IuliiaKurakina/simpleapiiuk.svg?branch=main)](https://travis-ci.com/IuliiaKurakina/simpleapiiuk)
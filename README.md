﻿## Технология разработки программного обеспечения
## Лабораторная работа №1: создание микросервиса на Spring Boot с базой данных
## Куракина Юлия Владимировна МАС2031 

#### Hard - Реализация в API 3-х таблиц и связей между ними через Hibernate ORM @OneToMany, @ManyToOne.

1. Получить список всех клиентов: 
- curl GET 'http://localhost:8080/api/v1/clients'
- curl GET 'http://localhost:8080/api/v1/accounts'
- curl GET 'http://localhost:8080/api/v1/deposits'
2. Получить запись по id: 
- curl GET http://localhost:8080/api/v1/clients/{id}
- curl GET http://localhost:8080/api/v1/accounts/{id}
- curl GET http://localhost:8080/api/v1/deposits/{id}
3. Добавить новую запись: 
- curl SAVE http://localhost:8080/api/v1/clients 
- curl SAVE http://localhost:8080/api/v1/accounts  / добавление к существующему родительскому id
- curl SAVE http://localhost:8080/api/v1/deposits  / добавление к существующему родительскому id
4. Удалить имеющуюся запись: 
- curl POST http://localhost:8080/api/v1/clients/{id}
- curl POST http://localhost:8080/api/v1/accounts/{id}
- curl POST http://localhost:8080/api/v1/deposits/{id}
5. Обновить имеющуюся запись: 
- curl PUT http://localhost:8080/api/v1/accounts/{id}
- curl PUT http://localhost:8080/api/v1/deposits/{id}
6. Получить hostname: 
- curl GET http://localhost:8080/api/v1/status 
7. Пример запроса:
```
{
	"date_of_birth": "2000-12-01",
	"passport": "4614 501578",
	"surname": "Ковалева",
	"name": "Алёна",
	"phone": "7903548878",
	"accounts": [
		{
			"opening_date": "2000-12-01",
			"deposit_amount": 100,
			"deposits": [
				{
					"closing_date": "2000-12-01",
					"interest_rate": 4,
					"prolongation": true
				}
			]
		}
	]
}
```
[Пример запроса в Insomnia](https://drive.google.com/drive/folders/1hO3hkDz8sVtnm3IPv-ZMsbI93jKhiI5y?usp=sharing)

Знакомство с CI/CD и его реализацией на примере Travis CI и Heroku. Ссылка на приложение:
1. Получить все записи:
- curl GET https://simpleapiiuk.herokuapp.com/api/v1/clients
- curl GET https://simpleapiiuk.herokuapp.com/api/v1/accounts
- curl GET https://simpleapiiuk.herokuapp.com/api/v1/deposits
2. Получить запись по id:
- curl GET https://simpleapiiuk.herokuapp.com/api/v1/clients/209
- curl GET https://simpleapiiuk.herokuapp.com/api/v1/accounts/210
- curl GET https://simpleapiiuk.herokuapp.com/api/v1/deposits/211
3. Получить hostname:
- curl GET https://simpleapiiuk.herokuapp.com/api/v1/status


![Build Status](https://travis-ci.com/IuliiaKurakina/simpleapiiuk.svg?branch=hard)

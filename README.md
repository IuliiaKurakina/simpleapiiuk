## Технология разработки программного обеспечения
## Куракина Юлия Владимировна МАС2031
#### Hard - Реализация в API 3-х таблиц и связей между ними через Hibernate ORM @OneToMany, @ManyToOne.

#### Получить список всех клиентов: 
curl GET http://localhost:8080/api/v1/clients
curl GET http://localhost:8080/api/v1/accounts
curl GET http://localhost:8080/api/v1/deposits
#### Получить запись по id: 
curl GET http://localhost:8080/api/v1/clients/{id}
curl GET http://localhost:8080/api/v1/accounts/{id}
curl GET http://localhost:8080/api/v1/deposits/{id}
#### Добавить новую запись: 
curl SAVE http://localhost:8080/api/v1/clients 
curl SAVE http://localhost:8080/api/v1/accounts  / добавление к существующему родительскому id
curl SAVE http://localhost:8080/api/v1/deposits  / добавление к существующему родительскому id
#### Удалить имеющуюся запись: 
curl POST http://localhost:8080/api/v1/clients/{id}
curl POST http://localhost:8080/api/v1/accounts/{id}
curl POST http://localhost:8080/api/v1/deposits/{id}
#### Обновить имеющуюся запись: 
curl POST http://localhost:8080/api/v1/accounts/{id}
curl POST http://localhost:8080/api/v1/deposits/{id}
#### Получить hostname: 
curl GET http://localhost:8080/api/v1/status 
#### Пример запроса:
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
 

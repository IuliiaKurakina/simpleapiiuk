﻿## Технология разработки программного обеспечения
## Лабораторная работа по улучшению проекта simpleapiiuk
## Куракина Юлия Владимировна МАС2031 
#### Hard - Требования к проекту:
- Подключение SonarQube к проекту, и запуск его на этапе сборки. 
- Реализация в API не менее 3-х таблиц и связей между ними.
- Написание unit-тестов на слой сервисов и контроллеров, покрытие тестами - не менее 80%.

1. Получить hostname: 
    - curl GET http://localhost:8080/api/v1/status
2. Список запросов к api клиента:
    - Добавить клиента
    ```shell script
    curl --request POST \
      --url http://localhost:8080/api/v1/clients \
      --header 'Content-Type: application/json' \
      --data '{
    	"name": "Вася", 
    	"phone": "355-55-15"
    }'
    ```
    - Получить клиента по {id = 1}
    ```shell script
    curl --request GET \
      --url http://localhost:8080/api/v1/clients/1
    ```
    - Получить всех клиентов
    ```shell script
    curl --request GET \
      --url http://localhost:8080/api/v1/clients
    ```
    - Удалить клиента по {id = 1}
    ```shell script
    curl --request DELETE \
      --url http://localhost:8080/api/v1/clients/1
    ```
3. Список запросов к api счетов:
    - Перепривязать счет к клиенту {clientId=2 accountId=1}
    ```shell script
    curl --request POST \
      --url 'http://localhost:8080/api/v1/accounts/setClientToAccount?clientId=2&accountId=1' \
      --header 'Content-Type: application/json'
    ```
    - Добавить счет к клиенту с {id = 1}
    ```shell script
    curl --request POST \
      --url http://localhost:8080/api/v1/accounts \
      --header 'Content-Type: application/json' \
      --data '{
    	"number": 7231,
    	"amount": 163,
    	"client": {
    							"id" : 1
    						}
    }'
    ```
    - Получить счет по {id = 1}
    ```shell script
    curl --request GET \
      --url http://localhost:8080/api/v1/accounts/2
    ```
    - Получить все счета 
    ```shell script
    curl --request GET \
      --url http://localhost:8080/api/v1/accounts
    ```
    - Удалить счет {id = 1}
    ```shell script
    curl --request DELETE \
      --url http://localhost:8080/api/v1/accounts/1
    ```
3. Список запросов к api вкладов:
    - Перепривязать вклад к счету {accountId=2 depositId1}
    ```shell script
    curl --request POST \
      --url 'http://localhost:8080/api/v1/deposits/setAccountToDeposit?accountId=2&depositId=1&=' \
      --header 'Content-Type: application/json'
    ```
    - Добавить вклад
    ```shell script
    curl --request POST \
      --url http://localhost:8080/api/v1/deposits \
      --header 'Content-Type: application/json' \
      --data '{
    	"rate": 2,
    	"term": 2,
    	"account": {
    							"id" : 1
    						}
    }'
    ```
    - Получить все вклады
    ```shell script
    curl --request GET \
      --url http://localhost:8080/api/v1/deposits
    ```
    - Получить вклад {id = 1}
    ```shell script
    curl --request GET \
      --url http://localhost:8080/api/v1/deposits/1
    ```
    - Удалить вклад {id = 1}
    ```shell script
    curl --request DELETE \
      --url http://localhost:8080/api/v1/deposits/1
    ```

[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-black.svg)](https://sonarcloud.io/dashboard?id=ru.mtuci%3Asimpleapiiuk)
![Build Status](https://travis-ci.com/IuliiaKurakina/simpleapiiuk.svg?branch=hard)

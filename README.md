# job4j_car_accident 
[![Build Status](https://app.travis-ci.com/Jazzik42/job4j_car_accident.svg?branch=master)](https://app.travis-ci.com/Jazzik42/job4j_car_accident)
[![codecov](https://codecov.io/gh/Jazzik42/job4j_car_accident/branch/master/graph/badge.svg?token=IK4YY07Q8R)](https://codecov.io/gh/Jazzik42/job4j_car_accident)
# Технологии:
* Spring (MVC, ORM, Data, Security)
* JSP
* JSTL
* JDBC
* Hibernate
* PostgreSQL
* Maven
* Tomcat
1. Данное веб-приложение представляет собой систему учёта автонарушений.
2. При добавлении нового инцидента нужно указать название, описание, адрес, выбрать тип инцидента (возможно выбрать только 1 тип) и статьи (возможно выбрать несколько).
3. Есть возможность редактировать уже существующие инциденты.
4. Список доступных статей и типов можно поменять.
5. Неавторизованные пользователи с приложением работать не могут.
6. Регистрация основана на контроллере RegControl, который инициирует сохранение новых пользователей в БД.
7. Авторизация построена на Spring Security. Все зарегистрированные пользователи хранятся в БД. Все пароли пользователей хранятся в БД в закодированном виде.
8. По умолчанию в БД хранится пользователь с ролью администратора. Его логин - root, пароль - secret.
9. Для работы с БД по умолчанию используется Spring Data JPA. Есть также возможность заменить данную реализацию на Spring JDBC, Spring HibernateTemplate или же хранить все данные в памяти.

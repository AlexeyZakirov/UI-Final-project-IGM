# Проект по автоматизации тестирования [IGM](https://igm.gg/)

<a href="https://igm.gg/"><img src="media\screenshots\igm.jpg" width="850" alt="Bork"/></a>

---
##  **Содержание:**

---

* [Технологии и инструменты](#технологии-и-инструменты)
* [Реализованные автоматизированные тесты](#реализованные-автоматизированные-тесты)
* [Запуск тестов - сборка в Jenkins](#запуск-тестов---сборка-в-jenkins)
* [Запуск тестов - локальный](#запуск-тестов---локальный)
* [Allure Report](#allure-report)
* [Allure TestOps](#allure-testops)
* [Jira](#jira)
* [Уведомление в Telegram о результатах тестов](#уведомление-в-telegram-о-результатах-тестов)
* [Пример видео выполнения теста на Selenoid](#пример-видео-выполнения-теста-на-selenoid)


## Технологии и инструменты:

---


<p align="center">  
<a href="https://www.jetbrains.com/idea/"><img src="media/logo/Intelij_IDEA.svg" width="50" height="50"  alt="IDEA"/></a>  
<a href="https://www.java.com/"><img src="media/logo/Java.svg" width="50" height="50"  alt="Java"/></a>  
<a href="https://github.com/"><img src="media/logo/GitHub.svg" width="50" height="50"  alt="Github"/></a>  
<a href="https://junit.org/junit5/"><img src="media/logo/JUnit5.svg" width="50" height="50"  alt="JUnit 5"/></a>  
<a href="https://gradle.org/"><img src="media/logo/Gradle.svg" width="50" height="50"  alt="Gradle"/></a>  
<a href="https://selenide.org/"><img src="media/logo/Selenide.svg" width="50" height="50"  alt="Selenide"/></a>  
<a href="https://aerokube.com/selenoid/"><img src="media/logo/Selenoid.svg" width="50" height="50"  alt="Selenoid"/></a>
<a href="https://www.jenkins.io/"><img src="media/logo/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a>
<a href="https://github.com/allure-framework/"><img src="media/logo/Allure_Report.svg" width="50" height="50"  alt="Allure"/></a>
</p>

---

## Реализованные автоматизированные тесты:

---
### Тесты на главную страницу
- *Переключение активной игры на следующую в слайдере TopGames посредством кнопки 'Следующий слайд'*
- *Переключение активной игры на предыдущую в слайдере TopGames посредством кнопки 'Предыдущий слайд'*
- *Переключение активной игры в слайдере TopGames посредством рандомного клика на видимую иконку*
### Тесты на компонент хэдер
- *Наличие всех элементов в хэдере*
### Тесты на поисковую строку
- *Поиск существующей игры с переходом на страницу игры*
- *Поиск несуществующей игры*
- *Сброс поискового запроса*
### Тесты на раздел 'Каталог'
- *Установка диапазона цены предлагаемых игр вручную*
- *Установка диапазона цены предлагаемых игр через радио баттоны*
- *Сброс только выбранного фильтра*
- *Сброс всех установленных фильтров вручную*
- *Сброс всех установленных фильтров через кнопку*
- *Добавить игру в раздел 'Желаемое', будучи авторизованным*
- *Добавить игру в раздел 'Желаемое', будучи не авторизованным*


---

## Запуск тестов - сборка в [Jenkins](https://jenkins.autotests.cloud/job/30-tinwhip-UI_FINAL_PROJECT/):

---
 
<p align="center">  
<img title="Jenkins" src="media\screenshots\Jenkins.png" width="850">  
</p> 

***Для запуска тестов необходимо кликнуть 'Build with Parameters' и выбрать testType***

***Ключ testType позволяет запустить тесты с конкретным тэгом, либо выбор всех тестов для запуска:***
- *test - запускает все тесты*
- *header_test - запускает тесты на компонент хэдер*
- *main_test - запускает тесты на главную страницу*
- *catalog_test - запускает тесты на раздел 'Каталог'*
- *search_test - запускает тесты на поисковую строку*

## Запуск тестов - локальный
___
***Локальный запуск (вместо test можно выбрать другой ключ, см. пункт выше):***
- *-Denv=remote - запускает тесты удаленно посредством Selenoid*
- *-Denv=local - запускает тесты локально*

***Пример команды запуска:***
```  
gradle clean test -Denv=remote
```
*Также реализован запуск отдельного теста внутри IDE, посредством запуска через зелёный треугольник*

---
## [Allure Report](https://jenkins.autotests.cloud/job/30-tinwhip-UI_FINAL_PROJECT/allure/)

---

## Основная страница отчёта
***С инфомацией о количестве запущенных тестов, статусе прохождения и графика TREND, отображающего тенденцию прогона тестов***

<p align="center">  
<img title="Allure Report" src="media\screenshots\Allure Report.png" width="850">  
</p>  

## Тест-кейсы.
***Содержат подробное описание шагов со скриншотами, видео и Page Source по каждому тесту***

<p align="center">  
<img title="Allure Tests" src="media\screenshots\AllureTests.png" width="850">  
</p> 


---
## [Allure TestOps](https://allure.autotests.cloud/project/4564/test-cases?treeId=0)

---

## Реализована интеграция с Allure TestOps

<p align="center">  
<img title="Allure TestOps" src="media\screenshots\AllureTestOps.png" width="850">  
</p>  

---
## [Jira](https://jira.autotests.cloud/browse/HOMEWORK-1382)

---

## Реализована интеграция с Jira

<p align="center">  
<img title="Jira" src="media\screenshots\Jira1.png" width="850">  
</p>  

---

## Уведомление в Telegram о результатах тестов
____

<p align="center">  
<img title="Telegram" src="media\screenshots\telegram.png" width="850">  
</p> 

## Пример видео выполнения теста на Selenoid
____
<p align="center">
<img title="Selenoid Video" src="media/screenshots/video.gif" width="550" height="350"  alt="video">   
</p>
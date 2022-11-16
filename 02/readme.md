# Домашнее задание к занятию "Получаем картинку от NASA"

### Чеклист готовности к домашнему заданию:

1. Установлена IDEA на ваш компьютер [(см инструкцию в задании 1)](https://github.com/netology-code/jdfree-homeworks/tree/jdfree-6/01#readme)

------

### Описание 

Нужно воспользоваться публичным API NASA и скачать ежедневно выгружаемую им изображение или другой контент (например видео).<br>
Несмотря на то, что API публичный, доступ к нему предоставляется по ключу, который достаточно просто получить по адресу: https://api.nasa.gov/. Перейдя по ссылке, заполняем личными данными поля:
* First Name
* Last Name
* Email

В ответ (а так же на почтовый адрес) будет выслан ключ. С этим ключом нужно делать запросы к API.

Итак, чтобы получить ссылку на картинку или другой контент, нужно:

1. Сделать запрос по адресу: https://api.nasa.gov/planetary/apod?api_key=ВАШ_КЛЮЧ
2. Разобрать полученный ответ
3. В ответе найти поле `url` - оно содержит адрес на изображение или другой контент (например видео), который нужно скачать и сохранить локально (на своем компьютере), имя сохраняемого файла нужно взять из части url (из примера ниже DSC1028_PetersNEOWISEAuroralSpike_800.jpg)
4. Проверить что сохраненный файл открывается.

Пример ответа сервиса NASA

```cs{
{
  "copyright": "Bill Peters",
  "date": "2020-07-17",
  "explanation": "After local midnight on July 14 comet NEOWISE was still above the horizon for Goldenrod, Alberta, Canada, just north of Calgary, planet Earth. In this snapshot it makes for an awesome night with dancing displays of the northern lights. The long-tailed comet and auroral displays are beautiful apparitions in the north these days. Both show the influence of spaceweather and the wind from the Sun. Skygazers have widely welcomed the visitor from the Oort cloud, though C/2020 F3 (NEOWISE) is in an orbit that is now taking it out of the inner Solar System.  Comet NEOWISE Images: July 16 | July 15 | July 14 | July 13 | July 12 | July 11 | July 10 & earlier",
  "hdurl": "https://apod.nasa.gov/apod/image/2007/DSC1028_PetersNEOWISEAuroralSpike.jpg",
  "media_type": "image",
  "service_version": "v1",
  "title": "NEOWISE of the North",
  "url": "https://apod.nasa.gov/apod/image/2007/DSC1028_PetersNEOWISEAuroralSpike_800.jpg"
}
```
### Что нужно сделать
1. Получить ключ для API NASA по адресу: https://api.nasa.gov/ <br>
2. Сделать запрос из кода: https://api.nasa.gov/planetary/apod?api_key=ВАШ_КЛЮЧ <br>
3. Создать класс ответа и разобрать json-ответ с помощью Jackson или Gson <br>
4. Найти поле url в ответе и скачать массив byte, который сохранить в файл <br>
5. Имя файла должно быть взято из части url

### Реализация
1. Создайте проект `maven` или `gradle` и добавьте в pom.xml или gradle.build библиотеку apache httpclient

Пример:

```cs{
<dependency>
   <groupId>org.apache.httpcomponents</groupId>
   <artifactId>httpclient</artifactId>
   <version>4.5.12</version>
</dependency>
```

2. Создайте метод в который добавьте и настройте класс `CloseableHttpClient` например с помощью builder

```cs{
CloseableHttpClient httpClient = HttpClientBuilder.create()
    .setDefaultRequestConfig(RequestConfig.custom()
        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
        .build())
    .build();
```

3. Добавьте объект запроса HttpGet request = new HttpGet("https://api.nasa.gov/planetary/apod?api_key=ВАШ_КЛЮЧ") и вызовите удаленный сервис `CloseableHttpResponse response = httpClient.execute(request)`;<br>
4. Добавьте в pom.xml или gradle.build библиотеку для работы с json

Пример:

```cs{
<dependency>
   <groupId>com.fasterxml.jackson.core</groupId>
   <artifactId>jackson-databind</artifactId>
   <version>2.11.1</version>
</dependency>
```

5. Создайте класс, в который будем преобразовывать json ответ от сервера;<br>
6. Преобразуйте json в java-объект;<br>
7. В java-объекте найдите поле url и сделайте с ним еще один http-запрос с помощью уже созданного httpClient;<br>
8. Сохраните тело ответа в файл с именем части url;<br>
9. Проверьте, что файл скачивается и открывается;<br>
10. Готово!


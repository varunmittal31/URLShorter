URL Shorter

This project demonstrates to create short URL and redirect it to original URL by Spring boot.

Execution

java -jar spring-shorturl-0.0.1-SNAPSHOT.jar

API Details


Create Short URL:
---------------------------------

http://localhost:10092/test/shorturl/

Request body:
--

Post Request

https://google.com


Response body:
--
{
    "shortURL": "http://localhost:10092/test/shorturl/c5cd9fc1",
    "url": "https://google.com",
    "created": "2021-05-23T18:52:20.0876033"
}

Response codes:

HTTP Status	Description
200	successful operation
500	internal server error

Redirecting to original URL
--------------------------------------
Write below URL in browser with id

http://localhost:10092/test/shorturl/{id}

This URL will be redirected to original URL


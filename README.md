DB usage (docker-compose.yml is provided in the project root directory):
* Type: MySQL
* Endpoint: localhost:3306
* Username: root
* Password: root

Assumptions:
* MySQL database "db_main" exists.

Sample API requests:
* curl -H "Content-Type: application/json" -X POST -d '{"url":"https://fastly.picsum.photos/id/10/2500/1667.jpg?hmac=J04WWC_ebchx3WwzbM-Z4_KC_LeLBWr5LZMaAkWkF68","durationMillis":3000}' http://localhost:8080/addImage
* curl -X DELETE http://localhost:8080/deleteImage/1
* curl -H "Content-Type: application/json" -X GET -d '{"keywords":["ab","ba"],"minDurationMillis":2000,"maxDurationMillis":4000}' http://localhost:8080/images/search
* curl -H "Content-Type: application/json" -X POST -d '{"name":"slideshowAbc","images":[{"id":1,"durationMillis":5000},{"id":2,"durationMillis":7000}]}' http://localhost:8080/addSlideshow
* curl -X DELETE http://localhost:8080/deleteSlideshow/1
* curl -X GET http://localhost:8080/slideshow/1/slideshowOrder
* curl -X POST http://localhost:8080/slideshow/1/proofOfPlay/1

Notes:
* The routes of the last 2 API endpoints in the requirements specification were altered for preserving naming consistency.

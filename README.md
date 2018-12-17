# homework


running: mvn package spring-boot:run

test calls:

apply:

curl -X POST \
  http://localhost:8080/loans \
  -H 'Content-Type: application/json' \
  -d '{
	"amount" : 2000,
	"termInDays" : 31
}'

extend:

curl -X POST \
  http://localhost:8080/loans/1/extend

get:

curl -X GET \
  http://localhost:8080/loans/1




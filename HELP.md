docker network create restaurante-net

docker run -d --name mysql-db --network restaurante-net -p 3306:3306 -e MYSQL_ROOT_PASSWORD=admin -e MYSQL_DATABASE=restaurante -e MYSQL_PASSWORD='!@#$1234qW' -e MYSQL_USER=restaurante_user mysql:9.3.0

docker build -t restaurante .

docker run -p 8080:8080 --network restaurante-net \
-e URL_DATABASE=jdbc:mysql://mysql-db:3306/restaurante \
-e USER_DATABASE=restaurante_user \
-e PASS_DATABASE='!@#$1234qW' \
restaurante

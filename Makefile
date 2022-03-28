rebuild-docker:
	docker-compose down
	gradle clean build -p mitrasoft_server
	gradle clean build -p mitrasoft_rest_client
	docker-compose up --build
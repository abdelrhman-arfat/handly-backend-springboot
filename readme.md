# Handly Project (Spring Boot + Docker + Hot Reload)

## 🚀 Build and Run with Docker Compose

### 1. Build containers
```bash
docker-compose build -d
```

### Down containers 
```bash
docker-compose build -d
```


### Deal with the spring boot application
```bash
docker exec -it springboot-app bash
mvn compile -Dspring-boot.run.fork=false
```
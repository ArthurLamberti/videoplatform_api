# Criar as docker networks
docker network create adm_videos_services

# Criar as pastas com permissões
mkdir -m 777 .docker
mkdir -m 777 .docker/keycloak

docker compose -f app/docker-compose.yml up -d

echo "Subindo containers..."
sleep 20
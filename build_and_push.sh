#!/bin/bash

# Construir la imagen Docker
docker build -t tu-usuario-en-dockerhub/nombre-de-tu-imagen:tag .

# Iniciar sesión en Docker Hub
docker login

# Etiquetar la imagen
docker tag tu-usuario-en-dockerhub/nombre-de-tu-imagen:tag tu-usuario-en-dockerhub/nombre-de-tu-imagen:tag

# Subir la imagen a Docker Hub
docker push tu-usuario-en-dockerhub/nombre-de-tu-imagen:tag

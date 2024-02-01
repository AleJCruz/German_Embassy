#!/bin/bash

# Recuperar secretos desde Azure Key Vault
SECRET1=$(az keyvault secret show --vault-name tu-keyvault --name secret1 --query value --output tsv)
SECRET2=$(az keyvault secret show --vault-name tu-keyvault --name secret2 --query value --output tsv)

# Configurar variables de entorno
export SECRET1
export SECRET2

# Ejecutar la aplicación Spring Boot
java -jar app.jar

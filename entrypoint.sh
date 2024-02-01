#!/bin/bash

# Recuperar secretos desde Azure Key Vault
SECRET1=$(az keyvault secret show --vault-name ExampleAle --name SecretEx  --query value --output tsv)

# Configurar variables de entorno
export SECRET1

# Ejecutar la aplicación Spring Boot
java -jar app.jar

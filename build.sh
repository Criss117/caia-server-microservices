#!/bin/bash

base_dir="$(dirname "$0")"

projects=("config-server" "caia-eureka" "caia-gateway")

for proyecto in "${projects[@]}"; do
  echo $base_dir
  echo "Compilando $proyecto..."
  cd "$base_dir/$proyecto" || { echo "No se pudo acceder a $proyecto"; exit 1; }
  mvn clean install -DskipTests || { echo "Error al compilar $proyecto"; exit 1; }
  cd ..
  echo "$proyecto compilado con éxito."
done

echo "Todos los proyectos han sido compilados."

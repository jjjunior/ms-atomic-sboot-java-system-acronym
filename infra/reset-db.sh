#!/bin/bash

set -e

# Nome do volume — ajuste se necessário
VOLUME_NAME="postgres_data"
PROJECT_NAME=$(basename "$PWD")
FULL_VOLUME_NAME="${PROJECT_NAME}_${VOLUME_NAME}"

echo "🔻 Parando containers..."
docker-compose down

echo "🧹 Removendo volume de dados: $FULL_VOLUME_NAME"
docker volume rm "$FULL_VOLUME_NAME" || {
  echo "⚠️  Volume não encontrado ou já removido. Continuando..."
}

echo "⬆️ Subindo containers novamente com volume limpo..."
docker-compose up -d

echo "✅ Banco de dados reiniciado com sucesso!"
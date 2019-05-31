#!/usr/bin/env bash

VERSAO=""

MSG_COMMIT="$1"

APP="recebimento-gtin"
HOST="10.1.10.244"
USER="root"
PASS="musabela"
DIR="/root/docker/${APP}/"

ARQ_VERSAO="src/main/resources/versao.txt"

VERSAO_OLD=`cat ${ARQ_VERSAO}`

if [[ "$VERSAO_OLD" == "" ]]
then
  VERSAO_OLD="1.0"
fi

sshpass -p${PASS} ssh ${USER}@${HOST} cp -vf ${DIR}/${APP}.war /${DIR}/bak/${APP}_${VERSAO_OLD}.war

if [[ "$VERSAO" = "" ]]
then
  V1=`echo $VERSAO_OLD | cut -d"." -f1`
  V2=`echo $VERSAO_OLD | cut -d"." -f2`
  V3=$(( "$V2 + 1" ))
  VERSAO="${V1}.${V3}"
fi

echo ${VERSAO} > ${ARQ_VERSAO}

git.sh "deploy $VERSAO: $MSG_COMMIT"

gradle

sshpass -p${PASS} rsync -av \
       build/libs/${APP}.war ${USER}@${HOST}:${DIR}/${APP}.war


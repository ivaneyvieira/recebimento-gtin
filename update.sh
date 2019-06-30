#!/usr/bin/env bash

git pull
gradle clean build
gradle clean build -p rest

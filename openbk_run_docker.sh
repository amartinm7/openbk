#!/bin/bash

docker run -p 8000:8000 -e SPRING_PROFILES_ACTIVE=dev -it ms-openbk

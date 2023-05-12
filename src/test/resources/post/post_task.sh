#!/bin/bash

printf "Task\n"
curl -X 'POST' \
  'http://localhost:8000/v1/task' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d "{
      \"name\": \"task#1\",
      \"description\": \"lorem ipsum\"
     }"
printf "\nmessage sent"


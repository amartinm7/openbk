#!/bin/bash

printf "Task\n"
curl -X 'PUT' \
  'http://localhost:8000/v1/task/3deab62e-2fe2-4f30-aede-96484fa3f738' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d "{
      \"name\": \"task#1\",
      \"description\": \"lorem ipsum\",
      \"priority\": \"4\"
     }"
printf "\nmessage sent"


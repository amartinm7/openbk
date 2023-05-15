# openbk: Task CRUD springboot microservice 

To run the app and the database execute

```bash
docker-compose up -d
```

Look the endpoint on the open-api url [open-api](http://localhost:8000/swagger-ui/index.html)

Once everything is alright let's go to execute the endpoints.

look for the `testing.http` file and execute the endpoints

Another alternative is to execute the scripts over the test/resources folder

```bash
sh create/create_task.sh
sh update/update_task.sh
sh retrieve/retrieve_task.sh
sh delete/delete_task.sh
```



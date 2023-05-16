# openbk: Task CRUD springboot microservice 

## Some intructions to run the project.

To run the app and the database execute

```bash
docker-compose up -d
```

Look the endpoint on the open-api url [open-api](http://localhost:8000/swagger-ui/index.html)

Once everything is alright let's go to execute the endpoints.

look for the `src/test/testing.http` file and execute the endpoints

Another alternative is to execute the scripts over the test/resources folder

```bash
sh src/test/resources/create/create_task.sh
sh src/test/resources/update/update_task.sh
sh src/test/resources/retrieve/retrieve_task.sh
sh src/test/resources/delete/delete_task.sh
```
## Run the documentation

run the server to watch the documenation on the [doc folder](http://localhost:3000)
```bash
docsify serve _docs
```





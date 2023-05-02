# Personal observation:

- I suppose I did not fully understand the task: I did not get why we should use a database for retaining the info on
  worker and on worker statistics (still, if we do, why use multiple tables and not just one?), since these are computed
  on the fly by DockerClient;
    - as a result, I created a scheduler that recreates the database every 1 minute (not efficient...) to have
      approximately the freshest data;
    - because of this, I am not that sure that I should have used @OneToOne and GET/PUT since sometimes there are
      resources created in the database (hence, maybe a POST method would have worked better)
- run by calling ```./run.bat```
- when the app is up and running, open Postman, import the file "openfabric.postman_collection.json", and run the
  requests

--------------------------------

# Backend Junior Developer (Intern) Test

Welcome! and thank you for applying!

## Requirement

Your task is to add the necessary features to the current project's API blueprint structure to enable
it to manage **Docker** containers, also referred to as workers, as fallows:

🔄 Integrate the 🐳 Docker Java library (https://github.com/docker-java/docker-java/blob/main/docs/getting_started.md) to
enable connect and manage 👷‍♀️ workers.

➕ Add the required fields and database migration for the 👷‍♂️ Worker entity to keep **all** the information associated
on available at the container level (like 🔌 ports, 👨‍💼 name, 🟢 status, etc..)

🆕 Add required entities and tables to track the 👷‍♂️ Worker statistics.

🆙 Update the 👷‍♂️ WorkerController to add actions for:

* 📄 List workers (paginated)
* ▶️ Start and ⏹️ Stop worker
* 🔍 Get worker information
* 📊 Get worker statistics

## Constraints and restrictions

You are free to use any package or library you see feet as long as you follow these rules:

* 👎 You can't copy and paste from other peoples work

## Run

Once you have established a connection to the database, you can test the application by using Swagger.

You can access Swagger by navigating to the following link: http://localhost:8080/swagger-ui/#/.

## Submission

Your solution must be uploaded on GitHub, and submit us the link in **max 1 week** after receiving the task.

## Note

Keep in mind that this is the project that will be used to evaluate your skills.
So we do expect you to make sure that the app is fully functional and doesn't have any obvious missing pieces.
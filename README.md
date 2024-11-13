# Course Management API


This project is a challenge from a Java course at [Rockeseat](https://app.rocketseat.com.br/). In this challenge, we reinforce the concepts learned in the Java Spring Boot module through practical application. In this case, you will develop a fictitious API for a programming course company, where you will implement basic CRUD operations to manage courses.

The API should provide the following features:

1. Create a new course
2. List all courses
3. Update a course by its id
4. Delete a course by its id

## API Endpoints and Business Logic

### Course Properties
Before we dive into the API routes, let's understand the structure (properties) of a course:

- `id`: Unique identifier for each course
- `name`: Name of the course
- `category`: Category of the course
- `description`: Description of the course
- `active`: Defines whether the course is active or not
- `created_at`: The date when the course was created
- `updated_at`: The date when the course was last updated (this should change whenever a course is updated)

### Routes

`POST - /cursos`
* This endpoint allows the creation of a new course in the database by sending the name, category, and description fields in the request body.
* When a course is created, the id, created_at, and updated_at fields will be automatically filled, based on the properties defined above.

`GET - /cursos`
* This endpoint retrieves a list of all courses saved in the database.
* You can also filter the courses by name and category.

`PUT - /cursos/:id`
* This endpoint allows you to update a course by its id.
* The request body should contain the name, category, and/or description fields to be updated.
* If only name is provided, category and description cannot be updated, and vice versa.
* If the active field is included in this request, it will be ignored, as this update should be handled through the PATCH route.

`DELETE - /cursos/:id`
* This endpoint allows you to remove a course by its id.

`PATCH - /cursos/:id/active`
* This endpoint allows you to toggle the active status of a course, setting it to true or false.

## Technologies Used

- **Java 21**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **H2 Database** (In-memory for test)
- **PostgreSQL** (Running in Docker)
- **Maven** (Build tool)
- **JUnit** (Testing framework)

## Getting Started

### Prerequisites

- Java 21 or higher
- Maven 3.8 or higher
- Docker (for running PostgreSQL)

### Installation

1. Clone the repository:
```
   git clone https://github.com/nathaliadv/course-platform.git
   cd course-platform
 ```

2. Start the PostgreSQL service using Docker Compose: ```docker-compose up -d```.
This command will start all services defined in your docker-compose.yml file, including PostgreSQL.


3. Build the project: ```mvn clean install```.


4. Run the application: ```mvn spring-boot:run```


> You can find examples of requests in the **Rocketseat.postman_collection.json** file included in the project. This file contains a collection of requests for interacting with the API, which can be imported into Postman for testing and exploration.


## API Documentation
This API is documented using **Swagger**. Swagger provides a user-friendly UI to interact with and test the API endpoints, as well as a detailed view of each endpoint's structure, parameters, and possible responses.

Once the application is running, you can access the Swagger documentation at:

`http://localhost:8080/swagger-ui.html` for the Swagger UI (Graphical interface)
`http://localhost:8080/v3/api-docs for the raw OpenAPI specification in JSON format
Swagger simplifies exploring and testing the API features directly from your browser, providing real-time feedback on requests and responses.


## Contributing
Feel free to fork this project, make changes, and create pull requests. All contributions are welcome!

## License
This project is licensed under the MIT License.

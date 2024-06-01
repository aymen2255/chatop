
# Projet Openclassroom: Chatop API

 

Generated with Java 22 and Spring




## Installation

Clone the repository

```bash
    git clone https://github.com/aymen2255/chatop.git
```

```bash
    Run sql file available at ressources/sql/script.sql
```

In the Maven tool window or view, select the project and then run the install command. This will download and install the dependencies that you added to the pom.xml file.

Note: If you are using a command-line interface (CLI) instead of an IDE, you can run below command in the project's root directory to install the Maven dependencies.

```bash
    mvn install
```


## Environment Variables

To run this project, you will need to add the following environment variables to your application.properties file

`db_name`

`db_username`

`db_password`

`upload_file_path`

`secretKey`

`jwt_expiration`



## Run Application


Run server inside you IDE or use this command-line

```bash
    mvn spring-boot:run
```


## API Reference Example

#### Registration

```http
  POST /api/auth/register
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `name` | `string` | **Required** |
| `email` | `string` | **Required** |
| `password` | `string` | **Required** |

#### Login

```http
  POST /api/login
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |

#### Get all rentals

```http
  GET /api/rentals
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |

#### Get one rental

```http
  GET /api/rentals/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `api_key` | `string` | **Required**. Your API key |
| `id`      | `integer` | **Required**. Id of rental to fetch |

#### For more Endpoints

The API documentation is available at the link below

#### http://localhost:3001/swagger-ui/index.html


## License

[MIT](https://choosealicense.com/licenses/mit/)


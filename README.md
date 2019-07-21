# be.care coding assignment

Technical assignment test for be.care, July 2019.

## Description

A description of the expected assignment is located [here](assignment.pdf).

## Getting Started

### Requirements

This project works on the following operating systems:

- Linux,
- macOS,
- Windows.

This project requires the following item to be installed and configured in your environment:

- Java 8.

### Installation

1. Clone the repository to your local computer.

    ```sh
    git clone https://github.com/alxlve/coding-assignment-becare
    ```

2. Enter the project directory.

    ```sh
    cd coding-assignment-becare
    ```

3. Package the project with Maven.

    ```sh
    ./mvnw package
    ```

4. Run the application.

    ```sh
    java -jar target/coding-assignment.becare-<version>.jar
    ```

5. Navigate to [http://localhost:8080](http://localhost:8080).

### Usage

You can run the application with one of the following Spring Boot profiles:

- `debug-dev`,
- `dev`,
- `staging`,
- `prod`.

To select a profile append the parameter `-Dspring.profiles.active=<profile-name>` on the `java` command line.
The default profile to be selected is `dev` if you do not specify any on the command line.

Here is a comparison chart between all the embedded profiles:

|                          | `debug-dev`       | `dev`     | `staging` | `prod` |
|--------------------------|-------------------|-----------|-----------|--------|
| **Database engine**      | H2                | H2        | H2        | H2     |
| **Storage type**         | File              | In-Memory | File      | File   |
| **Schema migrator**      | Hibernate         | Hibernate | Flyway    | Flyway |
| **Database population**  | Yes               | Yes       | Yes       | No     |
| **Database persistence** | Yes               | No        | Yes       | Yes    |
| **Might need deletion**  | Yes               | No        | Yes       | No     |
| **Verbosity**            | Trace + Hibernate | Debug     | Info      | Info   |
| **Performance**          | Horrible          | Bad       | Ok        | Ok     |

*The database population file is located at `$PROJECT_DIR$/src/main/resources/db/data/standard/populate.sql`.*

Here are the URL of all profiles to their database:

|             | **URL**                                                                              |
|-------------|--------------------------------------------------------------------------------------|
| `debug-dev` | `jdbc:h2:file:$PROJECT_DIR$/.h2/coding_assignment_becare_debug_dev;AUTO_SERVER=TRUE` |
| `dev`       | `jdbc:h2:mem:coding_assignment_becare_dev`                                           |
| `staging`   | `jdbc:h2:file:$PROJECT_DIR$/.h2/coding_assignment_becare_staging;AUTO_SERVER=TRUE`   |
| `prod`      | `jdbc:h2:file:$PROJECT_DIR$/.h2/coding_assignment_becare_prod;AUTO_SERVER=TRUE`      |

*If you wish to reset a database just delete its file after the application shutdown.*

## Checking the assignment

1. Launch the application with the `prod` profile.

    ```sh
    java -jar target/coding-assignment.becare-<version>.jar -Dspring.profiles.active=prod
    ```

2. Navigate to [http://localhost:8080](http://localhost:8080).

3. Launch Postman and do some requests.

    Exports are located at `$PROJECT_DIR$/postman`.

### Checking the sources

This project was implemented on IntelliJ IDEA. All the needed configuration is kept in this repository.

You do not need to import this project in IntelliJ IDEA, just open it!

## TODO

- Implement more tests (they are too few tests in number, I lack of time).
- Reach 90% code coverage.

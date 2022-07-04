# SBAB

## Challenge

The task was to write an application that finds out which bus lines that have the most bus stops on their route and then to present the top 10 bus lines in a nice and formatted way in a web browser.

## bus-route-service

A microservice that integrates with Trafiklab API to fetch, hold and ultimately expose information about:

- the bus lines with the most stops on their routes and
- the stop points for a given bus line

## How do I run the application?

To start the application, simply run:

`./gradlew bootRun`

Once the application has successfully started, open up a web browser and navigate to:

- http://localhost:8080/

To view the content of the database, simply go to:

- http://localhost:8080/h2-console

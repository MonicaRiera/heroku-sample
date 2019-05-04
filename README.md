# Heroku Sample App

Simple application made with Spring Boot and configured to be deployed in Heroku.

It needs the environment variable `MONGO_URI` to connect to mongo.
It will use a `test` database there.

The application has a `SampleApi` with endpoints to `insertWord` and `listWords`.
It also has endpoints to `range` that returns a range of integers given the last number and saves it to the database,
and `nameCode` and `codes` that calculates the integer code of a name and also saves it in the database.



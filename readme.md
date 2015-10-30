Build with `mvn clean install docker:build`

And try to run the built docker image

    docker run -it <id of image>

The application can run locally without docker with

    mvn clean install camel:run

        
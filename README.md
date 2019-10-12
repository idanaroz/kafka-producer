# producer

This project main feaure is a Service has a POST method.
The POST method gets a string and produces it to Kafka.

In order to produce it to the right kafka bootstrapServer it needs the relevant kafka bootstrapServer target. 

To run this server kafka_bootstrapServer input is needed as can be seen in application.properties file. 

In addition there is an easy option to create docker container by using the Dockerfile. TO achieve that:
# 1. create a jar for the project.
Let's same the name is: kafka-server-producer:1.1-SNAPSHOT.jar

# 2. Edit the Docker file 
so the env will be your private ip. 

# 3. build the image
sudo docker build -t idanaroz/kafka-server-producer:1.1-SNAPSHOT -t  idanaroz/kafka-server-producer:latest .

# 4.  run the container
sudo docker run -d -p 8080:8080  idanaroz/kafka-server-producer:1.1-SNAPSHOT




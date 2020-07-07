Table of Contents

• Basic Overview

<<<<<<< HEAD
- Authorities - [ROLE_USER, SCOPE_https://www.googleapis.com/auth/userinfo.email, 
                      SCOPE_https://www.googleapis.com/auth/userinfo.profile, SCOPE_openid]
- CLASS       - class org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken

======================================================================

• To retrive all the Roles and Permissions of a specific user 
• GET:   https://localhost:4711/users/getRolesById/1

  Resonse
  --------

[
    {
        "gts_role_id": 1,
        "gts_role_name": "ROLE_USER",
        "permissions": [
            {
                "gts_permission_id": 1,
                "gts_permission_name": "CAN_CREATE_ACCOUNT",
                "gts_permission_status": false,
                "gts_permission_description": null
            },
            {
                "gts_permission_id": 2,
                "gts_permission_name": "CAN_UPDATE_ACCCOUNT",
                "gts_permission_status": false,
                "gts_permission_description": null
            }
        ]
    }
]






=======
• Why should i use it

• Deployment

• Automated Deployment

• Installation

• Usage

• Security Perspective

Basic Overview
• Hier is the Trainer module, we uses Spring framework, Spring Boot, Spring Security, Mysql, Hibernate ,JPA and Postman.

What should i use
• JDK 1.8

• Spring Tool Suit 4

• MySQL 8.0 https://www.youtube.com/watch?v=GIRcpjg-3Eg

• Postman 7

Install MySQL and create tables
 {
create table gts_users(
                      gts_user_id bigint NOT NULL AUTO_INCREMENT,
                      gts_user_email varchar(256) NOT NULL, 
                      gts_user_password varchar(256) NOT NULL, 
                      gts_user_first_name varchar(256) , 
                      gts_user_last_name varchar(256) ,
                      gts_user_mobile varchar(256) , 
                      gts_user_status varchar(256) , 
                      gts_user_login_tries int, 
                      gts_is_mobile_validated varchar(256), 
                      PRIMARY KEY(gts_user_id),
                      UNIQUE (gts_user_id) );

 }
create table gts_roles( gts_role_id bigint NOT NULL AUTO_INCREMENT, gts_role_name varchar(255) NOT NULL, gts_role_status bit, gts_role_description varchar(255), PRIMARY KEY (gts_role_id) );

create table gts_users_roles_map( gts_user_role_map_id bigint NOT NULL AUTO_INCREMENT, gts_user_id bigint , gts_role_id bigint , PRIMARY KEY (gts_user_role_map_id), FOREIGN KEY(gts_user_id) REFERENCES gts_users(gts_user_id), FOREIGN KEY(gts_role_id) REFERENCES gts_roles(gts_role_id) );

create table gts_permission( gts_permission_id bigint NOT NULL AUTO_INCREMENT, gts_permission_name varchar(255) NOT NULL, gts_permission_status bit, gts_permission_description varchar(255), PRIMARY KEY (gts_permission_id) );

• id should be unique • email should be unique

Pull the Project from Github and run it

Go to Github https://github.com/kabitagorai2210/GTSProfileService and pull the project in the Spring Tool Suit.

In spring Tool Suit right click on the project name -> Maven -> Update project Then manven will fetch the requeired Jars from the centeral repository.

In the Project open application.properties -> change the credentials for database

Go to project name right click -> Run as -> Spring boot app

At this time MySQL should also be running so that we can insert data into Table through Psotman calls Postman calls

• Login through predefined users with ADMIN, USER roles • This application will create automatically 2 users and define one as ROLE_USER and one as ROLE_ADMIN • After successfull login we will get a Authorization Token, after that for any call we want to make we have to put that Authorization Token in the Header of Postman request then only that request will be executed.

Note: as this is predefined user with ROLE_ADMIN so i have give all the permission to it and it can call all the methods choose : PUT localhost:9999/users/login Request Login with ROLE_ADMIN
 {
  "gts_user_email" : "admin@gmail.com",
  "gts_user_password" : "1234"
 }
Response { "status_code": "200" }

Note: as this is predefined user with ROLE_USER so i have give all the permission to it and it can call all the methods this user can call only getUser with ID and update user methods. choose : PUT localhost:9999/users/login Request Login with ROLE_User { "gts_user_email" : "user@gmail.com", "gts_user_password" : "1234" }

Response { "status_code": "200" }

Request with ROLE_USER

Request
choose : POST localhost:9999/users

 {
   "gts_user_email" : "10@gmail.com",
   "gts_user_password" : "44",
   "gts_user_first_name" : "bunny",
   "gts_user_last_name" :"sss",
   "gts_user_mobile" : "123456",
   "gts_user_status" : "true",
   "gts_user_login_tries" :"0",
   "gts_is_mobile_validated" : "true"
}

Response
 {
   "success": "true",
   "message": "Account created Successfully",
   "status_code": "200"
} OR

 {
   "success": "false",
   "message": "Account does not created",
   "status_code": "500"
}

• Update User through Postman
• while updating id, email must be same we can not update them only name and password can be updated
choose : PUT localhost:9999/users/1 Request
{

   "gts_user_password" : "44",
   "gts_user_first_name" : "bunny",
   "gts_user_last_name" :"sss",
   "gts_user_mobile" : "123456",
   "gts_user_status" : "true",
   "gts_user_login_tries" :"0",
   "gts_is_mobile_validated" : "true"
}

Response
  {
    "success": "true",
    "message": "Account updated successfully",
    "status_code": "200"
  }
                       
   OR
   
  {
    "success": "false",
    "message": "Account Nr does not found",
    "status_code": "500"
}

• Retrive single user by Id through Postman
• choose : GET localhost:9999/users/1

Response
   {
    "gts_user_id": 1,
    "gts_user_email": "aaa@gmail.com",
    "gts_user_password": "$2a$10$Bu61K80JKo1oS3QghCF0nOwCG9kDkeIrQ.HbK.K16H/IgjDOd5vEq",
    "gts_user_first_name": "aaa",
    "gts_user_last_name": "AAA",
    "gts_user_mobile": "123456",
    "gts_user_status": true,
    "gts_user_login_tries": 1,
    "gts_is_mobile_validated": true
  }
  
  OR
  
  {
   "success": "false",
   "message": "Not found user with this User ID",
   "status_code": "500"
}

• Retrive all users through Postman
• At present i gave only authority for ROLE_ADMIN to retrive all the Users List, so must login with Admin account ) • choose : GET localhost:9999/users

Response
[
{
    "gts_user_id": 2,
    "gts_user_email": "admin@gmail.com",
    "gts_user_password": "$2a$10$j7SHcievYopzIjn0J8OwPu8G59wTRWwCUl6mVwS3HSbRF0wH3wpzS",
    "gts_user_first_name": "bbb",
    "gts_user_last_name": "BBB",
    "gts_user_mobile": "123456",
    "gts_user_status": true,
    "gts_user_login_tries": 0,
    "gts_is_mobile_validated": true
},
{
    "gts_user_id": 1,
    "gts_user_email": "user@gmail.com",
    "gts_user_password": "$2a$10$908futjv/QqDEeUCk5dx1e0mUI47blQHSFwg7.OEiHzHYVH9uZA7a",
    "gts_user_first_name": "aaa",
    "gts_user_last_name": "AAA",
    "gts_user_mobile": "123456",
    "gts_user_status": true,
    "gts_user_login_tries": 1,
    "gts_is_mobile_validated": true
}
]

OR

 {
   "success": "false",
   "message": "Not found user with this User ID",
   "status_code": "500"
}

• Delete single user by Id through Postman
• choose : DELETE localhost:9999/users/1

Response
{
  "success": "true",
  "message": "User successfully deleted",
  "status_code": "200"
}
OR

{
  "success": "false",
  "message": "User can not be deleted",
  "status_code": "500"
 }
Deployment:
�⁯• After testing successfully on localhost now we need to deploy this app and database on AWS.

• First create Database in AWS Relational Database Service with any name and note down the Endpoint for this database and port alloted, most probably 3306

• Endpoint is in this format

                    <name>.<id>.<region>.rds.amazonaws.com.
• Now access this database with mysql workbench or command prompt.

• With command prompt use this link

                   >mysql -h <name>.<id>.region.rds.amazonaws.com -P 3306 -u <username> -p
and hit enter after that enter your password which u have for your RDS database.

• Now you will be inside your RDS Database now create Database first inside RDS

                         mysql> create database <database name>;
                 
                         mysql> use <database name>
• create all tables which we have created on mysql locally using command-

                         create table <table name>
• Once we are done with creating all tables inside Database now we need to update application.properties file inside our project.

• Update datasource url first as instead of localhost use endpoint for our RDS Database

Eg. Localhost/GTSUserProfile where GTSUserProfile is database name, we use

                           <endpoint>:3306/GTSUserProfile
• Give your datasource username and password as your RDS account’s.

• Create a new file in project name it as Dockerfile which will be used when we will create docker image of project.

• Data to be entered in that file --

                FROM openjdk:8
                ADD target/app_02-0.0.1-SNAPSHOT.war app.jar
                ENTRYPOINT ["java","-jar","app.jar"]
• Now create .jar file of Maven clean and install project to create .jar file inside target folder.

• Now open Command prompt and cd inside your project folder, and create new network as >

                       docker network create GTS-Network
• Now build docker image as >

                          docker image build -t <name> .
• Check if image created >

                         docker images
• Now create docker container >

                        docker container run –network GTS-Network --name <container name> -p 8080:8080 -d <image name>
• At this tiem docker container should be created and running check >

                       docker container ls -a
• Check through postman if its up and running at localhost:8080

• If its working fine then Create a new repository in AWS Elastic Container Registry(ECR) and name it as your docker image name

• After creation copy the URI of this repository and install AWSCLI in your local machine

• Now come to to your command prompt and

                     aws configure
and give your credentials as in AWS key.

• Then >

                   aws ecr get-login-password --region ap-south-1 | docker login --username AWS --password-stdin <Repository URI>
• You will be logged in into this repository

• Now check with >

                           docker images
and copy image id of image which needs to be uploaded to ECR

• Now tag your docker image with AWS ECR >

                           docker tag <Image id>  <Repository URI>
• Now push this image into AWS ECR >

                              docker push <Repository URI>
• Now get into your AWS account and check ECR Repository you should have docker image inside that.

• Now Search for Elastic Container Service in AWS and create new cluster select EC2Linux + Networking in that give a name to your cluster select EC2 instance type, select keypair which you have created already select your VPC, subnets and Security groups then hit create.

• Once created come to Task definitions and create new task definition select EC2 and hit next step, enter task name then open add container enter your container image name here and ECR Repository URI which you will get in your ECR. In hard limit enter 512 and in post mapping enter 8080:8080 then hit add

• Create task definition now, select your cluster now and open it select tasks > run new task select EC2 select your task definition and cluster if not already selected, no of tasks =1, hit run task now check for last status if its running if not then refresh page wait for it to run.

• Once running successfully click on ECS Instances and click on EC2 instance link, there must be one instance running, copy Public DNS and paste it in your browser with :8080 added at last, check if its working, your RDS should be available if not go and start that first before running any task.

• Check through postman as :8080/users/login check for all methods lie POST, PUT DELETE ,GET

4- Automated Deployment:

• We will use github actions for automation of deployment process, which will be triggered from commit into repository.

• Login into Github repository which needs to be deployed, hit actions then new workflow select deploy to Amazon ECS, setup this workflow.

• Aws.yml will be opened edit on: as on which action we would like to trigger the deployment process eg- push, pull etc for this project we will select push for branches update master

                      push:
                             branches:
                                    - master
• Update jdk version which is used for project

                                 - name: Set up JDK 1.8
• Update fields like to validate credentials with AWS, save AWS Access key ID and Secret Access Key into Settings>Secrets>New> save both there.

                          uses: aws-actions/configure-aws-credentials@v1
                     with:
                          aws-access-key-id: ${{ secrets.AWS_ACCESS_KEY_ID }}
                          aws-secret-access-key: ${{ secrets.AWS_SECRET_ACCESS_KEY }}
                          aws-session-token: ${{ secrets.AWS_SESSION_TOKEN }}
                          aws-region: ap-south-1
• Under id:Build-image update processes to clean build and push docker image into our ECR repository for deploying

                                 ECR_REGISTRY: ${{ steps.login-ecr.outputs.registry }}
                                            ECR_REPOSITORY: gts-rds007
                                            IMAGE_TAG: ${{ github.sha }}
                               docker build -t $ECR_REGISTRY/$ECR_REPOSITORY:$IMAGE_TAG
                                  docker push $ECR_REGISTRY/$ECR_REPOSITORY:$IMAGE_TAG
                       echo "::set-output name=image::$ECR_REGISTRY/$ECR_REPOSITORY:$IMAGE_TAG"
• Under ID: render-web-container update required fields task definition will be taken from task-definition.json update that,

                                  uses: aws-actions/amazon-ecs-render-task-definition@v1
                                         task-definition: task-definition.json
                                             container-name: gts-rds007
                                       image: ${{steps.build-image.outputs.image}}
• Under name deployment to amazon ECS update service name, cluster name which will be used for deploying process

                           task-definition: ${{steps.render-web-container.outputs.task-definition}}
                                                 service: gts-rds007
                                               cluster: cluster-gts-user         
• Clusters, Service,Containers, ECR repository with same name as we used here shoud aleready be there in AWS as we have created in deployment process in last process.

• For task-definition .json login into your aws account console from left side pane select task definitions hit on your already created task definition hit on json and copy complete script.

• Create new json file and paste this data into that file.

• Now push this file into your Github repository.
>>>>>>> refs/heads/Local-1

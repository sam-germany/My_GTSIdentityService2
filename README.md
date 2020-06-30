
Table of Contents

• Basic Overview

• Why should i use it

• Deployment

• Installation

• Usage

• Security Perspective


1.	Basic Overview

• Hier is the Trainer module, we uses Spring framework, Spring Boot, Spring Security, Mysql, Hibernate ,JPA and Postman.

2.	What should i use

•   JDK 1.8

•   Spring Tool Suit 4    

•   MySQL 8.0         https://www.youtube.com/watch?v=GIRcpjg-3Eg

•   Postman 7



Install MySQL and create tables 

 
create table gts_users(
               gts_user_id                bigint NOT NULL AUTO_INCREMENT,
               gts_user_email             varchar(256)  NOT NULL,
               gts_user_password          varchar(256)  NOT NULL,
               gts_user_first_name        varchar(256) ,
               gts_user_last_name         varchar(256) ,
               gts_user_mobile            varchar(256) ,
               gts_user_status            varchar(256) ,
               gts_user_login_tries       int,
               gts_is_mobile_validated    varchar(256),
               PRIMARY KEY(gts_user_id),
               UNIQUE (gts_user_id)
                );
                     
create table gts_roles(
               gts_role_id                        bigint NOT NULL AUTO_INCREMENT,
               gts_role_name                      varchar(255) NOT NULL,
               gts_role_status                    bit,
               gts_role_description               varchar(255),
               PRIMARY KEY (gts_role_id)
                );
                
create table gts_users_roles_map(
               gts_user_role_map_id              bigint NOT NULL AUTO_INCREMENT,
               gts_user_id                       bigint ,
               gts_role_id                       bigint ,
               PRIMARY KEY (gts_user_role_map_id),
               FOREIGN KEY(gts_user_id) REFERENCES gts_users(gts_user_id),
               FOREIGN KEY(gts_role_id) REFERENCES gts_roles(gts_role_id)
                );                
                
create table gts_permission(
               gts_permission_id                  bigint NOT NULL AUTO_INCREMENT,
               gts_permission_name                varchar(255) NOT NULL,
               gts_permission_status              bit,
               gts_permission_description         varchar(255),
               PRIMARY KEY (gts_permission_id)
                );                
                
create table gts_roles_permissions_map(
              gts_role_permission_id             bigint NOT NULL AUTO_INCREMENT,
              gts_role_id                        bigint ,
              gts_permission_id                  bigint,
              PRIMARY KEY(gts_role_permission_id),
              FOREIGN KEY(gts_role_id) REFERENCES gts_roles(gts_role_id),
              FOREIGN KEY(gts_permission_id) REFERENCES gts_permission(gts_permission_id )
                );                
                
                
                

• id should be unique • email should be unique

Pull the Project from Github and run it 

Go to Github https://github.com/kabitagorai2210/GTSProfileService and pull the project in the Spring Tool Suit. 

In spring Tool Suit right click on the project name -> Maven -> Update project Then manven will fetch the requeired Jars from the centeral repository. 

 In the Project open application.properties -> change the credentials for database

 Go to project name right click -> Run as -> Spring boot app

At this time MySQL should also be running so that we can insert data into Table through Psotman calls
Postman calls

• Login through predefined users with  ADMIN, USER roles
• This application will create automatically 2 users and define one as ROLE_USER and one as  ROLE_ADMIN
• After successfull login we will get a Authorization Token, after that for any call we want to make we have to put
  that Authorization Token in the Header of Postman request then only that request will be executed.
  

Note: as this is predefined user with ROLE_ADMIN so i have give all the permission to it and it can call all the methods
 choose :   PUT   localhost:9999/users/login
 Request  Login with ROLE_ADMIN 
--------
     {
      "gts_user_email" : "admin@gmail.com",
      "gts_user_password" : "1234"
     }

  Response
          {
          "status_code": "200"
          }
          



    
Note: as this is predefined user with ROLE_USER so i have give all the permission to it and it can call all the methods
      this user can call only  getUser with ID  and update user methods. 
 choose :   PUT   localhost:9999/users/login
 Request  Login with ROLE_User 
     {
      "gts_user_email" : "user@gmail.com",
      "gts_user_password" : "1234"
     }

  Response
          {
          "status_code": "200"
          }
     

 Request  with ROLE_USER
  
--------










Request
--------
 choose :   POST   localhost:9999/users         


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
--------         
     {
       "success": "true",
       "message": "Account created Successfully",
       "status_code": "200"
}
     OR
     
     
     {
       "success": "false",
       "message": "Account does not created",
       "status_code": "500"
}
       
       
       
• Update User through Postman    
• while updating id, email must be same we can not update them only name and password can be updated    
choose :   PUT   localhost:9999/1
Request
--------
         
      {
        "user_id": 1,
        "email": "user@hotmail.de",
        "password" : "bbbbb",
        "name" : "nnnn",
        "lastname" :"sss",
        "mobile" : "123456",
        "isactive" : "true"
                            }
         
 
Response
--------        
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
         
• Retrive single user by Id  through Postman          
• choose : GET       localhost:9999/users/1      

Response
--------          
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
• At present i gave only authority for ROLE_ADMIN to retrive all the Users List, so must login with Admin account )
• choose : GET      localhost:9999/users      
  
Response
--------            
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


• Delete single user by Id  through Postman     
• choose : DELETE    localhost:9999/users/1  

Response
--------     
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
         

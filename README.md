# Step 1
Create a MySQL database.
Create a user and grant all privileges to him.

Terminal:
- create database db_name;
- use db_name;
- create user 'username'@'localhost' identified by 'password';
- grant all privileges on db_name.* to 'username'@'localhost';
- flush privileges;
- exit;

Open application.yaml and replace your credentials (datasource).
Start Spring Boot Application.



# Step 2
Create your first table using POST http://localhost:8181/api/v1.0/schema/tables

Sample request payload:
```json
{  
   "name":"employee",
   "columns": [
      {
         "name":"employee_id",
         "type":"LONG",
         "primaryKey": true
        
      },
      {  
         "name":"full_name",
         "type":"STRING"
          
      },
      {  
         "name":"job_title",
         "type":"STRING"
      },
      {  
         "name":"username",
         "type":"STRING"
      },
      {  
         "name":"serial_number",
         "type":"LONG"
      },
      {  
         "name":"age",
         "type":"INTEGER"
      },
       {  
         "name":"salary",
         "type":"DOUBLE"
      },
      {
      	 "name":"is_higly_skilled",
         "type":"BOOLEAN"
      }
   ]
}
```

Expected Response : HTTP/1.1 200 OK 

# Step 3
- Open a database GUI tool.
- Connect to your database and validate the table's structure using <describe emplyee;>. Column lengths and default values of each data type are predetermined in ColumnType.java.


# Other Services:
- Get info (Describe) of the persisted table using GET http://localhost:8181/api/v1.0/schema/tables/{table_name}.

- Drop the table using DELETE http://localhost:8181/api/v1.0/schema/tables/{table_name}.

- Show all tables using GET http://localhost:8181/api/v1.0/schema/tables.
  Contains info of the data structure for each created table. 









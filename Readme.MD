REQUIREMENTS:

To install Eclipse, you can follow these steps:
Firstly, visit the official website of Eclipse (https://www.eclipse.org/downloads/)and download the installer of Eclipse. Next, you need to install Java 18 on your system. Visit the official website of Oracle (https://www.oracle.com/java/technologies/javase-jdk18-downloads.html) and follow the instructions to download and install the correct version of Java 18 according to the system. After Java 18 is installed, you can run the installer of Eclipse that was downloaded in step 1 and follow instructions to finish the installation process. To make sure that Eclipse software installation in the system is correct, you can open Eclipse and create fresh and new projects. In New Project, choose the Java version category and select the project type. Mention location, project name and click Done. Eclipse creates a new project and opens it up in editor.  Additionally, if you need to use the MySQL database for the backend, you will need to download and install it separately, and develop a database accordingly. Finally, you can download the "Insurance" program and open it up in Eclipse, and this confirms that the project is downloaded in the correct format. You can run the program by clicking the Run button.

SQL:

Firstly, visit the official website of MySQL (https://www.mysql.com/downloads/) and download the appropriate version of MySQL based on system. Then, follow the instructions provided on the website to finfish the installation. After MySQL installation is done, you can open the MySQL CLI by typing the "MySQL" command in a terminal. In the MySQL CLI, you can create a new DB by using the following command:
CREATE DATABASE database_name;
Replace "database_name" with the desired name of your database. After creating the database, you can create tables and populate them with data by writing SQL queries. You can also use various tools and GUIs available for MySQL to manage your databases more efficiently.

Code:

Create DB:

CREATE database ood_project;

Create Tables:

USER REGISTRATION:

CREATE TABLE user_registration (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  pass VARCHAR(255) NOT NULL
);

INSURANCE PLAN:

CREATE TABLE insurance_plan (id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    ins_type VARCHAR(255) NOT NULL,
    ins_name VARCHAR(255) NOT NULL,
    premium DECIMAL(10,2) NOT NULL,
    duration INT,
    agent VARCHAR(255) NOT NULL
);


In DB file, replace the connection string with your system’s MySQL connection string i.e 
Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ood_project?allowPublicKeyRetrieval=true&useSSL=false","root","Mysql@1");

// Databricks notebook source
// MAGIC %run ./setup

// COMMAND ----------

print(path + "temp/")

// COMMAND ----------


val config = Config(Map(
  "url"            -> "luishackdbserver.database.windows.net",
  "databaseName"   -> "hackdb",
  "dbTable"        -> "user_t",
  "user"           -> "hackadmin",
  "password"       -> "Hack@2019",
  "connectTimeout" -> "5", //seconds
  "queryTimeout"   -> "5"  //seconds
))

val collection = spark.read.sqlDB(config)
collection.show()

// COMMAND ----------

display(collection.filter($"username" === "abhinav@gmail.com"))

// COMMAND ----------

Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")

// COMMAND ----------

val jdbcHostname = "luishackdbserver.database.windows.net"
val jdbcPort = 1433
val jdbcDatabase = "hackdb"
val jdbcUsername = "hackadmin"
val jdbcPassword = "Hack@2019"

// Create the JDBC URL without passing in the user and password parameters.
val jdbcUrl = s"jdbc:sqlserver://${jdbcHostname}:${jdbcPort};database=${jdbcDatabase}"

// Create a Properties() object to hold the parameters.
import java.util.Properties
val connectionProperties = new Properties()

connectionProperties.put("user", s"${jdbcUsername}")
connectionProperties.put("password", s"${jdbcPassword}")

// COMMAND ----------

val driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
connectionProperties.setProperty("Driver", driverClass)

// COMMAND ----------

val employees_table = spark.read.jdbc(jdbcUrl, "user_t", connectionProperties)
employees_table.createOrReplaceTempView("user_t")


// COMMAND ----------

// MAGIC %sql
// MAGIC 
// MAGIC select * from user_t;

// COMMAND ----------

// MAGIC %sql
// MAGIC 
// MAGIC drop table user_t; 

// COMMAND ----------

// MAGIC %sql
// MAGIC CREATE TABLE user_t
// MAGIC   (username String, password String, household_count INT, created_date timestamp, last_login timestamp)
// MAGIC   USING PARQUET --PARTITIONED BY (username)
// MAGIC   OPTIONS ('compression'='snappy');

// COMMAND ----------

// MAGIC %sql  
// MAGIC INSERT OVERWRITE table user_t 
// MAGIC   select "r1" as username, "r12" as password, 10 as household_count, null , null;

// COMMAND ----------

// MAGIC %sql  
// MAGIC select * from user_t;

// COMMAND ----------

spark.table("user_t")
     .write
     .mode(SaveMode.Append) // <--- Append to the existing table
     .jdbc(jdbcUrl, "user_t", connectionProperties)
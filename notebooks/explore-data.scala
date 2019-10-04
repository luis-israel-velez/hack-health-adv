// Databricks notebook source
// MAGIC %run ./setup

// COMMAND ----------

val storeDF = spark.read.format("delta").load(path+"curated/storeTransactions")


display(storeDF.groupBy("trade_item_cd").agg(count("*").alias("transactions")).orderBy("trade_item_cd"))


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

// MAGIC %sql
// MAGIC 
// MAGIC drop table order_trade_item_t; 

// COMMAND ----------

// MAGIC %sql
// MAGIC CREATE TABLE order_trade_item_t
// MAGIC   USING DELTA
// MAGIC   LOCATION 'wasbs://lv-health-adv-store@lvhealthadvstrg.blob.core.windows.net/curated/storeTransactions'

// COMMAND ----------

// MAGIC %sql
// MAGIC select * from order_trade_item_t;

// COMMAND ----------

spark.table("order_trade_item_t")
     .write
     .mode(SaveMode.Append) // <--- Append to the existing table
     .jdbc(jdbcUrl, "order_trade_item_t", connectionProperties)
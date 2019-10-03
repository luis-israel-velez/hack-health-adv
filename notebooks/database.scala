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
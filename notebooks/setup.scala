// Databricks notebook source
//Lib if needed
import org.apache.spark.sql.types.{StructType, StructField, LongType, StringType, IntegerType, TimestampType}
import org.apache.spark.sql.functions._

// COMMAND ----------

//Global Settings

val path = "wasbs://lv-health-adv-store@lvhealthadvstrg.blob.core.windows.net/"
val storageAccount = "lvhealthadvstrg" //TODO: Security
val storageKey = dbutils.secrets.get(scope = "health-adv-scope", key = "strg-key")//dbutils.secrets.listScopes


// COMMAND ----------

//"fs.azure.account.key.<storage-account-name>.blob.core.windows.net",
//"<storage-account-access-key>")
spark.conf.set(
  "fs.azure.account.key." + storageAccount + ".blob.core.windows.net",
  storageKey)
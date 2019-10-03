// Databricks notebook source
// MAGIC %run ./setup

// COMMAND ----------

val storeDF = spark.read.format("delta").load(path+"curated/storeTransactions")


display(storeDF.filter($"country" === "US").groupBy("brand").agg(count("*").alias("transactions")).orderBy("brand"))


// COMMAND ----------

display(dbutils.fs.ls(path+"landing/lv-iot-streaming/01/" + year + month  + day))
// Databricks notebook source
// MAGIC %run ./setup

// COMMAND ----------

val storeDF = spark.read.format("delta").load(path+"curated/storeTransactions")


display(storeDF.filter($"country" === "US").groupBy("brand").agg(count("*").alias("transactions")).orderBy("brand"))

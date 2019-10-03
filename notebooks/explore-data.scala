// Databricks notebook source
// MAGIC %run ./setup

// COMMAND ----------

val storeDF = spark.read.format("delta").load(path+"curated/storeTransactions")


display(storeDF.filter($"country" === "US").groupBy("brand").agg(count("*").alias("transactions")).orderBy("brand"))


// COMMAND ----------

display(dbutils.fs.ls(path+"landing/lv-iot-streaming/01/" + year + month  + day))

// COMMAND ----------

val date = java.time.LocalDate.now.toString()
val year = date.split("-")(0) + "/"
val month = date.split("-")(1) + "/"
val day = date.split("-")(2) + "/"

println(path+"landing/lv-iot-streaming/01/" + year + month  + day)

val df = spark
  .read.format("avro")
  .option("infereSchema", true)
  .load(path+"landing/lv-iot-streaming/01/" + year + month  + day + "*")

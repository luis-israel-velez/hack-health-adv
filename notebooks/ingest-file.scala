// Databricks notebook source
// MAGIC %run ./setup

// COMMAND ----------

val date = java.time.LocalDate.now.toString()
val year = date.split("-")(0) + "/"
val month = date.split("-")(1) + "/"
val day = date.split("-")(2) + "/"

println(path+"landing/lv-iot-streaming/01/" + year + month  + day)

val df = spark
  .read.format("avro")
  .option("infereSchema", true)
  .load(path+"landing/hack-eastus2-iothealthadv/00/"+ year + month  + "*/*") //TODO: Parametes? (We can add day)


// COMMAND ----------

val impressionSchema = new StructType()
    .add("EntityType", StringType, true)
    .add("impressionDate", StringType, true)
    .add("advertID", StringType, true)
    .add("impressionURL", StringType, true)
    .add("sessionID", StringType, true)
    .add("firstName", StringType, true)
    .add("lastName", StringType, true)


val impressionsDF = df
  .select($"body".cast("string").alias("json"))
  .withColumn("data", from_json(col("json"), impressionSchema))
  .filter($"data.EntityType" === "Impressions")
  .select("data.*")


// COMMAND ----------


val storeSchema = new StructType()
    .add("EntityType", StringType, true)
    .add("transactionDate", StringType, true)
    .add("storeID", StringType, true)
    .add("laneID", StringType, true)
    .add("customerName", StringType, true)
    .add("brand", StringType, true)
    .add("country", StringType, true)


val storeDF = df
  .select($"body".cast("string").alias("json"))
  .withColumn("data", from_json(col("json"), storeSchema))
  .filter($"data.EntityType" === "Store-Transaction")
  .select("data.*")

display(storeDF)


// COMMAND ----------

storeDF.write.format("delta").save(path+"curated/storeTransactions")
Kafka-enabled Event Hubs on Azure
---

## Overview

A general introduction to Event Hubs:

https://docs.microsoft.com/en-us/azure/event-hubs/event-hubs-about

An overview of Event Hubs for Apache Kafka:

https://docs.microsoft.com/en-us/azure/event-hubs/event-hubs-for-kafka-ecosystem-overview

## Create a Kafka-enabled Event Hubs namespace
How to create Kafka-enabled namespaces:

https://docs.microsoft.com/en-us/azure/event-hubs/event-hubs-create

## Create Kafka-enabled Event Hubs
Once a namespace is created, you can create Kafka-enabled Event Hubs in the namespace:

https://docs.microsoft.com/en-us/azure/event-hubs/event-hubs-create-kafka-enabled

# Configuration 
The following information is needed when configuring a Dispatcher instance.

## The Fully Qualified Domain Name (FQDN) of the Namespace
The FQND of a namespace is: 
`<name>.servicebus.windows.net`

## How to get the Connection String for a Namespace
Once a namespace is created, you can obtain the connection string required to communicate with Event Hubs:

https://docs.microsoft.com/en-us/azure/event-hubs/event-hubs-get-connection-string

The connection string should have the following format:
  `Endpoint=sb://<FQDN>/;SharedAccessKeyName=<KeyName>;SharedAccessKey=<KeyValue>`

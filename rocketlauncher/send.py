#!/usr/bin/env python
import pika
import json
import math
import time


# read hostname from config file
cfg_file = open('host.cfg')
for line in cfg_file:
    fields = line.strip().split()
HOST = fields[2]

QUEUE_NAME = 'rocket_launcher'


def send_message(message):
    connection = pika.BlockingConnection(pika.ConnectionParameters(HOST))
    channel = connection.channel()
    channel.queue_declare(queue=QUEUE_NAME)
    channel.basic_publish(exchange='', routing_key=QUEUE_NAME, body=message)
    print(" [x] Sent " + message)
    connection.close()


def send_trigger_message(distance):
    data = {"trigger": True, "distance": distance, "time": int(math.floor(time.time()))}
    send_message(json.dumps(data))

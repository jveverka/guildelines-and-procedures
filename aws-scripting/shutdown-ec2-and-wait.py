#!/usr/bin/env python3

import boto3
import time

client = boto3.client('ec2')
instanceId = "i-09b0bff88d319b1e1"

client.stop_instances(InstanceIds=[instanceId])

print ('Stopping instance ', instanceId)
running = True

while running:
    response = client.describe_instances()
    reservations = response['Reservations']
    print ('waiting for instance shutdown ...')
    for reservation in reservations:
        instances = reservation['Instances']
        for instance in instances:
            print(instance['InstanceId'], instance['State']['Name'])
            if instance['InstanceId'] == instanceId and instance['State']['Name'] == 'stopped':
                running = False
    if running:
        time.sleep(3)

print('Instance: ', instanceId, ' was stopped')

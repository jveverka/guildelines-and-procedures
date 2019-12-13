#!/usr/bin/env python3

import boto3

client = boto3.client('ec2')
response = client.describe_instances()

reservations = response['Reservations']

for reservation in reservations:
    instances = reservation['Instances']
    for instance in instances:
        print(instance['InstanceId'], instance['State']['Name'])
        

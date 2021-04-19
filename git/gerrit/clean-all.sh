#!/usr/bin/python3

import yaml
import subprocess

with open("projects.yaml", 'r') as stream:
    data_loaded = yaml.load(stream)

for project in data_loaded['projects']:
    if project.find("*") >= 0:
       print("skipping: "+ project)
    else:
       print("deleting project: " + project)  
       subprocess.run(["rm", "-rf", project])


#!/usr/bin/python3

import yaml
import subprocess

with open("projects.yaml", 'r') as stream:
    data_loaded = yaml.load(stream)

for project in data_loaded['projects']:
    print("updating project: " + project)	
    subprocess.run(["git", "-C", project, "pull"])


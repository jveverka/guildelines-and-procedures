#!/usr/bin/python3

import yaml
import subprocess

with open("projects.yaml", 'r') as stream:
    data_loaded = yaml.load(stream)

base_url = data_loaded['base-url']
print("base-url: " + base_url)

for project in data_loaded['projects']:
    print("cloning project: " + project)	
    subprocess.run(["git", "clone", base_url + project])


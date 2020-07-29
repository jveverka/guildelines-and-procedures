# Simple Python REST testing  example
This example demonstrates how to simple implement integrations tests in python using 
[python3 http client](https://docs.python.org/3/library/http.client.html) and [pytest](https://docs.pytest.org/en/stable/). 

## Install dependencies
```
sudo apt install python3 pip3
pip3 intstall U pytest Flask
```

## Run tests
1. start rest-server
   ```
   ./rest-server
   ```
2. run integrations tests
   ```
   pytest
   ```

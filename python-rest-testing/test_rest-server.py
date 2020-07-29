
import pytest
from http import client

http_connection = client.HTTPConnection('localhost', 8080)

def test_get_system_info():
    http_connection.request("GET", "/system/info")
    response = http_connection.getresponse()
    assert response.status ==  200

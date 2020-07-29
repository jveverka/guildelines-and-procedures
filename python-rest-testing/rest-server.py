#!/usr/bin/env python3

from flask import Flask, request, jsonify

app = Flask(__name__)

@app.route('/system/info', methods=["GET"])
def getVersion():
    version = { "id": "001", "type": "rest-sever", "version": "1.0.0", "name": "the-name" }
    return jsonify(version)

if __name__ == '__main__':
    try:
        print('rest-server init.')
        app.run(debug=False, host="0.0.0.0", port=8080)
    except SystemExit:
        camera.stop_preview()
        print('rest-server shutdown.')
        pass

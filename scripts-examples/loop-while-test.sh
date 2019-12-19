#!/bin/bash

echo "while loop started"

index=1
while [ ${index} -le 10 ]; do
     echo "iteration: ${index}"
    ((index++))
done

echo "done."

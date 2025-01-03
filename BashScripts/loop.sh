#!/bin/bash
count=1
while [ $count -le 5 ]; do
	cat test.txt
	count=$((count+1))
done

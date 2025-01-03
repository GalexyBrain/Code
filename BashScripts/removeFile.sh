#!/bin/bash

echo "Enter file name : "
read fname
if [ -e $fname ]; then
	echo "File exists do you want to remove it (y/n) : "
	read ch
	if [ $ch == "y" ]; then
		sudo rm "$fname"
	fi
else
	echo "File does not exist do you want to create it (y/n) : "
	read ch
	if [ $ch == "y" ]; then
		sudo touch "$fname"
	fi
fi


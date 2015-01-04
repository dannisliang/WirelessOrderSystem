WirelessOrderSystem
===================

A Wireless oreder system using in restaurants

A wireless order system for waiters. Functions are alike to those on a
PDA. However, using smart phones instead of PDA saves the money of
purchasing new devices. Restaurants can just set up a server and install
Android app on waiters' smart phones.

This wireless order system has the following functions:

1.Take the guests' order.

2.Merge two tables into one, combing their orders.

3.Transfer order from its table to a new one.

3.View the status of tables.

4.Check the bill.

Network communication uses Apache Http. Web server(Tomacat) uses
Servlet.  Android client and server use SQLite and MySQL to store
information, sepreately.
To synchornize the databse of the server end and the client, we also
build up the refresh function.

Descriptions of packages in this app:

com.amaker.provider  Visit local SQLite
com.amaker.util      Tools for database
com.amaker.wlo       Activies

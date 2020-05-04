# GymondoApp



I tried doing as much as possible but I had a lot of work and the server was down :

<!DOCTYPE HTML PUBLIC "-//IETF//DTD HTML 2.0//EN">
<html>

<head>
	<title>503 Service Temporarily Unavailable</title>
</head>

<body>
	<h1>Service Temporarily Unavailable</h1>
	<p>The server is temporarily unable to service your
		request due to maintenance downtime or capacity
		problems. Please try again later.</p>
	<hr>
	<address>Apache/2.2.15 (CentOS) Server at wger.de Port 443</address>
</body>

</html>

On how to solve the other problems:


On searching , I supose is just a filtering using a searchview and overriding a QuerryTextChange.
On the filtering using body parts , I think an easy way is to do it is to modify the endpoint and filter by doing another request ,but that is not that practical.
On the loading UI , the request should return an Observable and while subscribing on it and porcessing the data , show a dialog.
For the API error , I supose is just when I dont get status 200 , I catch the error (while doing the fetching on the server) and show a dialog box or something


I'm sorry I couldn't do much but I would love a discussion.

# GymondoApp



I tried doing as much as possible in such a short time.

On how to solve the other problems:


On searching , I supose is just a filtering using a searchview and overriding a QuerryTextChange.
On the filtering using body parts , I think an easy way is to do it is to modify the endpoint and filter by doing another request ,but that is not that practical.
On the loading UI , the request should return an Observable and while subscribing on it and porcessing the data , show a dialog.
For the API error , I supose is just when I dont get status 200 , I catch the error (while doing the fetching on the server) and show a dialog box or something


I'm sorry I couldn't do much but I would love a discussion.

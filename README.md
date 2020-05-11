# GymondoApp



I tried doing as much as possible but I had a lot of work and I got stuck in several places :
I couldn't get the scroll going , I sincerly don't know why.
For the search I tried both SearchView and EditText but couldn't make it work , and it's probably because I used obeservables for the retrofit calls and because of that I couldn't keep the list of exercises that go in and filter them afterwards. I probably should do a normal Call return for retrofit and that would make everything so much easier (And to the callback in an AsyncTask)



On how to solve the other problems:

For the equipment,muscles,etc I should put them all in MutableLists and bundle them to Deatiled fragment and just get by id which is which.
On the filtering using body parts , I think an easy way is to do. It is the same as filtering by name , but I do it by body part.
For the API error , I supose is just when I dont get status 200 , I catch the error (while doing the fetching on the server) and show a dialog box or something

I thing that I forgot to correct and I don't want to do it know because the time is up is the fact that the dimensions used in the app (margins , paddings,text sizes , etc) should be in a dimen folder , I forgot to put them there and now it is too late.


I'm sorry I couldn't do too much but I would love a technical discussion. I really want to know what was not alright in some parts of the app.

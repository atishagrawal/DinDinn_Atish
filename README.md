# DinDinn Mini Assignment

Mini assignment

Please replicate the UI as shown in the attached document, and send us your completed assignment using GitHub.

## Requirements:
 - You do not need to use exact images, so please use other suitable images.
 - Write a networking layer to call the API and fetch data which you have to mock the data to display on the UI.
 - Use the following dependencies/libraries to implement: Rxjava and Kotlin

# Problem Description

Suppose we have 2 kinds of apps, merchant app and customer app. When the customer makes an order, the order will be received on the merchant app as a new order. The api response is like this (just for example, you can modify the API if you feel you have to. The
most required fields are created_at, alerted_at, expired_time, and id)


There are 3 major scenarios involved here which are: “Loaded for the first time” and “accept the order”.
## First Scenario: Load Data
Let's say we received new orders from our api, we got two orders here. The first order expiration time is at “2021-06-10T15:05+00Z” (universal time), and we need to create a progress bar that will be reduced every minute (countdown progress bar). When the time has reached the given specific alerted_at, then we need to play a default system ringtone (you can use an external sound file also).
So if the response contains 2 order items, that means we are going to have 2 counter threads (to animate each item asynchronously).
You are asked to design the asynchronous rendering for every single item in our list.
## Second Scenario : Accept the Order
We have received 2 orders, and we have them rendered on our screen. The timers are on now. When the timer is passing the expired time, then the view will be changed, and there is no “accept” button anymore (change the order item view to just show the “Expired” button).
When the expired button is pressed, the item will be removed from the list. But when the user presses the “accept” button before passing its expiration time, then the item will be removed from the list. The sound and the timer which related to this item has to be stopped.
You need to implement that feature with the most efficient thread management strategy.
## Third Scenario : Go to the ingredient screen, and back to the order screen
We need to create the ingredient screen, which displays a list of ingredients. The data will be loaded from the API (you can create your own JSON payload). There is a search bar on the top of the ingredient screen. It takes text input and will call a search API. The response will be rendered on the ingredient list as the search result.
As you can see from the design, the order screen could have many tabs, based on how many menu categories we have. Every tab will call the same API (for example /ingredient_by_category?category_id=5)
When the user is back from ingredient to order screen, make sure the timer thread will keep running as long as the current time does not exceed the expiration time.

# The problems to be solved here are:
  - How you design the logic so that we could have multiple tabs based on the api response
  - How you update the screen after searching for the ingredient.
  - How you maintain the thread state when you back to the order screen
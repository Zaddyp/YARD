Original App Design Project - README Template
===

# YARD

## Table of Contents
1. [Overview](#Overview)
3. [Product Spec](#Product-Spec)
4. [Wireframes](#Wireframes)
5. [Schema](#Schema)

## Overview
### Description
YARD is a social media application that is used to allow students attending Historically Black Colleges and Universities (HBCUs) always connected, and create awareness them.

### App Evaluation
[Evaluation of your app across the following attributes]
- **Category:** - social media 
- **Mobile:** - Mobile is view only and uses camera
- **Story:** - Allows users to share their lives in pictures and enhance their content 
- **Market:** - The targeted audience is students attending a HBCU.  
- **Habit:** - Users can post throughout the day many times. Users can explore endless pictures in any category imaginable whenever they want. Very habbit forming!
- **Scope:** - Starting out narrow focused to school by school based on Geography before expanding to every HBCU student across the country


## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* [x] Log in and Sign up - users can log in and sign up
    * verifying user emails belong to an HBCU
* [x] Bio - users can view their profile and personal posts
* [x] Home - user can see feeds 
    * user can post 
* [x] Compose
    * users can post photos,and texts
* [x] Gestures - users can swipe left or right to delete picture they wanted to post
* [x] Animation - animated progress bar
* [x] SDKs - Google Maps SDK to get the location
* [x] Visual Polish from external libraries - Lottie

**Optional Nice-to-have Stories**
* [x] Delete Account - Privacy 
   * I had to get the current user, query the posts for the current user, created a list of objects representing the posts, then deleted them individualy. NB: Not the most efficient solution. I plan on making it more effective by using an API that can bulk delete all posts assigned to a user id.
* [x] beautifying the app for better UI/UX
* [x] log out 
* [x] Google fonts for app font
* [x] Users can like a post
* [x] Users can see more about a particular user by clicking the person's username 
* [x] Users can change profile pictures through uploading or taking pictures
* Events - users can see whats happening like trends etc 
* Search - users can search for other users
* Refresh to see new posts
* search with different flters like based on school, majors etc
* log in with facebook option
* chat
* stories

## DIFFICULTIES
- Verifying the user attends an HBCU after signing up. Users cant log in until the school email has been verified that it belongs to an HBCU. I started with HBCUs in some specific states like Delaware, Tennessee, and Virginia.
   * solution: I got the formats of those HBCU emails and immediately tells the user that he cant sign up if his email doesnt match the format of the HBCU emails i fetched from the school's website. If it matches, my algorithm then sends a verification link to the email and the user cant log in until he/she verifies. So ther user cant fake an email address. The user details doesnt also take space in the database. That is, until the user has been verified before the information is stored in the database
- Background thread and pagination:
   * solution - for background thread, i used a calculated loading bar to show the posts are loading and at most before the loading bar finishes the posts would have queried. I did so because background thread makes my app way faster since it isnt running on the UI thread and the user wont be seeing any blank screen. I created a handler thread and used a handler to run it in the background while the loading bar is loading.
   * For pagination, i used "set skip" on the query to continue adding 15 posts and then when the user scrolls to the end, it loads and fetches another 15 posts



### 2. Screen Archetypes

* Log in/ Sign up
   * For log in - username and password
   * For sign up - username, school, school email, classification, major, password
* Home
   * Feeds showing posts 

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Home
* Bio

**Flow Navigation** (Screen to Screen)
* [list first screen here] Log in 
   * Sign up if the person does not have an account 
   * if the person does - Home page
* [list second screen here] Home 
   * Home - where the feeds are 
   * Bio - profile, about, posts 


## Wireframes
[Add picture of your hand sketched wireframes in this section]
![](https://i.imgur.com/5G7EMNl.jpg)
<img src="YOUR_WIREFRAME_IMAGE_URL" width=600>

### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype

## Schema 
[This section will be completed in Unit 9]

![](https://i.imgur.com/QnmjMnm.jpg)

### Models
[Add table of models]
### Networking

* Home Feed Screen
   *(Read/GET) Query all posts where user is author
* (Create/POST) Create a new like on a post


* Create Post Screen
   * (Create/POST) Create a new post object
* Profile Screen


- [Add list of network requests by screen ]
- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]

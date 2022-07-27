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
    * verifying emails
* Bio - users can view their profile and personal posts
* Home - user can see feeds 
    * user can post 
* Compose
    * users can post photos,and texts
* Gestures - users can swipe left or right to delete picture they wanted to post
* Animation - animated progress bar
* SDKs - Google Maps SDK to get the location
* Visual Polish from external libraries - Lottie

**Optional Nice-to-have Stories**
* Delete Account - Privacy 
   * Not the most efficient solution. I plan on making it more effective by using an API that can bulk delete all posts assigned to a user id.
* beautifying the app for better UI/UX
* log out 
* Google fonts for app font
* Events - users can see whats happening like trends etc 
* Search - users can search for other users
* Refresh to see new posts
* search with different flters like based on school, majors etc
* log in with facebook option
* chat
* stories



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

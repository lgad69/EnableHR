# enableHR Coding Challenge

This project comprises two parts:

* The front end application - based on the [React Todo MVC Example](https://github.com/reactjs/redux/tree/master/examples/Todomvc).
* A back end spring-boot application which serves as the API for this example.

The application allows users to:

1. Create new to-do items
2. Edit existing todos (by double clicking)
3. Mark and un-mark a to-do as complete
4. Filter to-dos based on their status
5. Permanently remove (clear) completed to-dos

## Pre-requisites

* A git client (if you're on Windows, [Atlassian SourceTree](http://www.sourcetreeapp.com/) works well)
* A java editor or IDE of your choice (we use [IntelliJ Idea](http://www.jetbrains.com/idea/) but there's nothing here that relies on that)
* Java 1.8+ (it won't work with Java 7 - we use Java 8 syntax)
* Maven 3.8+ (it may work with earlier versions - but you may have issues running the front-end-maven-plugin)
* An internet connection (to download maven dependencies, etc - it's not required once you've done that)

## Getting started

Run `mvn install` to get everything downloaded and make sure you're good on that front.

Run `mvn test` and `mvn com.github.eirslett:frontend-maven-plugin:npm@npm-test` to run the back-end and front-end tests respectively.

Then, using multiple terminals/command windows:

1. Run `mvn spring-boot:run` to start the back end (spring-boot based) server.
   You can access it at [http://localhost:8080/todos](http://localhost:8080/todos) to make sure it's up.
   Alternatively, you can run the Main class from your IDE to support debugging, hotswap, etc as necessary.
2. Run `mvn com.github.eirslett:frontend-maven-plugin:npm@npm-start` to start the front end application.
   You can access it at [http://localhost:3000/](http://localhost:3000/) to make sure it's up.
   Once it's running, you are able to edit the static assets, and they will be recompiled and reloaded on the fly - you don't need to restart the front-end server.
   If you'd rather run node directly, you can go to the `src/main/frontend` folder and run `npm start` and `npm test` (and bypass maven completely).

## Database Console

If you want to query the database, you can access [http://localhost:8080/h2-console](http://localhost:8080/h2-console).
You can find the necessary connection details in src/main/resources/application.yml (the username/password will probably be todo/todo).

## API calls

The back end currently exposes the following endpoints (and uses an in-memory List of Maps representing JSON payloads)

| Path             | Method | Request                             | Response                                        | Description                                                                              |
|-----------------|---------|-------------------------------------|-------------------------------------------------|------------------------------------------------------------------------------------------|
| /todo           | POST    | `{text:String, completed: Boolean}` | `{id:Int, text:String, completed:Boolean}`      | Creates a new Todo                                                                       |
| /todo           | PUT     | `{id:Int, text:String}`             | `{id:Int, text:String, completed:Boolean}`      | Updates a Todo                                                                           |
| /todo           | DELETE  | `{id:Int}`                          | *none*                                          | Deletes a Todo                                                                           |
| /todo/complete  | POST    | `{id:Int}`                          | `{id:Int, text:String, completed:Boolean}`      | Completes a Todo                                                                         |
| /todos          | GET     | *none*                              | `[{id:Int, text:String, completed:Boolean},..]` | Lists all Todos                                                                          |
| /todos/clear    | POST    | *none*                              | `[{id:Int, text:String, completed:Boolean},..]` | Removes completed Todos                                                                  |
| /todos/complete | POST    | *none*                              | `[{id:Int, text:String, completed:Boolean},..]` | Marks all Todos as complete (if any are incomplete), or incomplete (if all are complete) | 

This API was written by a front-end developer as a proof of concept to make things work, but isn't particularly REST-ful and needs someone like you to improve it!

## Front End

If you'd rather run node / npm directly you can.
The project is set up to use node v14.17.6 and npm 7.22.0, but earlier versions should be ok.
Just go into the src/main/frontend directly and do everything from there.

## Kotlin

We are gradually moving our code-base across to [kotlin](https://kotlinlang.org/).
This project has a maven configuration supporting kotlin - but you don't have to use it (if you do want to go down that path, show us what you can do!).

To enable this configuration, you need to activate the kotlin profile.
This can be done on a per-build process by `mvn -Pkotlin <goals>`.
If you want to turn it on permanently, change the profile activation:

```xml
    <profile>
      <id>kotlin</id>
      <activation>
        <!--<activeByDefault>false</activeByDefault>-->
        <activeByDefault>true</activeByDefault>
      </activation>
      ...
    </profile>
```

The configuration is an either/or model (you can either use kotlin or java - but not both).
If you want to mix and match, see [Compiling Kotlin and Java sources](https://kotlinlang.org/docs/reference/using-maven.html#compiling-kotlin-and-java-sources) in the official documentation.

If you find that kotlin (or java) is getting in the way - feel free to delete the relevant files under `src/main/` and `src/test/`!

## Tasks

### 1. Persistence of Todos

Persist the tasks to the in-memory database, and accessible via the TodoRepository (rather than the quick and nasty `List<Map>` implementation that is currently there).

We expect this task to take at least 30 minutes.

### 2. Make the API more REST-ful

We'd like to expand the application to make the API more REST-ful so we can expose it to other applications.

To do this, you'll need to refactor the back end API to expose the appropriate paths and methods (verbs).
You'll also need to update the front end (under src/main/frontend) to submit requests to the appropriate paths.

We expect this task to take at least 1.5 hours.

### 3. Support for multiple Todo lists

Our Todo app has been wildly successful, and based on user demand we want to add the ability for users to maintain multiple lists of Todos (eg "bills to pay", "shopping list", "jobs around the house").

Your task is to build the back end support (from the database up to the controller) for such lists and extend the API so clients can use this functionality.

The API should support creation of new named lists, updating the names of those lists, and managing Todos within those lists.

You need to make sure that what you build continues to work with our existing front-end, as well as for any other consumers of the API that you built in the previous task.

That is, clients of the API should be able to do all of the necessary steps without knowing about lists, and without changing their implementation based on the previous step.

How you build that support is up to you.

We expect this task to take at least 2 hours.

## Assessment

We'll be looking at a number of aspects of your solution:

1. The quality of your code - whether it works as expected (and that is verifiable), maintains backwards compatability when required (task 3) and does what has been asked
2. Whether your solution is consistent with the existing code and components
3. How you design your solution to ensure that the API is intuitive and follows REST-ful principles
4. Whether the solution is well layered (both front end and back end) and maintains an appropriate separation of concerns between presentation, business-logic and data-access.
5. Your development process - we'll go to the point of looking at what you've done through individual commits - so try and keep your work moving in a structured way - don't just give us one big commit at the end (you wouldn't spend a week on a task and then commit it on Friday afternoon, would you?).
 
## Support

We're asking you to do this work in your own time, so it's only fair that we're available to answer any questions or queries that you have.
We're only too happy for you to contact us whenever you like - including over weekends or at night.

We're assessing your ability to work with Spring, Hibernate, JSON and to tweak an existing front-end.
We're not expecting you to be an expert in maven or npm.
If you do have trouble getting things up and running, get in touch - don't burn your time trying to work through things that are getting in the way of what we're trying to evaluate.

If you do want to get in touch, you can contact:

* Vaasugi Velmurugu via email: [vxv@enablehr.com](mailto:vxv@enablehr.com)
* Milesh Bhana via email: [mxb@enablehr.com](mailto:mxb@enablehr.com)
* Martin Lau via email: [msl@enablehr.com](mailto:msl@enablehr.com)

If you send an email, include all four of us to have the best chance of someone replying quickly.

# Good luck!

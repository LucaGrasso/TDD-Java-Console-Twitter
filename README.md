# TDD-Java-Console-Twitter
A console-based social networking application (similar to Twitter).


Technical instructions:


MacOs:

```sh
$ open Terminal
$ cd \...\TDD-Java-Console-Twitter\bin\com\lg\ctwit
$ JAVA -JAR ctwit.jar
$ Exit (for close.)
```
Windows:

```sh
$ open Command Prompt
$ cd \...\TDD-Java-Console-Twitter\bin\com\lg\ctwit
$ JAVA -JAR ctwit.jar
$ Exit (for close.)
```
## Developer

My IDE is Eclipse.

with "System.getProperty("line.separator")" the Test work fine with MacOs and Windows

### Dependencies info for developer:

- JavaSE-1.8

- Maven Dependencies
 
- Mockito 2.18.3
	
- Junit5 
	
- Assertj 3.1.0
	

Exercise

Implement a console-based social networking application (similar to Twitter) satisfying the scenarios 
below.

Features

1) Posting: Alice can publish messages to a personal timeline

> Alice -> I love the weather today
> Bob -> Damn! We lost!
> Bob -> Good game though.

2) Reading: I can view Alice and Bob’s timelines

> Alice
> I love the weather today (5 minutes ago)

> Bob
> Good game though. (1 minute ago)
> Damn! We lost! (2 minutes ago)

3) Following: Charlie can subscribe to Alice’s and Bob’s timelines, and view an aggregated list of all 
subscriptions

> Charlie -> I'm in New York today! Anyone want to have a coffee?

> Charlie follows Alice
> Charlie wall

>Charlie - I'm in New York today! Anyone want to have a coffee? (2 seconds ago)
> Alice - I love the weather today (5 minutes ago)

> Charlie follows Bob
> Charlie wall

> Charlie - I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)
> Bob - Good game though. (1 minute ago)
> Bob - Damn! We lost! (2 minutes ago)
> Alice - I love the weather today (5 minutes ago)

Details

● The application must use the console for input and output.

● Users submit commands to the application. There are four commands. “posting”, “reading”, etc. 
are not part of the commands; commands always start with the user’s name.

	○ posting: <user name> -> <message>
	○ reading: <user name>
	○ following: <user name> follows <another user>
	○ wall: <user name> wall

● Don't worry about handling any exceptions or invalid commands. Assume that the user will 
always type the correct commands. Just focus on the sunny day scenarios.

● Don’t bother making it work over a network or across processes. It can all be done in memory, 
assuming that users will all use the same terminal.

● Non-existing users should be created as they post their first message. Application should not start 
with a pre-defined list of users.

● Use whichever language, libraries, frameworks and tools you like.

● Provide instructions on how to run the application.

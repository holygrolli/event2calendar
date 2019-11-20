# event2calendar - Parse events from web pages and add it to your calendar

![](https://github.com/adulescentulus/event2calendar/workflows/Deploy%20to%20AWS%20Lambda/badge.svg)

This projects was developed to create my custom conference schedule for conference without an easy way to create a 
personal schedule with all talks and sessions. On supported conference sites you browse all wanted sessions and then 
click on a small bookmarklet, which will send the current URL to the backend service, which will parse the sessions dates 
and present you with a button to Google Calendar, where you can put this calendar event to one or different calendars. 
After you have all interesting sessions together you will be able to decide, which overlap and which alternate session you 
could attend.

You can visit the main event2calendar website [here](http://e2c.networkchallenge.de). There you will find the bookmarklet.

Every conference or festival needs its own parser. So only supported websites are able to be scanned for events. 
Also each session needs to be on a separate website to be extracted.

## Contribute

All parsers implement the interface [IEventParser](lambda-modules/lambda.backend/src/main/java/de/networkchallenge/e2c/lambda/backend/parser/api/IEventParser.java). 
So feel free to open a pull request with your own parser.
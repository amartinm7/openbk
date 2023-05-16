# Openbk

## TODO Reminder: next steps
- Split repository into reader and writer. Segregation of concerns https://medium.com/javarevisited/what-is-cqrs-command-and-query-responsibility-segregation-pattern-7b1b38514edd
- implement a command-bus
- implement a event-bus
- integrate with kafka

## Running the documentation server

https://docsify.js.org/#/quickstart

```bash
npm i docsify-cli -g
```

init docs
```bash
docsify init ./_docs
```

run the server
```bash
docsify serve _docs
```


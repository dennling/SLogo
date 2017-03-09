dl186
az73

## Backend Refactoring

#### We took a look at the TreeParser file and noticed that the createTree(Input input) was an extremely long method. We subsequently took out a major chunk code that had it's own function. Specifically, we removed the createCommandNode functionality when we found that the input into the parser was a command, and factored that into its own method. The purpose of this chunk was to differentiate where the command would be found and run.

#### We chose to refactor this because the original method was extremely dense and hard to read.
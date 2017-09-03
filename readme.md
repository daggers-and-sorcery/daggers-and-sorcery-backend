# Daggers & Sorcery Backend
## Installation
### MySQL
For the server to run in development mode you need a MySQL server running on your local machine (with localhost) on the default port settings. The user used to access this database is root with the password of root. You also need to create the 'swords' database in MySQL before running the application.

### Redis
You also need to run a local redis instance with the default settings. Please make sure that the installed Redis version 3.x+.

## Coding guidelines
### Package naming
Please don't use 'impl' as a package name. The interface and the implementations should either sit in the same package or logically placed different subpackages.
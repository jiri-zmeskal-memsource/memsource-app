# Memsource project listing
Application for listing memsource projects. It also provides storage of Memsource credentials in the H2 database. Application is deployed on Heroku and can be viewed [here](https://memsource-app.herokuapp.com/)

## Architecture
- Backend is done in SpringBoot, it runs against H2 database and provides set of rest-apis for credentials manipulation and project listing
- Frontend is done in Angular 9 (+ Bootstrap). Frontend app uses rest-apis provided by the backend

## Implemented features
- Sign in
- Sign out
- Memsource projects listing
- Token refresh (done by Spring scheduling)

## Tests
Tests were deliberatelly omitted for now. 

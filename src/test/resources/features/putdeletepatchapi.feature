Feature: Testing API PUT/DELETE/PATCH

  @Sample
  Scenario: Testing the API PUT/DELETE/PATCH endpoints
    Given Config URL: 'https://reqres.in'
    Given Create a user with name: CaptainPlanet job: SuperHero using request: '/api/users'
    Given Update existing user using request: '/api/users/'
    Given Delete user using request: '/api/users/'
    Given Verify that user is deleted using request: '/api/user/'


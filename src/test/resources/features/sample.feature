Feature: Testing API

  @Sample
  Scenario: Sample API scenario
    Given Config URL: 'https://reqres.in/api'
    Given Testing GET, parameters: 'users?page=2'

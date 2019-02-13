Feature: Testing API

  @Sample
  Scenario: Sample API scenario
    Given Config URL: 'https://reqres.in/api'
    Given Set request specification to given
    Given Testing GET, parameters: 'users?page=2'

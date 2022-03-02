Feature: fetching User Details

Scenario: testing the get call for User Details

    Given url 'https://reqres.in/api/users/2'
    When method GET
    Then status 200

    #We are printing the Response of the API using the print keyword
    Then print response

    # Declaring and assigning a string value:
    Given def varName = '****************value'

    # using a variable
    Then print varName
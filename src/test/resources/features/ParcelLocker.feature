Feature: Parcel Lockers API Search

  Scenario Outline: Search for parcel lockers in a city
    When I send a request to search parcel lockers in "<city>"
    Then I save the parcel lockers data to parcellockers.<city>.json

    Examples:
      | city     |
      | Warsaw   |
      | Krakow   |
      | Gdansk   |